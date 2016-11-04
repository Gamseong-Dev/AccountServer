package com.highluck.gamseong.common.library;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUpload {

	 public String upload(MultipartFile sourceFile, String path) throws IOException {

	        String sourceFileName = sourceFile.getOriginalFilename();
	        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
	        String fileUrl;
	        String filePath;

	        File destinationFile;
	        String destinationFileName;
	        do {
	            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
	            fileUrl = "upload/" + destinationFileName;
	            filePath = path;
	            destinationFile = new File(filePath + fileUrl);
	        } while (destinationFile.exists());
	        destinationFile.getParentFile().mkdirs();
	        sourceFile.transferTo(destinationFile);
	      
	        return fileUrl;
	    }
}
