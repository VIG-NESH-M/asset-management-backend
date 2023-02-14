package com.staunch.tech.fileexceptions;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.staunch.tech.dto.FileResponseErrDto;



@RestControllerAdvice
public class FileExceptionAdvice extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<Object> handleFileNotFoundException(FileNotFoundException exc) {
	    
		List<String> details = new ArrayList<String>();
        details.add(exc.getMessage());
        
        FileResponseErrDto err = new FileResponseErrDto(LocalDateTime.now(), "File Not Found" ,details);
	
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<Object> handleMaxSizeException(MaxUploadSizeExceededException exc) {
	    
		List<String> details = new ArrayList<String>();
        details.add(exc.getMessage());
		
        FileResponseErrDto err = new FileResponseErrDto(LocalDateTime.now(), "File Size Exceeded" ,details);
	
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(err);
	}

}
