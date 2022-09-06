package com.training.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.repo.BookingRepo;

@Service
public class BookingService {

	@Autowired
	BookingRepo bookingRepo;
	
	
	
}
