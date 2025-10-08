package com.gnfs.GNFS;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MvcWebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		exposedDirectory("Incident-photos", registry);
	}


	private void exposedDirectory(String dirName, ResourceHandlerRegistry registry) {
		
		//get directory path
		Path uploadDir = Paths.get(dirName);
		
		//get absolute path
		String absolutePath = uploadDir.toFile().getAbsolutePath();
		
		String logicalPath = dirName.replace("..","") + "/**";
		//if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
		
		registry.addResourceHandler(logicalPath)
		.addResourceLocations("file:/" + absolutePath + "/");
	}
	
	
//	 @Override
//	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//	        // ✅ absolute folder where your images are stored
//	        String uploadPath = "C:/Users/solom/Documents/workspace-spring-tool-suite-4-4.27.0.RELEASE/GNFS/Incident-photos/";
//
//	        registry.addResourceHandler("/Incident-photos/**")
//	                .addResourceLocations("file:///" + uploadPath);
//	    }

}
