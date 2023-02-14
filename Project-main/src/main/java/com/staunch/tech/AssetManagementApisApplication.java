package com.staunch.tech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.staunch.tech.config.UploadFileConfig;

@SpringBootApplication
@EnableConfigurationProperties({
	UploadFileConfig.class
})
public class AssetManagementApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssetManagementApisApplication.class, args);
	}

}
