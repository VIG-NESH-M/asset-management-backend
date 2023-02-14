package com.staunch.tech.fileexceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class FileNotFoundExecption extends RuntimeException{
	
	private static final long serialVersionUID =1L;
	
	private String message;
	

}
