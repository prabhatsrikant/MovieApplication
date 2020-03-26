package com.ril.streaming.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ril.streaming.model.Movie;

public interface MovieRepository extends MongoRepository<Movie, String>{
	
	public Movie findByMovieId(int movieId);
	
}
