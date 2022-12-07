package ru.gb.internetshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartPositionDto {
    private Long id;
    private String title;
    private int price;
    private int amount;
}
