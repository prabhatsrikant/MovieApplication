package com.ril.streaming.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ril.streaming.model.User;

public interface UserMovieRepository extends MongoRepository<User, String>{
		
	User findByUsername(String username);

}
