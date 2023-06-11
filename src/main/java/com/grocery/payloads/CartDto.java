package com.grocery.payloads;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDto {
   
    private Integer cartQuantity;
	
	private float cartAmount;
	
	private ProductDto product;
	
	private UserDto user;
}
