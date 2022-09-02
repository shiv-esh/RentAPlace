package com.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
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
//	@GetMapping("/property")
//	public Property getProperty(@RequestParam int pid){
//		return propertyService.getPropertyById(pid);
//	}
	@DeleteMapping("/delete/property")
	public void deleteProperty(@RequestParam int pid){
		 propertyService.deleteProperty(pid);
	}
	@PostMapping("/add/property")
	public void  insertProperty(@RequestBody Property property){
		 propertyService.addProperty(property);
	}
	@PutMapping("/update/property")
	public void  updateProperty(@RequestBody Property property){
		 propertyService.updateProperty(property);
	}
	

}
