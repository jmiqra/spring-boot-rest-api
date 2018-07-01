package com.asraf.services.aws.s3;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsRequest.KeyVersion;
import com.amazonaws.services.s3.model.DeleteObjectsResult;
import com.amazonaws.services.s3.model.Grant;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectResult;

@Service
public class S3ObjectService extends S3Service {

	protected final String BUCKET_NAME;

	private final String BUCKET_URL_PREFIX;

	public S3ObjectService() {
		BUCKET_NAME = "ratul-test";
		BUCKET_URL_PREFIX = "https://" + BUCKET_NAME + ".s3.amazonaws.com/";
	}

	public PutObjectResult put(String key, File file) {
		return s3Client.putObject(BUCKET_NAME, key, file);
	}

	public PutObjectResult put(String key, InputStream stream) {
		return s3Client.putObject(BUCKET_NAME, key, stream, null);
	}

	public PutObjectResult put(String key, String content) {
		return s3Client.putObject(BUCKET_NAME, key, content);
	}

	public void setAcl(String key, CannedAccessControlList cannedAccessControlList) {
		s3Client.setObjectAcl(BUCKET_NAME, key, cannedAccessControlList);
	}

	public void makePublic(String key) {
		this.setAcl(key, CannedAccessControlList.PublicRead);
	}

	public boolean isPublic(String key) {
		AccessControlList acl = s3Client.getObjectAcl(BUCKET_NAME, key);
		for (Iterator<Grant> iterator = acl.getGrantsAsList().iterator(); iterator.hasNext();) {
			Grant grant = iterator.next();
			if (grant.getPermission().equals(Permission.Read)
					&& grant.getGrantee().getIdentifier().equals("http://acs.amazonaws.com/groups/global/AllUsers"))
				return true;
		}
		return false;
	}

	public boolean isExists(String objectName) {
		return s3Client.doesObjectExist(BUCKET_NAME, objectName);
	}

	public String getBucketUrl(String key) {
		return BUCKET_URL_PREFIX + key;
	}

	public String getKey(String bucketUrl) {
		return bucketUrl.substring(0, BUCKET_URL_PREFIX.length());
	}

	public ObjectListing getAllObjects(String prefix) {
		ListObjectsRequest request = new ListObjectsRequest();
		request.setBucketName(BUCKET_NAME);
		request.setPrefix(prefix);

		ObjectListing objectListing = s3Client.listObjects(request);
		return objectListing;
	}

	public URL getPreSignedUrl(String key, HttpMethod httpMethod, int expirationInMinute) {
		Date expiration = new Date();
		long expTimeMillis = expiration.getTime();
		expTimeMillis += 1000 * 60 * expirationInMinute;
		expiration.setTime(expTimeMillis);
		return s3Client.generatePresignedUrl(BUCKET_NAME, key, expiration, httpMethod);
	}

	public DeleteObjectsResult deleteAllObjects(String prefix) {
		ObjectListing objectListing = this.getAllObjects(prefix);
		List<KeyVersion> keyVersions = objectListing.getObjectSummaries().stream().map(m -> new KeyVersion(m.getKey()))
				.collect(Collectors.toList());
		DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(BUCKET_NAME).withKeys(keyVersions);
		return s3Client.deleteObjects(deleteObjectsRequest);
	}

	public void delete(String key) {
		s3Client.deleteObject(BUCKET_NAME, key);
	}

}
