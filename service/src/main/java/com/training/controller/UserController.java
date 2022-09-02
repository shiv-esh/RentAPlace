package com.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.Dto.BookingDto;
import com.training.Dto.PropertyDto;
import com.training.Dto.SignUpRequest;
import com.training.Dto.UserDto;
import com.training.model.User;
import com.training.service.PropertyService;
import com.training.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
public class UserController {
	
@Autowired
UserService userService;

@Autowired
PropertyService propertyService;


@GetMapping("")
public List<User> getAllUsers(){
	
	return userService.getAllUsers();
}
@PostMapping("/signUp")
public UserDto signUp(@RequestBody SignUpRequest signUpRequest){
    return userService.signup(signUpRequest);

}

@GetMapping("/statusProperty")
public List<PropertyDto> getProperty(){
	System.out.println("nice");
	return propertyService.getnbProperty();
}

@PostMapping("/booking")
public String book(@RequestBody BookingDto bookingdto)
{
	return propertyService.book(bookingdto);
}

//@GetMapping("/user")
//public User getUserById(@RequestParam Integer user_id){
//
//	return userService.getUserById(user_id);
//}

}
