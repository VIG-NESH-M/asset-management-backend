package com.staunch.tech.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.staunch.tech.dto.ResourceDto;
import com.staunch.tech.entity.Employee;
import com.staunch.tech.entity.Resource;
import com.staunch.tech.exception.AssetManagementException;
import com.staunch.tech.repository.EmployeeRepository;
import com.staunch.tech.repository.ResourceRepository;
import com.staunch.tech.service.IResourceService;
import com.staunch.tech.utils.ConversionUtils;
import com.staunch.tech.utils.ValidationUtils;

@Service
public class ResourceService implements IResourceService {
	@Autowired
	private ValidationUtils validationUtils;

	static Logger logger = LoggerFactory.getLogger(ResourceService.class);

	@Autowired
	private ResourceRepository repository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Resource registerResource(ResourceDto resourceDto) {
		validationUtils.validate(resourceDto);
		var userOpt = employeeRepository.findById(resourceDto.getUserId());
		if (userOpt.isEmpty()) {
			throw new AssetManagementException("User Id is Invalid!");
		}

		try {
			var resource = ConversionUtils.convertDtoToNewEntity(resourceDto, userOpt.get().getId());
			return repository.save(resource);
		} catch (DataIntegrityViolationException e) {
			throw new AssetManagementException("SQL Error " + e.getRootCause().getMessage());
		}
	}

	@Override
	public Resource updateResource(int id, ResourceDto resourceDto) {
		var repo = repository.findById(id);
		Resource resource = repo.get();
		resource.setResourceName(resourceDto.getResourceName());
		resource.setResourceType(resourceDto.getResourceType());
		resource.setEndDate(resourceDto.getEndDate());
		return repository.save(resource);
	}

	@Override
	public ResourceDto getResourceById(int id) {
		var useroption = repository.findById(id);
		if (useroption.isEmpty()) {
			throw new AssetManagementException("Resource id is Invalid");
		}
		return ConversionUtils.convertEntityToDto(useroption.get());
	}

	@Override
	public List<ResourceDto> getAllResource() {

		var resourceList = repository.findAll();
		if (resourceList.isEmpty()) {
			throw new AssetManagementException("Employee List is Empty");
		}
		var resourceDtoList = new ArrayList<ResourceDto>();
		for (Resource res : resourceList) {
			resourceDtoList.add(ConversionUtils.convertEntityToDto(res));
		}
		return resourceDtoList;
	}

	@Override
	public String deleteById(int id) {
		var assetOpt = repository.findById(id);
		if (assetOpt.isEmpty()) {
			throw new AssetManagementException("Asset id is Invalid");
		}
		repository.deleteById(id);
		return "resource with id : " + id + " deleted successfully";
	}
}
