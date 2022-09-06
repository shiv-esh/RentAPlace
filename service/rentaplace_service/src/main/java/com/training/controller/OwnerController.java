package com.training.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.training.Dto.PropertyDto;
import com.training.Dto.ServerResponse;
import com.training.Dto.SignUpRequest;
import com.training.Dto.UserDto;
import com.training.model.Property;
import com.training.model.User;

import com.training.service.PropertyService;
import com.training.service.UserService;
@CrossOrigin("*")
@RestController
@RequestMapping("/owners")
public class OwnerController {
	



@Autowired
PropertyService propertyService;

@GetMapping("/myProperties/{owner_id}")
public List<Property> getMyProperties(@PathVariable("owner_id")int owner_id){
	return propertyService.getMyProperties(owner_id);
}

@PostMapping("/addProperty/{owner}")
public ServerResponse addProperty( @PathVariable("owner") String oids,@ModelAttribute PropertyDto propertydto) throws IOException {
	ServerResponse response=new ServerResponse();
	
//		System.out.println(oids);
	String[] splitted=oids.split(",");
	propertydto.setOwner_id(Integer.parseInt(splitted[0]));
	propertydto.setPrice(Integer.parseInt(splitted[1]));
	
		
//		System.out.println(propertydto.getImage());
		propertyService.addProperty(propertydto);
//	}catch(IOException e) {
//		ServerResponse response1 = new ServerResponse();
//        response1.setMessage("Something went wrong");
//        response1.setStatus(HttpStatus.BAD_REQUEST);
//        return response1;
//	}
	
//	System.out.println("addproperty");
	response.setMessage("Propertyadded successfully");
    response.setStatus(HttpStatus.OK);
    return  response;
}
@PostMapping("/approve/{bid}")
public ServerResponse approveProperty(@PathVariable("bid")int bid) {
	
	ServerResponse response=new ServerResponse();
	 propertyService.approveproperty(bid);
	 response.setMessage("Approved ");
		response.setStatus(HttpStatus.OK);
		
		 return response;
}
@PostMapping("/disapprove/{bid}")
public ServerResponse disapproveProperty(@PathVariable("bid")int bid) {
	ServerResponse response=new ServerResponse();
	 propertyService.disapproveproperty(bid);
	 response.setMessage("Dispproved ");
		response.setStatus(HttpStatus.OK);
		
		 return response;
}
@DeleteMapping("/delete/{pid}")
public  ServerResponse deleteProperty(@PathVariable("pid") int pid){
	ServerResponse response=new ServerResponse();
	
	 propertyService.deleteProperty(pid);
	 response.setMessage("Property Deleted successfully");
		response.setStatus(HttpStatus.OK);
	 return response;
}

@PutMapping("/updatePrice/{pid}/{price}")
public void  updatePropertyPrice(@PathVariable("pid") int pid,@PathVariable("price") int price){
	 propertyService.updateProperty(price,pid);
}
}