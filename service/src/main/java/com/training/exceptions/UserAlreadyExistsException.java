package com.training.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value= HttpStatus.BAD_REQUEST,reason="User Already Exist")
public class UserAlreadyExistsException  extends RuntimeException{

}
