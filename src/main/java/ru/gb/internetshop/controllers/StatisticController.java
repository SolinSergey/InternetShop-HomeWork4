package ru.gb.internetshop.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.internetshop.dto.ProductDto;
import ru.gb.internetshop.entities.Product;
import ru.gb.internetshop.entities.User;
import ru.gb.internetshop.proxy.AppLoggingAspect;
import ru.gb.internetshop.services.ProductService;
import ru.gb.internetshop.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistic")
public class StatisticController {
    private final AppLoggingAspect appLoggingAspect;
    private final ProductService productService;
    private final UserService userService;
    @GetMapping
    public List<String> outputStatistic(){
        appLoggingAspect.profilingInit();

        List <String> result=new ArrayList<>();
        Product product=productService.findById(11L).get();
        List<ProductDto> list = productService.findAll();
        ProductDto productDto = new ProductDto();
        Random random = new Random();
        productDto.setTitle("Морковка заячья"+ random.nextInt(1000));
        productDto.setPrice(100);
        productDto.setCategory("Прочее");
        productService.addProduct(productDto);
        result.add("Profiling ProductService: "+ appLoggingAspect.getProductServiceProfilingTime());

        User user = userService.findByUsername("serg").get();
        UserDetails userDetails=userService.loadUserByUsername("serg");
        result.add("Profiling UserService: "+ appLoggingAspect.getUserServiceProfilingTime());

        return result;
    }
}
