package ru.gb.internetshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.internetshop.cart.Cart;
import ru.gb.internetshop.cart.CartItem;
import ru.gb.internetshop.exceptions.ResourceNotFoundException;
import ru.gb.internetshop.entities.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {
    private Cart cart;
    private final ProductService productService;

    @PostConstruct
    public void init(){
        cart=new Cart();
        cart.setItems(new ArrayList<>());
    }

    public Cart getCurrentCart(){
        return cart;
    }

    public void subToCart(Long productId){
        System.out.println(cart);
        for (CartItem i: cart.getItems()){
            if (productId.equals(i.getProductId()) && i.getAmount()>1) {
                i.decrementAmount();
                break;
            }else if (productId.equals(i.getProductId())){
                cart.remove(i);
                break;
            }
        }
    }

    public void removeItem(Long productId){
        for (CartItem i: cart.getItems()){
            if (productId.equals(i.getProductId())){
                cart.remove(i);
                break;
            }
        }
    }
    public void addToCart(Long productId){
        Product p = productService.findById(productId).orElseThrow(()->new ResourceNotFoundException("Продукт с id:"+productId+" не найден"));
        cart.add(p);
    }
}
