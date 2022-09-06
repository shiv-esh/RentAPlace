package com.training.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.model.Property;
import com.training.model.User;
@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	 
	Optional<User> findByUsername(String username);
}
