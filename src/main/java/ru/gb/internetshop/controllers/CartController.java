package ru.gb.internetshop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.internetshop.converters.CartConverter;
import ru.gb.internetshop.dto.CartDto;
import ru.gb.internetshop.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/add/{productId}")
    public void addProductToCart(@PathVariable Long productId){
        cartService.addToCart(productId);
    }

    @GetMapping("/sub/{productId}")
    public void subProductFromCurrentCart(@PathVariable Long productId){
        cartService.subToCart(productId);
    }

    @DeleteMapping("/remove/{id}")
    public void removeItem(@PathVariable Long id) {
        cartService.removeItem(id);
    }

    @GetMapping
    public CartDto getCurrentCart(){
        return cartConverter.entityToCartDto(cartService.getCurrentCart());
    }

}
