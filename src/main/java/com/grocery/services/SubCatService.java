package com.grocery.services;

import java.util.List;

import com.grocery.payloads.SubCatDto;

public interface SubCatService {
	
	SubCatDto createSubCategory(SubCatDto subcat,Integer categoryId);
	
	SubCatDto updateSubCategory(SubCatDto subcat,Integer subcatId,Integer categoryId);
	
	void deleteSubCategory(Integer subcatId);
	
	SubCatDto getSubCatById(Integer subcatId);
	
	List<SubCatDto>getAllSubCategory();
	
	List<SubCatDto>getSubCategoryByCategory(Integer categoryId);

}
