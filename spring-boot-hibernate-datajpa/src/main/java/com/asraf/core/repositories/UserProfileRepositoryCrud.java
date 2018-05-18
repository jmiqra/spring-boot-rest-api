package com.asraf.core.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.asraf.core.entities.UserProfile;

@Transactional
public interface UserProfileRepositoryCrud extends PagingAndSortingRepository<UserProfile, Long> {

}
