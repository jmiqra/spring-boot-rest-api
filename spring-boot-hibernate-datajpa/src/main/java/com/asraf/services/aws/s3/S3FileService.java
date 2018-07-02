package com.asraf.services.aws.s3;

import java.io.File;
import java.io.InputStream;

import com.amazonaws.services.s3.model.PutObjectResult;

public interface S3FileService extends S3ObjectService {

	PutObjectResult put(String key, File file);

	PutObjectResult put(String key, InputStream stream);

	void download(String key, File outputFile);

	InputStream download(String key);

}