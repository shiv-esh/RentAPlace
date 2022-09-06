package com.training.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.training.model.Booking;
import com.training.model.Property;
@Repository
public interface PropertyRepo extends JpaRepository<Property, Integer>{

   Optional<Property> findByName(String name);
   @Query(value = "select * from property where owner_id=?1",nativeQuery = true)
   List<Property> findByOwner_id(int owner_id);
   Property findByPid(int pid);

	
	
}
