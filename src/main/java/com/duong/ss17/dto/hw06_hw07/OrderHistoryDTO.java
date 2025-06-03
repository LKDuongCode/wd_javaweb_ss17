package com.duong.ss17.dto.hw06_hw07;

import com.duong.ss17.entity.hw06_hw07.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistoryDTO {
    private int id;
    private String recipientName;
    private double totalMoney;
    private OrderStatus status;
    private List<String> productNames;

}