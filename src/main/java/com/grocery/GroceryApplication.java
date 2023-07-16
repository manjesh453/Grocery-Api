package com.grocery;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.grocery.config.AppConstant;
import com.grocery.entities.Role;
import com.grocery.repositories.RoleRepo;

@SpringBootApplication
public class GroceryApplication implements CommandLineRunner {
   @Autowired
	private RoleRepo roleRepo;
	public static void main(String[] args) {
		SpringApplication.run(GroceryApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	

	@Override
	public void run(String... args) throws Exception {
		try {
			Role role=new Role();
			role.setId(AppConstant.ADMIN_USER);
			role.setName("ROLE_ADMIN");
			Role role1=new Role();
			role1.setId(AppConstant.Normal_USER);
			role1.setName("ROLE_NORMAL");
			
			List<Role>roles=new ArrayList<>();
			roles.add(role);
			roles.add(role1);
			
			this.roleRepo.saveAll(roles);
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	

}	
