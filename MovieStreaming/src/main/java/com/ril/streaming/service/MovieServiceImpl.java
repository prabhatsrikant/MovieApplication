package com.ril.streaming.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ril.streaming.exception.MovieAlreadyExistException;
import com.ril.streaming.exception.MovieNotFoundException;
import com.ril.streaming.exception.UserAlreadyExistsException;
import com.ril.streaming.model.Movie;
import com.ril.streaming.model.User;
import com.ril.streaming.repository.MovieRepository;
import com.ril.streaming.repository.UserMovieRepository;
import com.ril.streaming.service.MovieService;


@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	MovieRepository movieRespository;
	
	@Autowired
	UserMovieRepository userMovieRepository;
	
	
	public void saveMovie(Movie movie) throws MovieAlreadyExistException {
		movieRespository.save(movie);
	}

	public Movie getMovie(int movieId) throws MovieNotFoundException {
		Movie movie= movieRespository.findByMovieId(movieId);
		if(movie == null) {
			throw new MovieNotFoundException();
		}
		return movie;
	}

	public Movie updateMovie(Movie movie) throws MovieNotFoundException {
		Movie temp;
		temp = movieRespository.findByMovieId(movie.getMovieId());
		if(temp == null) {
			throw new MovieNotFoundException();
		}
		return movieRespository.save(movie);
	}
	
	public List<Movie> getAllMovie() {
		return movieRespository.findAll();
	}

	public User saveUserMovieToCart(Movie movie, String username) throws MovieAlreadyExistException {
		User fetchUser = userMovieRepository.findByUsername(username);
		System.out.println(fetchUser);
		List<Movie> fetchMovies = fetchUser.getMovies();

		if (fetchMovies != null) {
			for (Movie movieObj : fetchMovies) {
				if (movieObj.getMovieId() == (movie.getMovieId())) {
					throw new MovieAlreadyExistException();
				}
			}
			fetchMovies.add(movie);
			fetchUser.setMovies(fetchMovies);
		}
			else {
				fetchMovies = new ArrayList();
				fetchMovies.add(movie);
				fetchUser.setMovies(fetchMovies);
		}
			
			return fetchUser;
			
	}

	public Movie deleteUserMovieFromCart(String username, int movieId) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Movie> getAllUserMovieFromCart(String username) throws Exception {
		User fetchUser = userMovieRepository.findByUsername(username);
		return fetchUser.getMovies();
	}

	public User registerUser(User user) throws UserAlreadyExistsException {

		User fetchUserObj = userMovieRepository.findByUsername(user.getUsername());
		if(fetchUserObj != null) {
			throw new UserAlreadyExistsException();
		}else {
			userMovieRepository.save(user);
		}
		return user;
	}

		
}
