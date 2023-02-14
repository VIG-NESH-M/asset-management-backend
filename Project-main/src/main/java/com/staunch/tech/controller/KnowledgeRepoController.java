package com.staunch.tech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.staunch.tech.dto.ApiResponseDto;
import com.staunch.tech.dto.AssetDto;
import com.staunch.tech.dto.KRResponseDto;
import com.staunch.tech.dto.KnowledgeRepoDto;
import com.staunch.tech.service.impl.KnowledgeRepoServices;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/knowledgerepo")
public class KnowledgeRepoController {

	@Autowired
	public KnowledgeRepoServices knowledgeRepoServices;
	
	@PostMapping("/uploadfile")
	public ResponseEntity<ApiResponseDto> uploadfile(@RequestParam("dto") String dto, @RequestParam("file") MultipartFile file) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		final KnowledgeRepoDto modelDTO;
		modelDTO = mapper.readValue(dto, KnowledgeRepoDto.class);
		var response = new ApiResponseDto("1200", "Success", knowledgeRepoServices.savefile(modelDTO, file));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
       
        Resource resource = knowledgeRepoServices.downloadfile(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseDto> serachFile(@PathVariable int id)
	{
		var response = new ApiResponseDto("1200", "Success", knowledgeRepoServices.searchmanual(id));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponseDto> deleteFile(@PathVariable int id)
	{
		var response = new ApiResponseDto("1200", "Success", knowledgeRepoServices.deletefile(id));
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/list")
	public ResponseEntity<ApiResponseDto> listRepos() {
		var response = new ApiResponseDto("1200", "Success", knowledgeRepoServices.getAllRepo());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
