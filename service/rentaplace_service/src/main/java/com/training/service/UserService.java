package com.training.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.training.Dto.SignUpRequest;
import com.training.Dto.UserDto;
import com.training.exceptions.UserAlreadyExistsException;
import com.training.model.User;
import com.training.repo.UserRepo;

@Service
public class UserService implements UserDetailsService{
	   @Autowired
	    private UserRepo userRepos;
	    @Autowired
	    private BCryptPasswordEncoder passwordEncoder;


public List<User> getAllUsers(){
	return userRepos.findAll();
}

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	

    User user = userRepos.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

  return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
}

public User getUser(String username) {
	 return userRepos.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
}

public UserDto signup(SignUpRequest signUpRequest) {
    if (userRepos.findByUsername(signUpRequest.getUsername()).isPresent()) {
        throw new UserAlreadyExistsException();
    }
    User user = new User();

    user.setEmail(signUpRequest.getEmail());
    user.setUsername(signUpRequest.getUsername());
    user.setName(signUpRequest.getName());
    user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
    user.setPhone(signUpRequest.getPhone());
    user.setRole(signUpRequest.getRole());
    userRepos.save(user);
    return convertUserToUserDto(user);

}
public UserDto convertUserToUserDto(User user) {
    UserDto userDto = new UserDto(user.getUser_id(), user.getUsername());
    return userDto;
}

}
