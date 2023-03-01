package com.staunch.tech.dto;

import java.io.File;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetailsWO {

	 private String recipient;
	    private String recipientName;
	    private String msgBody;
	    private String subject;
}
