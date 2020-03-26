package com.ril.streaming.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT , reason = "Movie already exists")
public class MovieAlreadyExistException extends Exception{

}
