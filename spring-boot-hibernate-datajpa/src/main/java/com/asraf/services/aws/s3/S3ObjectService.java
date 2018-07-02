package com.asraf.services.aws.s3;

import java.net.URL;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectsResult;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectResult;

public interface S3ObjectService {

	PutObjectResult put(String key, String content);

	void setAcl(String key, CannedAccessControlList cannedAccessControlList);

	void makePublic(String key);

	boolean isPublic(String key);

	boolean isExists(String objectName);

	String getBucketUrl(String key);

	String getKey(String bucketUrl);

	ObjectListing getAllObjects(String prefix);

	URL getPreSignedUrl(String key, HttpMethod httpMethod, int expirationInMinute);

	DeleteObjectsResult deleteAllObjects(String prefix);

	void delete(String key);

}