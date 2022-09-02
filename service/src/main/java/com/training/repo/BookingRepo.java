package com.training.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.model.Booking;
import com.training.model.Property;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Integer>{
	
	List<Booking> findByStatus(Boolean status);
	
	Booking findByProperty(Property property);

}
