package com.training.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.training.model.Booking;
import com.training.model.Property;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Integer>{
	
	List<Booking> findByStatus(Boolean status);
	
	Booking findByProperty(Property property);
	
	@Query(value = "select * from booking where checkout<=?1 or status=false",nativeQuery = true)
	List<Booking> findByCheckins(Date checkin);
	
	@Query(value = "SELECT * FROM booking WHERE (checkin >= ?1 and checkin >= ?2) or (checkout <=  ?1 and checkout <= ?2 ) or user_id=0  " , nativeQuery=true)
	List<Booking> findByCheckinCheckout(Date checkinDate, Date checkoutDate);
}
