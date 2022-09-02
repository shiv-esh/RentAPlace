package com.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.training.Dto.PropertyDto;
import com.training.Dto.ServerResponse;
import com.training.Dto.SignUpRequest;
import com.training.Dto.UserDto;
import com.training.model.User;

import com.training.service.PropertyService;
import com.training.service.UserService;
@CrossOrigin("*")
@RestController
@RequestMapping("/owners")
public class OwnerController {
	



@Autowired
PropertyService propertyService;



@PostMapping("/addProperty")
public ServerResponse addProperty(@RequestBody PropertyDto propertydto) {
	ServerResponse response=new ServerResponse();
	propertyService.addProperty(propertydto);
	System.out.println("addproperty");
	response.setMessage("Propertyadded successfully");
    response.setStatus(HttpStatus.OK);
    return  response;
}
@PostMapping("/approve/{bid}")
public String approveProperty(@PathVariable("bid")int bid) {
	
	return propertyService.approveproperty(bid);
}

}