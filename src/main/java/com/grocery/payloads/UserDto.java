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
	
    private String userName;
	
	private String userPassword;
	
	private String email;
	
	private String userContact;
	
	private String userAddress;
	
	private Set<RoleDto>roles=new HashSet<>();
}
