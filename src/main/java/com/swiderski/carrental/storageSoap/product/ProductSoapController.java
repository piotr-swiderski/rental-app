package com.swiderski.carrental.storageSoap.product;

import com.swiderski.carrental.crud.ApiPageable;
import com.swiderski.carrental.xlsxGenerator.XlsxGenerator;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.slowikowski.jakub.soap_example._abstract.GetAllResponse;
import pl.slowikowski.jakub.soap_example.product.ProductXmlObject;
import pl.slowikowski.jakub.soap_example.product.SaveProductRequest;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

import static com.swiderski.carrental.soapClient.MessageUtils.ID_VALID_MESSAGE;

@RestController
@RequestMapping("${rest.api.version}/soap/products")
public class ProductSoapController {

    private final ProductSoapService productSoapService;

    public ProductSoapController(ProductSoapService productSoapService) {
        this.productSoapService = productSoapService;
    }

    @GetMapping("/{id}")
    public ProductXmlObject getById(@PathVariable @PositiveOrZero(message = ID_VALID_MESSAGE) long id) {
        return productSoapService.getById(id);
    }

    @PostMapping
    public ProductXmlObject save(@ModelAttribute SaveProductRequest request) {
        return productSoapService.save(request);
    }

    @PutMapping("/{id}")
    public ProductXmlObject update(@ModelAttribute ProductXmlObject product) {
        return productSoapService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public ProductXmlObject delete(@PathVariable @PositiveOrZero(message = ID_VALID_MESSAGE) long id) {
        return productSoapService.deleteProduct(id);
    }

    @PostMapping("buy/{id}")
    public ProductXmlObject buyProduct(@PathVariable @PositiveOrZero(message = ID_VALID_MESSAGE) long id) {
        return productSoapService.buyProduct(id);
    }

    @GetMapping("productsInGroup/{id}")
    @ApiPageable
    public GetAllResponse getProductsInGroup(@NotNull @ApiIgnore Pageable pageable,
                                             @PathVariable @PositiveOrZero(message = ID_VALID_MESSAGE) long id) {
        return productSoapService.productsInGroup(pageable, id);
    }

    @GetMapping
    @ApiPageable
    public GetAllResponse allProducts(@NotNull @ApiIgnore Pageable pageable, @RequestParam(required = false, defaultValue = "") String search) {
        return productSoapService.getAllProducts(pageable, search);
    }

    @GetMapping(value = "/excel", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiPageable
    public ResponseEntity<byte[]> getExcel(@ApiIgnore Pageable pageable, @RequestParam(required = false) String search) {
        byte[] excel = XlsxGenerator.customersToExcel(productSoapService.getAllProducts(pageable, search).getContent());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"report" + LocalDateTime.now() + ".xlsx\"")
                .body(excel);
    }

}
