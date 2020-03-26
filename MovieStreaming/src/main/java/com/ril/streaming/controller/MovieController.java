package com.ril.streaming.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ril.streaming.exception.MovieAlreadyExistException;
import com.ril.streaming.exception.MovieNotFoundException;
import com.ril.streaming.exception.UserAlreadyExistsException;
import com.ril.streaming.model.Movie;
import com.ril.streaming.model.User;
import com.ril.streaming.service.MovieServiceImpl;

@CrossOrigin("*")
@RestController
@RequestMapping("/movie")
public class MovieController {

	MovieServiceImpl movieService;
	ResponseEntity responseEntity;

	@Autowired
	public void movieController(MovieServiceImpl movieService) {
		this.movieService = movieService;
	}

	@PostMapping("/save")
	public ResponseEntity<?> savemovie(@RequestBody Movie movie) throws MovieAlreadyExistException {
		try {
			movieService.saveMovie(movie);
			responseEntity = new ResponseEntity(movie, HttpStatus.CREATED);
		} catch (MovieAlreadyExistException e) {
			throw new MovieAlreadyExistException();
		} catch (Exception e) {
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@PostMapping("/update")
	public ResponseEntity<?> updatemovie(@RequestBody Movie movie) throws MovieNotFoundException {
		try {

			movieService.updateMovie(movie);
			responseEntity = new ResponseEntity(movie, HttpStatus.OK);
		} catch (MovieNotFoundException e) {
			throw new MovieNotFoundException();
		} catch (Exception e) {
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@GetMapping("/{movieId}")
	public ResponseEntity<?> getmovieByName(@PathVariable("movieId") int movieId) throws MovieNotFoundException {
		try {
			Movie movie = movieService.getMovie(movieId);
			responseEntity = new ResponseEntity(movie, HttpStatus.OK);
		}
		catch (MovieNotFoundException e) {
			throw new MovieNotFoundException();
			}
		catch (Exception e) {
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@GetMapping("/getAll")
	public ResponseEntity<?> getAllmovie() {
		try {
			responseEntity = new ResponseEntity(movieService.getAllMovie(), HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;

	}
	
	
	@GetMapping("/{username}/getAll")
	public ResponseEntity<?> getAllUserMovieFromCart(@PathVariable String username) {
		try {
			responseEntity = new ResponseEntity(movieService.getAllUserMovieFromCart(username), HttpStatus.OK);
		} catch (Exception e) {
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;

	}
	
	@PostMapping("/{username}/save")
	public ResponseEntity<?> saveUserMovieToCart(@RequestBody Movie movie, @PathVariable String username) throws MovieAlreadyExistException {
		try {
			movieService.saveUserMovieToCart(movie, username);
			responseEntity = new ResponseEntity(movie, HttpStatus.CREATED);
		} catch (MovieAlreadyExistException e) {
			throw new MovieAlreadyExistException();
		} catch (Exception e) {
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) throws UserAlreadyExistsException {
		try {
			movieService.registerUser(user);
			responseEntity = new ResponseEntity(user, HttpStatus.CREATED);
		} catch (UserAlreadyExistsException e) {
			throw new UserAlreadyExistsException();
		} catch (Exception e) {
			responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

}
