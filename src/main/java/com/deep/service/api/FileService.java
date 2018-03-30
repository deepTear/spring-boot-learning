package com.deep.service.api;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	public String fileUpload(MultipartFile file);
}
