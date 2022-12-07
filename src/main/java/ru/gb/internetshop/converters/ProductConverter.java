package ru.gb.internetshop.converters;

import org.springframework.stereotype.Component;
import ru.gb.internetshop.dto.ProductDto;
import ru.gb.internetshop.entities.Product;

@Component
public class ProductConverter {
    public ProductDto entityToProductDto (Product product){
        ProductDto productDto=new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory().getTitle());
        return productDto;
    }
}
