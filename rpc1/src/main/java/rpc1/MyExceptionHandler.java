package rpc1;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class MyExceptionHandler {

	@ExceptionHandler(value= {IllegalArgumentException.class})
	public ResponseEntity<Object> handleIllegalArgumentException(
			IllegalArgumentException ex,
			WebRequest request){
		return handleExceptionInternal(ex, ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
}
