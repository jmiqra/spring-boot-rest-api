package com.asraf.services.aws.s3.persistence;

import java.io.File;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.asraf.services.aws.s3.S3FileService;

@Service
public class S3FileServiceImpl extends S3ObjectServiceImpl implements S3FileService {

	@Autowired
	public S3FileServiceImpl(AmazonS3 s3Client, @Value("${aws.s3.bucket}") String bucketName) {
		super(s3Client, bucketName);
	}

	public PutObjectResult put(String key, File file) {
		return s3Client.putObject(BUCKET_NAME, key, file);
	}

	public PutObjectResult put(String key, InputStream stream) {
		return s3Client.putObject(BUCKET_NAME, key, stream, null);
	}

	public void download(String key, File outputFile) {
		s3Client.getObject(new GetObjectRequest(BUCKET_NAME, key), outputFile);
	}

	public InputStream download(String key) {
		S3Object s3Object = s3Client.getObject(BUCKET_NAME, key);
		return s3Object.getObjectContent();
	}

}
