package com.staunch.tech.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.staunch.tech.dto.KRResponseDto;
import com.staunch.tech.dto.KnowledgeRepoDto;

public interface IKnowledgeRepo {
	
	void init();
	
	KRResponseDto savefile(KnowledgeRepoDto dto, MultipartFile file);
	
	Resource downloadfile(String fileName);
	
	String deletefile(int id);
	
	KRResponseDto searchmanual(int id);

}
