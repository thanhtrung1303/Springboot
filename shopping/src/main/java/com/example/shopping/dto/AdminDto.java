package com.example.shopping.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminDto {
    @Size(min=3, max =10, message = "Invalid first name! (3-10 character)")
    private String firstname;

    @Size(min=3, max =10, message = "Invalid first name! (3-10 character)")
    private String lastname;

    private String username;

    @Size(min=5, max =15, message = "Invalid first name! (5-15 character)")
    private String password;

    private String repeatPassword;
}
