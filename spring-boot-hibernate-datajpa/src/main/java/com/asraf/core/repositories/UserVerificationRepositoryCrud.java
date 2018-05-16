package com.asraf.core.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.asraf.core.entities.UserVerification;

@Transactional
public interface UserVerificationRepositoryCrud extends PagingAndSortingRepository<UserVerification, Long> {

}
