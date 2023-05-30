package com.grocery.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.entities.Category;
import com.grocery.entities.SubCategory;
import com.grocery.exceptions.ResourceNotFoundException;
import com.grocery.payloads.SubCatDto;
import com.grocery.repositories.CategoryRepo;
import com.grocery.repositories.SubCategoryRepo;
import com.grocery.services.SubCatService;

@Service
public class SubCatServiceImpl implements SubCatService{
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private SubCategoryRepo subcatRepo;
    @Autowired
	private ModelMapper modelMapper;
	
	@Override
	public SubCatDto createSubCategory(SubCatDto subcat, Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		SubCategory subcategory=this.modelMapper.map(subcat, SubCategory.class);
		subcategory.setCategory(cat);
		SubCategory newSubCategory=this.subcatRepo.save(subcategory);
		return this.modelMapper.map(newSubCategory, SubCatDto.class);
	}

	@Override
	public SubCatDto updateSubCategory(SubCatDto subcat, Integer subcatId,Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		SubCategory subCat=this.subcatRepo.findById(subcatId).orElseThrow(()->new ResourceNotFoundException("SubCategory", "SubCategoryId", subcatId));
		subCat.setSubName(subcat.getSubName());
		subCat.setCategory(subcat.getCategory());
		SubCategory updateSub=this.subcatRepo.save(subCat);
		return this.modelMapper.map(updateSub,SubCatDto.class);
	}

	@Override
	public void deleteSubCategory(Integer subcatId) {
		this.subcatRepo.deleteById(subcatId);
		
	}

	@Override
	public SubCatDto getSubCatById(Integer subcatId) {
		SubCategory subCat=this.subcatRepo.findById(subcatId).orElseThrow(()->new ResourceNotFoundException("SubCategory", "SubCategoryId", subcatId));
		return this.modelMapper.map(subCat, SubCatDto.class);
	}

	@Override
	public List<SubCatDto> getAllSubCategory() {
		List<SubCategory>list=this.subcatRepo.findAll();
		List<SubCatDto>subCat=list.stream().map(subcat->this.subToDto(subcat)).collect(Collectors.toList());
		return subCat;
	}

	@Override
	public List<SubCatDto> getSubCategoryByCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
		List<SubCategory>list=this.subcatRepo.findByCategory(cat);
		List<SubCatDto>subcat=list.stream().map((subcate)->this.modelMapper.map(list,SubCatDto.class)).collect(Collectors.toList());
		return subcat;
	}

	public SubCatDto subToDto(SubCategory subcat) {
		return this.modelMapper.map(subcat, SubCatDto.class);
	}
	
}
