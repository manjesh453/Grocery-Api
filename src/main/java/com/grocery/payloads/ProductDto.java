package com.grocery.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProductDto {

	
	private String productName;
	
	private float productPrice;
	
	private float productDiscount;
	
	private String productImage;
	
	private String productAmount;
	
	private SubCatDto SubCat;
}
