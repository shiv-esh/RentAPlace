package com.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.training.Dto.PropertyDto;
import com.training.model.Property;
import com.training.service.PropertyService;

@CrossOrigin("*")
@RestController

public class PropertyController {
	@Autowired
	PropertyService propertyService;
	@GetMapping("/properties")
	public List<Property> getAllProperties(){
		return propertyService.getAllProperties();
	}
	@GetMapping("/property/{pid}")
	public PropertyDto getProperty(@PathVariable("pid") int pid){
		return propertyService.findProperty(pid);
	}
	
	
	
	

}
