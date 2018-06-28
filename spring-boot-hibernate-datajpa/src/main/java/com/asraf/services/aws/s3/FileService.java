package com.asraf.services.aws.s3;

import java.io.File;
import java.io.InputStream;

import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

@Service
public class FileService extends ObjectService {

	public FileService() {
	}

	public void download(String key, File outputFile) {
		s3Client.getObject(new GetObjectRequest(BUCKET_NAME, key), outputFile);
	}

	public InputStream download(String key) {
		S3Object s3Object = s3Client.getObject(BUCKET_NAME, key);
		return s3Object.getObjectContent();
	}

}
