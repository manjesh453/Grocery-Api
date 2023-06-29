package com.grocery.payloads;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

	private Integer id;
	
    private String name;
	
	private String password;
	
	private String email;
	
	private String contact;
	
	private String address;
	
	private Set<RoleDto>roles=new HashSet<>();
}
