package com.training.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

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

	public List<Property> getAllProperties() {
		return propertyRepo.findAll();
	}

	public PropertyDto findProperty(int pid) {
		Property property= propertyRepo.findByPid(pid); 
		return ConverttoPropertyto(property);
	}
	public void updateProperty(int price, int pid) {
		Property property = propertyRepo.findByPid(pid);
		property.setPrice(price);
		propertyRepo.save(property);
	}

	public void deleteProperty(int pid) {
		propertyRepo.deleteById(pid);
	}

	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}

	public String addProperty(PropertyDto propertydto) {
		Property property = new Property();
		property.setName(propertydto.getName());
		property.setLocation(propertydto.getLocation());
		property.setType(propertydto.getType());
		 property.setFeatures(propertydto.getFeatures());
		property.setDescription(propertydto.getDescription());
		property.setPhone(propertydto.getPhone());
		property.setOwner_id(propertydto.getOwner_id());
		property.setPrice(propertydto.getPrice());
		if (propertydto.getImage() != null) {
			System.out.println("not null");
			try {
				property.setImage(compressBytes(propertydto.getImage().getBytes()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (propertydto.getImage1() != null) {
			System.out.println("not null");
			try {
				property.setImage1(compressBytes(propertydto.getImage1().getBytes()));
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		}
		if (propertydto.getImage2() != null) {
			System.out.println("not null");
			try {
				property.setImage2(compressBytes(propertydto.getImage2().getBytes()));
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		}
		if (propertydto.getImage3() != null) {
			System.out.println("not null");
			try {
				property.setImage3(compressBytes(propertydto.getImage3().getBytes()));
			} catch (IOException e) {
		
				e.printStackTrace();
			}
		}

		propertyRepo.save(property);
		Booking booking = new Booking();
		Property pr = new Property();
		booking.setProperty(propertyRepo.findByName(propertydto.getName()).get());
		booking.setStatus(false);
		bookingRepo.save(booking);
		return "Property is added successfully";

	}

	public List<Property> getMyProperties(int owner_id) {
		return propertyRepo.findByOwner_id(owner_id);
	}

	public List<PropertyDto> getnbProperty() {

		List<PropertyDto> propertydto = new ArrayList<>();
//	propertyRepo.findByStatus(false).forEach(property->{
//		propertydto.add(ConverttoPropertyto(property));
//		
//	});
		System.out.println("hello");
//		bookingRepo.findByStatus(false).forEach(booking -> {
//			System.out.println(booking);
//			System.out.println(booking.getUser_id());
//			if (booking.getUser_id() == 0) {
//				System.out.println(booking.getProperty());
//				Property property = booking.getProperty();
//				propertydto.add(ConverttoPropertyto(property));
//			}
//		});
		List<Booking> bookings=bookingRepo.findByStatus(false);
		System.out.print(bookings);
		for(Booking booking:bookings) {
			if(booking!=null) {
				if (booking.getUser_id() == 0) {
					System.out.println(booking.getProperty());
					Property property = booking.getProperty();
					propertydto.add(ConverttoPropertyto(property));
					System.out.print(propertydto);
				}
			}
		}
		return propertydto;
//
//		return propertydto;
	}

	public String book(BookingDto bookingdto) {
		Property property = propertyRepo.findById(bookingdto.getPid()).get();
		System.out.println(property + "book");
		Booking booking = bookingRepo.findByProperty(property);
		booking.setStatus(false);
		booking.setUser_id(bookingdto.getUser_id());
		System.out.println("hello" + booking);
		booking.setCheckin(bookingdto.getCheckin());
		booking.setCheckout(bookingdto.getCheckout());
		bookingRepo.save(booking);

		return "booked successfull";
	}
	public static byte[] decompressBytes(byte[] data) {
	    Inflater inflater = new Inflater();
	    inflater.setInput(data);
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
	    byte[] buffer = new byte[1024];
	    try {
	        while (!inflater.finished()) {
	            int count = inflater.inflate(buffer);
	            outputStream.write(buffer, 0, count);
	        }
	        outputStream.close();
	    } catch (IOException ioe) {
	    } catch (DataFormatException e) {
	    }
	    return outputStream.toByteArray();
	}
	PropertyDto ConverttoPropertyto(Property property) {
		PropertyDto propertyDto = new PropertyDto();
		propertyDto.setPid(property.getPid());
		propertyDto.setName(property.getName());
		 propertyDto.setDescription(property.getDescription());
		 propertyDto.setFeatures(property.getFeatures());
	propertyDto.setLocation(property.getLocation());
	propertyDto.setOwner_id(property.getOwner_id());
//	propertyDto.setOwnername(userRepo.findById(property.getOwner_id()).get().getName());
	if(property.getImage()!=null) {
//		System.out.println(property.getImage());
		propertyDto.setReturnedImage(decompressBytes(property.getImage()));
//	System.out.println(propertyDto.getReturnedImage());
	}
	if(property.getImage2()!=null) {
		propertyDto.setReturnedImage2(decompressBytes(property.getImage2()));
	}
	if(property.getImage3()!=null) {
		propertyDto.setReturnedImage3(decompressBytes(property.getImage3()));
	}
	if(property.getImage1()!=null) {
		propertyDto.setReturnedImage1(decompressBytes(property.getImage1()));
	}
	propertyDto.setPrice(property.getPrice());
	propertyDto.setPhone(property.getPhone());
	propertyDto.setType(property.getType());
		return propertyDto;

	}

	public String approveproperty(int bid) {
		Booking booking = bookingRepo.findById(bid).get();
		booking.setStatus(true);
		bookingRepo.save(booking);

		return "approved";
	}

	public String disapproveproperty(int bid) {
		Booking booking = bookingRepo.findById(bid).get();
		booking.setStatus(false);
		booking.setUser_id(0);
		booking.setCheckin(null);
		booking.setCheckout(null);
		bookingRepo.save(booking);

		return "approved";
	}
	
	public List<PropertyDto> searchCheckin(Date Checkin){
		List<PropertyDto> propertydto = new ArrayList<>();
		bookingRepo.findByCheckins(Checkin).forEach(booking -> {
			
		
				Property property = booking.getProperty();
				propertydto.add(ConverttoPropertyto(property));
			
		});
		
		return propertydto;
	}
	public List<PropertyDto> searchCinCout(Date checkin,Date checkout){
		List<PropertyDto> propertydto = new ArrayList<>();
		bookingRepo.findByCheckinCheckout(checkin,checkout).forEach(booking -> {
			
		
				Property property = booking.getProperty();
				propertydto.add(ConverttoPropertyto(property));
			
		});
		
		return propertydto;
	}

}
