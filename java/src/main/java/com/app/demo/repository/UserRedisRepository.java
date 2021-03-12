package com.app.demo.repository;

import com.app.demo.model.UserRedis;
import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRedisRepository extends CrudRepository<UserRedis, Integer> {

}

