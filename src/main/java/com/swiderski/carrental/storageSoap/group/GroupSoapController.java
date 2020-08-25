package com.swiderski.carrental.storageSoap.group;

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
import pl.slowikowski.jakub.soap_example.product_group.ProductGroupXmlObject;
import pl.slowikowski.jakub.soap_example.product_group.SaveProductGroupRequest;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

import static com.swiderski.carrental.soapClient.MessageUtils.ID_VALID_MESSAGE;

@RestController
@RequestMapping("${rest.api.version}/soap/groups")
public class GroupSoapController {

    private final GroupSoapService groupSoapService;

    public GroupSoapController(GroupSoapService productService) {
        this.groupSoapService = productService;
    }

    @GetMapping("/{id}")
    public ProductGroupXmlObject getById(@PathVariable @PositiveOrZero(message = ID_VALID_MESSAGE) long id) {
        return groupSoapService.getById(id);
    }

    @PostMapping
    public ProductGroupXmlObject save(@ModelAttribute SaveProductGroupRequest request) {
        return groupSoapService.save(request);
    }

    @PutMapping("/{id}")
    public ProductGroupXmlObject update(@ModelAttribute ProductGroupXmlObject product) {
        return groupSoapService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public ProductGroupXmlObject delete(@PathVariable @PositiveOrZero(message = ID_VALID_MESSAGE) long id) {
        return groupSoapService.deleteProduct(id);
    }

    @GetMapping
    @ApiPageable
    public GetAllResponse allProducts(@NotNull @ApiIgnore Pageable pageable, @RequestParam(required = false) String search) {
        return groupSoapService.getAllProducts(pageable, search);
    }

    @GetMapping(value = "/excel", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiPageable
    public ResponseEntity<byte[]> getExcel(@ApiIgnore Pageable pageable, @RequestParam(required = false) String search) {
        byte[] excel = XlsxGenerator.build(groupSoapService.getAllProducts(pageable, search).getContent());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"report" + LocalDateTime.now() + ".xlsx\"")
                .body(excel);
    }
}
