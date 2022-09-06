package com.training.Dto;

import java.sql.Date;

import javax.persistence.SecondaryTable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
	
	private int pid;
	
	private int user_id;
	
	private Date checkin;
	
	private Date checkout;
	
	

	
	
}
