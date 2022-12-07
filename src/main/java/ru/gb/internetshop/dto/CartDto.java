package ru.gb.internetshop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CartDto {
    List<CartItemDto> items=new ArrayList<>();
    private int totalPrice;


    public int calcTotalPrice(){
        totalPrice=0;
        for (CartItemDto i:items){
            totalPrice+=i.getTotalPrice();
        }
        return totalPrice;
    }
}
