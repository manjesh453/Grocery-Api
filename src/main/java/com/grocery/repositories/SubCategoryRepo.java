package com.grocery.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.grocery.entities.Category;
import com.grocery.entities.SubCategory;


public interface SubCategoryRepo extends JpaRepository<SubCategory, Integer>{

	List<SubCategory>findByCategory(Category category);
	

}
