package com.duong.ss17.dto.hw04_hw05;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCartDTO {
    private int customerId;
    private int productId;
    private int quantity;
}
