package com.training.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "emails", schema = "rent_a_place")
public class Email {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Column
	private int user_id;
	
	@Column
	private int owner_id ;
	
	@Column
	private int pid;
	
	@Column
	private String emailBody;
	
	@Column 
	private String useremail;
	
}
