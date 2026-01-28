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

	// Image URLs instead of binary data
	private String imageUrl;
	private String imageUrl1;
	private String imageUrl2;
	private String imageUrl3;

	// For file uploads
	public MultipartFile image;
	public MultipartFile image1;
	public MultipartFile image2;
	public MultipartFile image3;

}
