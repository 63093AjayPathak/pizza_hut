package com.app.exception_handler;

import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.dto.ApiResponse;

@ControllerAdvice // mandatory class level annotation to tell SC that following is centralized
					// exception handler class
//which will provide common advice to all controllers in the web app
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//handle the presentation logic validation errors detected due to @Valid annotation
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		System.out.println("In global Exception handler: Validation error " + ex);
		return new ResponseEntity<Object>(ex.getBindingResult().getFieldErrors().stream()
				.collect(Collectors.toMap(f -> f.getField(), f -> f.getDefaultMessage())), HttpStatus.BAD_REQUEST);
	}

//add a common method for exception handling for all unchecked exceptions
	@ExceptionHandler(RuntimeException.class) // MANDATORY method level annotation:
//                          to tell SC that this method  will handle specified exception

	public ResponseEntity<?> handleRuntimeException(RuntimeException e) {

		return ResponseEntity.internalServerError().body(new ApiResponse(e.getMessage()));

	}

}
