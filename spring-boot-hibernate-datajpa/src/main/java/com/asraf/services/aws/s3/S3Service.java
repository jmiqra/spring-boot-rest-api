package com.asraf.services.aws.s3;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public abstract class S3Service {

	protected AmazonS3 s3Client;

	public S3Service() {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAJHJF74UFM3LM2WCA",
				"ezsZma0X8AbyVTFi6LWr2uOtZAJNQLBMahK+Xyem");
		s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds))
				.withRegion(Regions.US_EAST_1).build();
	}

}
