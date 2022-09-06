package com.training.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "booking", schema = "rent_a_place")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int booking_id;
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pid", nullable = false)
    private Property property;
	
	@Column
	private int user_id;
	
	@Column
	private boolean status;
	
	@Column 
	private Date checkin;
	
	@Column 
	private Date checkout;
	
	

}
