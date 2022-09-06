package com.training.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
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
	private byte[] returnedImage;
    
	 public MultipartFile image;
	 
	 private byte[] returnedImage1;
	    
	 public MultipartFile image1;
	 
	 private byte[] returnedImage2;
	    
	 public MultipartFile image2;
	 
	 private byte[] returnedImage3;
	    
	 public MultipartFile image3;

}
