package com.swiderski.carrental.storageSoap.product;


import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.slowikowski.jakub.soap_example._abstract.GetAllResponse;
import pl.slowikowski.jakub.soap_example.product.*;


@Service
public class ProductSoapService {

    private final ProductPort clientPort;
    ObjectFactory objectFactory = new ObjectFactory();
    ProductWSMapper productWSMapper;

    public ProductSoapService(ProductWSMapper productWSMapper) {
        this.productWSMapper = productWSMapper;
        this.clientPort = new ProductPortService().getProductPort();
    }

    public ProductXmlObject getById(long id) {
        FindProductRequest request = objectFactory.createFindProductRequest();
        request.setId(id);
        return clientPort.findProduct(request);
    }

    public GetAllResponse getAllProducts(Pageable pageable, String search) {
        GetAllProductsRequest request = objectFactory.createGetAllProductsRequest();
        request.setPageable(productWSMapper.toPageableXml(pageable));
        request.setSearch(search);

        return clientPort.getAllProducts(request);
    }

    public ProductXmlObject deleteProduct(long id) {
        DeleteProductRequest request = objectFactory.createDeleteProductRequest();
        request.setId(id);

        return clientPort.deleteProduct(request);
    }

    public GetAllResponse productsInGroup(Pageable pageable, long groupId) {
        ProductsInGroupRequest request = objectFactory.createProductsInGroupRequest();
        request.setId(groupId);
        request.setPageable(productWSMapper.toPageableXml(pageable));

        return clientPort.productsInGroup(request);
    }


    public ProductXmlObject save(SaveProductRequest request) {
        return clientPort.saveProduct(request);
    }

    public ProductXmlObject buyProduct(long productId) {
        BuyProductRequest request = objectFactory.createBuyProductRequest();
        request.setId(productId);

        return clientPort.buyProduct(request);
    }

    public ProductXmlObject updateProduct(ProductXmlObject request) {
        return clientPort.updateProduct(request);
    }
}