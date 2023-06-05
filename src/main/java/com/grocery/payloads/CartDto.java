package com.grocery.payloads;

import com.grocery.entities.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDto {
   
    private Integer cartQuantity;
	
	private float cartAmount;
	
	private Product product;
}
