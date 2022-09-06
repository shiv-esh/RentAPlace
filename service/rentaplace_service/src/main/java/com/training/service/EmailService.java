package com.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.Dto.ServerResponse;
import com.training.model.Email;
import com.training.repo.EmailRepo;
import com.training.repo.PropertyRepo;

@Service
public class EmailService {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PropertyService propService;
	@Autowired
	PropertyRepo propRepo;
	@Autowired
	com.training.repo.UserRepo userRepo;
	
	@Autowired
	EmailRepo emailRepo;
	
	public void send(int pid,int user_id) {
		String propertyname=propRepo.findById(pid).get().getName();
		String username =userRepo.findById(user_id).get().getName();
		String message = propertyname+" booking requested by "+ username;
		Email email=new Email();
		email.setEmailBody(message);
		email.setOwner_id(propRepo.findById(pid).get().getOwner_id());
		email.setPid(pid);
		email.setUser_id(user_id);
		email.setUseremail(userRepo.findById(user_id).get().getEmail());
		emailRepo.save(email);
		
		
	}
	
	public List<Email> myEmail(int owner_id) {
		return emailRepo.findByOwner_id(owner_id);
	}

}
