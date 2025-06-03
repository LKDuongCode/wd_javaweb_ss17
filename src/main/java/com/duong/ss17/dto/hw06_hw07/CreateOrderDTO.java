package com.duong.ss17.dto.hw06_hw07;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDTO {
    private int customerId;
    private String recipientName;
    private String phoneNumber;
    private String address;
}
