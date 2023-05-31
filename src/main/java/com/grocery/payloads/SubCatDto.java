package com.grocery.payloads;


import com.grocery.entities.Category;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SubCatDto {

	
	private Integer subId;
	private String subName;
	
	private CategoryDto category;
}
