package com.grocery.payloads;

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
	
	private String userEmail;
	
	private String userType;
	
	private String userContact;
	
	private String userAddress;
}
