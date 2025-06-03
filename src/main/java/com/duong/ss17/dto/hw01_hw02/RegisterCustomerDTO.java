package com.duong.ss17.dto.hw01_hw02;

import com.duong.ss17.entity.hw01_hw02.Role;
import com.duong.ss17.entity.hw01_hw02.UserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterCustomerDTO {
    private String username;
    private String password;
    private String email;
    private String phone;
}
