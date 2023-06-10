package com.grocery.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table
@Getter
@Setter
public class User {
      
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	private String userName;
	
	private String userPassword;
	
	private String userEmail;
	
	private String userType;
	
	private String userContact;
	
	private String userAddress;

	
	
}
