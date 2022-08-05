package dev.as.curso.udemy.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //interceptor
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
	
		String error = "Exception not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		String msg = e.getMessage();
		
		StandardError erroCriado = new StandardError(Instant.now(), status.value(), error, msg, request.getRequestURI());
		
		return ResponseEntity.status(status).body(erroCriado);
	}
	
}
