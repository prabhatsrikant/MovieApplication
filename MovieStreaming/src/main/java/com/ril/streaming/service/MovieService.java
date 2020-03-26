package com.ril.streaming.service;

import java.util.List;

import com.ril.streaming.exception.MovieAlreadyExistException;
import com.ril.streaming.exception.MovieNotFoundException;
import com.ril.streaming.exception.UserAlreadyExistsException;
import com.ril.streaming.model.Movie;
import com.ril.streaming.model.User;

public interface MovieService {
	
	void saveMovie(Movie emp) throws MovieAlreadyExistException;
	Movie getMovie(int movieId) throws MovieNotFoundException;
	Movie updateMovie(Movie emp) throws MovieNotFoundException;
	public List<Movie> getAllMovie();
	
	
	User saveUserMovieToCart(Movie movie , String username) throws MovieAlreadyExistException;
	Movie deleteUserMovieFromCart(String username, int movieId) throws MovieNotFoundException;
	List<Movie> getAllUserMovieFromCart(String username) throws Exception;
	
	User registerUser(User user) throws UserAlreadyExistsException;

}
