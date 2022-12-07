package ru.gb.internetshop.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.gb.internetshop.converters.ProductConverter;
import ru.gb.internetshop.services.ProductService;
import ru.gb.internetshop.soap.products.*;

import java.util.ArrayList;
import java.util.List;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.gb.internetshop.ru/products";
    private final ProductService productService;
    private final ProductConverter productConverter;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProductById(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        ru.gb.internetshop.dto.ProductDto productDto=productConverter.entityToProductDto(productService.findById(request.getId()).get());
        ru.gb.internetshop.soap.products.ProductDto productDto1=new ru.gb.internetshop.soap.products.ProductDto();
        productDto1.setId(productDto.getId());
        productDto1.setTitle(productDto.getTitle());
        productDto1.setPrice(productDto.getPrice());
        productDto1.setCategory(productDto.getCategory());
        response.setProduct(productDto1);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllStudents(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        List<ru.gb.internetshop.dto.ProductDto> list = productService.findAll();
        List<ProductDto> list1=new ArrayList<>();
        for (ru.gb.internetshop.dto.ProductDto l:list){
            ProductDto dto = new ProductDto();
            dto.setId(l.getId());
            dto.setTitle(l.getTitle());
            dto.setPrice(l.getPrice());
            dto.setCategory(l.getCategory());
            list1.add(dto);
        }
        response.getProducts().addAll(list1);
        return response;
    }
}