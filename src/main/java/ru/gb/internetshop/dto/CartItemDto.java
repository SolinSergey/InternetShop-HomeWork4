package ru.gb.internetshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {
    private Long id;
    private String title;
    private int price;
    private int amount;
    private int totalPrice;

    public void calcTotalPrice(){
        totalPrice=price*amount;
    }
}
