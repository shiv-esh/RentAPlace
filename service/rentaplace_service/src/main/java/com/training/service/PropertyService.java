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
		Property property = propertyRepo.findByPid(pid);
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

	// Save image file to local storage and return URL
	private String saveImageFile(org.springframework.web.multipart.MultipartFile file, int propertyId, int imageIndex)
			throws IOException {
		// Use absolute path - create uploads directory in project root
		String projectRoot = System.getProperty("user.dir");
		String uploadDir = projectRoot + java.io.File.separator + "uploads" + java.io.File.separator + "properties";
		java.io.File uploadDirFile = new java.io.File(uploadDir);

		// Create directory if it doesn't exist
		if (!uploadDirFile.exists()) {
			uploadDirFile.mkdirs();
		}

		// Generate unique filename
		String originalFilename = file.getOriginalFilename();
		String extension = originalFilename != null && originalFilename.contains(".")
				? originalFilename.substring(originalFilename.lastIndexOf("."))
				: ".jpg";
		String filename = "property_" + propertyId + "_img" + imageIndex + extension;

		// Save file
		java.io.File destFile = new java.io.File(uploadDirFile, filename);
		file.transferTo(destFile);

		// Return URL (relative path that frontend can use)
		return "/uploads/properties/" + filename;
	}

	public String addProperty(PropertyDto propertydto) throws IOException {
		Property property = new Property();
		property.setName(propertydto.getName());
		property.setLocation(propertydto.getLocation());
		property.setType(propertydto.getType());
		property.setFeatures(propertydto.getFeatures());
		property.setDescription(propertydto.getDescription());
		property.setPhone(propertydto.getPhone());
		property.setOwner_id(propertydto.getOwner_id());
		property.setPrice(propertydto.getPrice());

		// Save property first to get ID for file naming
		Property savedProperty = propertyRepo.save(property);

		// Handle image uploads - store files and save URLs
		if (propertydto.getImage() != null && !propertydto.getImage().isEmpty()) {
			String imageUrl = saveImageFile(propertydto.getImage(), savedProperty.getPid(), 0);
			savedProperty.setImageUrl(imageUrl);
		}
		if (propertydto.getImage1() != null && !propertydto.getImage1().isEmpty()) {
			String imageUrl = saveImageFile(propertydto.getImage1(), savedProperty.getPid(), 1);
			savedProperty.setImageUrl1(imageUrl);
		}
		if (propertydto.getImage2() != null && !propertydto.getImage2().isEmpty()) {
			String imageUrl = saveImageFile(propertydto.getImage2(), savedProperty.getPid(), 2);
			savedProperty.setImageUrl2(imageUrl);
		}
		if (propertydto.getImage3() != null && !propertydto.getImage3().isEmpty()) {
			String imageUrl = saveImageFile(propertydto.getImage3(), savedProperty.getPid(), 3);
			savedProperty.setImageUrl3(imageUrl);
		}

		propertyRepo.save(savedProperty);

		Booking booking = new Booking();
		booking.setProperty(savedProperty);
		booking.setStatus(false);
		bookingRepo.save(booking);
		return "Property is added successfully";
	}

	public List<Property> getMyProperties(int owner_id) {
		return propertyRepo.findByOwner_id(owner_id);
	}

	public List<PropertyDto> getnbProperty() {

		List<PropertyDto> propertydto = new ArrayList<>();
		// propertyRepo.findByStatus(false).forEach(property->{
		// propertydto.add(ConverttoPropertyto(property));
		//
		// });
		System.out.println("hello");
		// bookingRepo.findByStatus(false).forEach(booking -> {
		// System.out.println(booking);
		// System.out.println(booking.getUser_id());
		// if (booking.getUser_id() == 0) {
		// System.out.println(booking.getProperty());
		// Property property = booking.getProperty();
		// propertydto.add(ConverttoPropertyto(property));
		// }
		// });
		List<Booking> bookings = bookingRepo.findByStatus(false);
		System.out.print(bookings);
		for (Booking booking : bookings) {
			if (booking != null) {
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
		// return propertydto;
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

		// Set image URLs instead of binary data
		propertyDto.setImageUrl(property.getImageUrl());
		propertyDto.setImageUrl1(property.getImageUrl1());
		propertyDto.setImageUrl2(property.getImageUrl2());
		propertyDto.setImageUrl3(property.getImageUrl3());

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

	public List<PropertyDto> searchCheckin(Date Checkin) {
		List<PropertyDto> propertydto = new ArrayList<>();
		bookingRepo.findByCheckins(Checkin).forEach(booking -> {

			Property property = booking.getProperty();
			propertydto.add(ConverttoPropertyto(property));

		});

		return propertydto;
	}

	public List<PropertyDto> searchCinCout(Date checkin, Date checkout) {
		List<PropertyDto> propertydto = new ArrayList<>();
		bookingRepo.findByCheckinCheckout(checkin, checkout).forEach(booking -> {

			Property property = booking.getProperty();
			propertydto.add(ConverttoPropertyto(property));

		});

		return propertydto;
	}

}
