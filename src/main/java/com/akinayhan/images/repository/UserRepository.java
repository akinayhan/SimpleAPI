package com.akinayhan.images.repository;

import com.akinayhan.images.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}