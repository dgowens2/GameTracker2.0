package com.tiy;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by DTG2 on 09/15/16.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    User findFirstByName(String name);
}
