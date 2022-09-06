package com.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.Dto.ServerResponse;
import com.training.model.Email;
import com.training.service.EmailService;

@CrossOrigin("*")
@RestController
@RequestMapping("email/")
public class EmailController {
	
	@Autowired
	EmailService emailService;
	
	@GetMapping("owner/{owner_id}")
	public List<Email> getEmails(@PathVariable("owner_id") int owner_id){
		return emailService.myEmail(owner_id);
	}
	
	@PostMapping("owner/{pid}/{user_id}")
	public ServerResponse send(@PathVariable("pid") int pid,@PathVariable("user_id") int user_id) {
		ServerResponse response=new ServerResponse();
		emailService.send( pid,user_id);
		response.setMessage("Email Sent");
		response.setStatus(HttpStatus.OK);
		return response;
	}

}
