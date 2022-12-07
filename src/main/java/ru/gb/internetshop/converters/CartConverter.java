package ru.gb.internetshop.converters;

import org.springframework.stereotype.Component;
import ru.gb.internetshop.cart.Cart;
import ru.gb.internetshop.cart.CartItem;
import ru.gb.internetshop.dto.CartDto;
import ru.gb.internetshop.dto.CartItemDto;

@Component
public class CartConverter {
    public CartDto entityToCartDto(Cart cart) {
        CartDto cartDto = new CartDto();

        for (CartItem i : cart.getItems()) {
            CartItemDto cartItemDto = new CartItemDto();
            cartItemDto.setId(i.getProductId());
            cartItemDto.setTitle(i.getProductTitle());
            cartItemDto.setPrice(i.getPrice());
            cartItemDto.setAmount(i.getAmount());
            cartItemDto.calcTotalPrice();
            cartDto.getItems().add(cartItemDto);
        }
        cartDto.calcTotalPrice();
        return cartDto;
    }
}
