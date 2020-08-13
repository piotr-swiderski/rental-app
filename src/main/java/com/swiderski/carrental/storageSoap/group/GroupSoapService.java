package com.swiderski.carrental.storageSoap.group;


import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.slowikowski.jakub.soap_example._abstract.GetAllResponse;
import pl.slowikowski.jakub.soap_example.product_group.DeleteProductGroupRequest;
import pl.slowikowski.jakub.soap_example.product_group.GetAllProductGroupsRequest;
import pl.slowikowski.jakub.soap_example.product_group.ObjectFactory;
import pl.slowikowski.jakub.soap_example.product_group.ProductGroupPort;
import pl.slowikowski.jakub.soap_example.product_group.ProductGroupPortService;
import pl.slowikowski.jakub.soap_example.product_group.ProductGroupRequest;
import pl.slowikowski.jakub.soap_example.product_group.ProductGroupXmlObject;
import pl.slowikowski.jakub.soap_example.product_group.SaveProductGroupRequest;


@Service
public class GroupSoapService {

    private final ProductGroupPort clientPort;
    ObjectFactory objectFactory = new ObjectFactory();
    ProductGroupWSMapper productGroupWSMapper;

    public GroupSoapService(ProductGroupWSMapper productGroupWSMapper) {
        this.productGroupWSMapper = productGroupWSMapper;
        this.clientPort = new ProductGroupPortService().getProductGroupPort();
    }

    public ProductGroupXmlObject getById(long id) {
        ProductGroupRequest request = objectFactory.createProductGroupRequest();
        request.setId(id);
        return clientPort.findProductGroupById(request);
    }

    public GetAllResponse getAllProducts(Pageable pageable, String search) {
        GetAllProductGroupsRequest request = objectFactory.createGetAllProductGroupsRequest();
        request.setPageable(productGroupWSMapper.toPageableXml(pageable));
        request.setSearch(search);

        return clientPort.getAllProductGroups(request);
    }

    public ProductGroupXmlObject deleteProduct(long id) {
        DeleteProductGroupRequest request = objectFactory.createDeleteProductGroupRequest();
        request.setId(id);

        return clientPort.deleteProductGroup(request);
    }

    public ProductGroupXmlObject save(SaveProductGroupRequest request) {
        return clientPort.saveProductGroup(request);
    }

    public ProductGroupXmlObject updateProduct(ProductGroupXmlObject request) {
        return clientPort.updateProductGroup(request);
    }
}