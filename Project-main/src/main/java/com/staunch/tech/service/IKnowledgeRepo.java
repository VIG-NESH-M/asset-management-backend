package com.staunch.tech.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.staunch.tech.dto.KRResponseDto;
import com.staunch.tech.dto.KnowledgeRepoDto;
import com.staunch.tech.entity.KnowledgeRepo;

public interface IKnowledgeRepo {
	
	void init();
	
	KRResponseDto savefile(KnowledgeRepoDto dto, MultipartFile file);
	
	Resource downloadfile(String fileName);
	
	String deletefile(int id);
	
	KRResponseDto searchmanual(int id);
	
	List<KnowledgeRepo> getAllRepo();

}
