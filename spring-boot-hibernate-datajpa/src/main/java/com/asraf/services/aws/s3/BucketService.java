package com.asraf.services.aws.s3;

import java.util.List;

import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;

@Service
public class BucketService extends S3Service {

	private String region;

	public BucketService() {
		
	}

	public boolean isBucketExists(String bucketName) {
		return s3Client.doesBucketExistV2(bucketName);
	}

	public void createBucket(String bucketName) throws Exception {
		if (this.isBucketExists(bucketName)) {
			throw new Exception(bucketName + " - bucket already exists!");
		}
		CreateBucketRequest request = new CreateBucketRequest(bucketName, region);
		s3Client.createBucket(request);
	}

	public void deleteBucket(String bucketName) throws Exception {
		if (this.isBucketExists(bucketName)) {
			throw new Exception(bucketName + " - bucket already exists!");
		}
		s3Client.deleteBucket(bucketName);
	}

	public List<Bucket> getAllS3Buckets() {
		return s3Client.listBuckets();
	}

	public ObjectListing showBucketSummary(String bucketName) {
		ObjectListing objectListing = s3Client.listObjects(new ListObjectsRequest().withBucketName(bucketName));
		return objectListing;
	}

}
