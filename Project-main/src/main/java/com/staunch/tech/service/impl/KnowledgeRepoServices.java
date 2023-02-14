package com.staunch.tech.service.impl;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.annotation.PostConstruct;
import org.springframework.core.io.Resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.staunch.tech.config.UploadFileConfig;
import com.staunch.tech.dto.KRResponseDto;
import com.staunch.tech.dto.KnowledgeRepoDto;
import com.staunch.tech.entity.KnowledgeRepo;
import com.staunch.tech.fileexceptions.FileStorageException;
import com.staunch.tech.fileexceptions.FileNotFoundExecption;
import com.staunch.tech.repository.EmployeeRepository;
import com.staunch.tech.repository.KnowledgeRepoRepository;
import com.staunch.tech.service.IKnowledgeRepo;
import com.staunch.tech.utils.ConversionUtils;

@Service
public class KnowledgeRepoServices implements IKnowledgeRepo {

	private final Path dirLocation;

	@Autowired
	private KnowledgeRepoRepository knowledgeRepo;

	@Autowired
	private EmployeeRepository employeeRepository;

	public KnowledgeRepoServices(UploadFileConfig uploadFileConfig) {
		this.dirLocation = Paths.get(uploadFileConfig.getLocation()).toAbsolutePath().normalize();
	}

	@Override
	@PostConstruct
	public void init() {
		try {
			Files.createDirectories(this.dirLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create upload dir!");
		}

	}

	@Override
	public KRResponseDto savefile(KnowledgeRepoDto dto, MultipartFile file) {

		long uploadedTime = System.currentTimeMillis();
		var userId = employeeRepository.findById(dto.getUserId());
		try {

			String fileName = file.getOriginalFilename();
			Path dfile = this.dirLocation.resolve(fileName);
			Files.copy(file.getInputStream(), dfile, StandardCopyOption.REPLACE_EXISTING);
			knowledgeRepo.save(
					new KnowledgeRepo(dto.getId(), fileName, dto.getAsset_name(), userId.get(), uploadedTime));
			return ConversionUtils.convertFilenameToDto(dto, fileName, userId.get().getId(), uploadedTime);

		} catch (Exception e) {
			throw new FileStorageException("Could not upload file");
		}
	}

	@Override
	public Resource downloadfile(String fileName) {
		try {

			Path file = this.dirLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return  resource;
			} else {
				throw new FileNotFoundExecption("Could not find file");
			}
		} catch (MalformedURLException e) {
			throw new FileNotFoundExecption("Could not download file");
		}
	}

	@Override
	public String deletefile(int id) {
		
		var manual = knowledgeRepo.findById(id);
		if (manual.isEmpty()) {
			throw new FileNotFoundExecption("Repo not found");
		}
		knowledgeRepo.deleteById(id);
		return "Repo with id : " + id + " deleted successfully";
	}

	@Override
	public KRResponseDto searchmanual(int id) {
		var manual = knowledgeRepo.findById(id);
		
		if (manual.isEmpty()) {
			throw new FileNotFoundExecption("Manual not found");
		}
		return ConversionUtils.convertKnoledgeRepoToDto(manual.get());
	}

	@Override
	public List<KnowledgeRepo> getAllRepo() {
		var manualList = knowledgeRepo.findAll();
		if (manualList.isEmpty()) {
			throw new FileNotFoundExecption("Repo List Empty");
		}
		return manualList;
	}

}
