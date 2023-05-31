package com.grocery.entities;


import com.grocery.payloads.CategoryDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer subId;
	private String subName;
	
	@ManyToOne
	private Category category;
}
