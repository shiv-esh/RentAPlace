package com.training.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
	
	private String username;
	private String name;
	private String email;
	private String  password;
	private  String phone;
	private String role;

}
