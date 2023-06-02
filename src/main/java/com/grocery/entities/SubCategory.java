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
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer subId;
	private String subName;
	
	@ManyToOne
	private Category category;
	
	@OneToMany(mappedBy = "SubCat",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Product> product=new ArrayList<>();
}
