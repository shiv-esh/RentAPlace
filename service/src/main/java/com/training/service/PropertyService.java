package com.training.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.Dto.BookingDto;
import com.training.Dto.PropertyDto;
import com.training.model.Booking;
import com.training.model.Property;
import com.training.model.User;
import com.training.repo.BookingRepo;
import com.training.repo.PropertyRepo;
import com.training.repo.UserRepo;


@Service
public class PropertyService {
@Autowired
PropertyRepo propertyRepo;

@Autowired
BookingRepo bookingRepo;

@Autowired
UserRepo userRepo;

public List<Property> getAllProperties(){
	return propertyRepo.findAll();
}
public void addProperty(Property property) {
	 propertyRepo.save(property);
}



public void updateProperty(Property property) {
	 propertyRepo.save(property);
}

public void deleteProperty(int pid) {
	propertyRepo.deleteById(pid);
}

public String addProperty(PropertyDto propertydto) {
	Property property = new Property();
	property.setName(propertydto.getName());
	property.setLocation(propertydto.getLocation());
	property.setType(propertydto.getType());
	//property.setFeatures(propertydto.getFeatures());
	property.setDescription(propertydto.getDescription());
	property.setPhone(propertydto.getPhone());
	property.setOwner_id(propertydto.getOwner_id());
	property.setPrice(propertydto.getPrice());
	propertyRepo.save(property);
	Booking booking =new Booking();
	Property pr =new Property();
	booking.setProperty(propertyRepo.findByName(propertydto.getName()).get());
	booking.setStatus(false);
	bookingRepo.save(booking);
	return "Property is added successfully";
	
}

public List<PropertyDto> getnbProperty(){
	
	List<PropertyDto> propertydto =new ArrayList<>();
//	propertyRepo.findByStatus(false).forEach(property->{
//		propertydto.add(ConverttoPropertyto(property));
//		
//	});
	System.out.println("hello");
	bookingRepo.findByStatus(false).forEach(booking->{
		System.out.println(booking);
		System.out.println(booking.getUser_id());
		if(booking.getUser_id()==0) {
			System.out.println(booking.getProperty());
		Property property =booking.getProperty();
		propertydto.add(ConverttoPropertyto(property));
		}
	});
	
	return propertydto;
}

public String book(BookingDto bookingdto) {
	Property property= propertyRepo.findById(bookingdto.getPid()).get();
	System.out.println(property+"book");
	 Booking booking=bookingRepo.findByProperty(property);
	 booking.setStatus(false);
	 booking.setUser_id(bookingdto.getUser_id());
	 System.out.println("hello"+booking);
	 booking.setCheckin(bookingdto.getCheckin());
	 booking.setCheckout(bookingdto.getCheckout());
	 bookingRepo.save(booking);
	 
	return "booked successfull";
}

PropertyDto ConverttoPropertyto(Property property) {
	PropertyDto propertyDto= new PropertyDto();
	propertyDto.setPid(property.getPid());
	propertyDto.setName(property.getName());
	//propertyDto.setDescription(property.getDescription());
//	propertyDto.setLocation(property.getLocation());
//	propertyDto.setOwner_id(property.getOwner_id());
//	propertyDto.setOwnername(userRepo.findById(property.getOwner_id()).get().getName());
//	propertyDto.setPrice(property.getPrice());
//	propertyDto.setPhone(property.getPhone());
//	propertyDto.setType(property.getType());
	return propertyDto;
	
		
}

public String approveproperty(int bid)
{  Booking booking =bookingRepo.findById(bid).get();
  booking.setStatus(true);
  bookingRepo.save(booking);

	return "approved";
}
public String disapproveproperty(int bid)
{  Booking booking =bookingRepo.findById(bid).get();
  booking.setStatus(false);
  booking.setUser_id(0);
  booking.setCheckin(null);
  booking.setCheckout(null);
  bookingRepo.save(booking);

	return "approved";
}

}
