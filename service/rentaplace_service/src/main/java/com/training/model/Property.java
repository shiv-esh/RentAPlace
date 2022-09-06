package com.training.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "property", schema = "rent_a_place")
public class Property {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pid;

	@Column
	private String name;

	@Column
	String location;

	@Column
	private String type;

	@Column
	private String features;

	@Column
	private String description;
	
	@Column
	private String phone;

	@Column
	private int owner_id;
	
	@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "property")
	private Booking booking;
//	@Column
//	private int rating;
	
	@Column
	private int price;
	
	@Lob
	private byte[] image;
	
	@Lob
	private byte[] image1;
	@Lob
	private byte[] image2;
	@Lob
	private byte[] image3;
	

}
