package com.asraf.repositories;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends UserRepositoryCrud, UserRepositoryExtended {

}
