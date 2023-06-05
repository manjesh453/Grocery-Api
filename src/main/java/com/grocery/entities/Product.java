package com.grocery.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Product {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	
	private String productName;
	
	private float productPrice;
	
	private float productDiscount;
	
	private String productImage;
	
	private float productAmount;
	
	@OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Cart>cartItem=new ArrayList<>();
	
	@ManyToOne
	private SubCategory SubCat;
}
