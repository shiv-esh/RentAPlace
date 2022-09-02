package com.training.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDto {
	private int pid;
	
	private String name;
	
	private String location;
	
	private String features;
	
	private String type;
	
	private String description;
	
	private String phone;
	
	private int owner_id;
	
	private String ownername;
	
	private int price;
	

}
