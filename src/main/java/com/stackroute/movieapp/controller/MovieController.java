package com.stackroute.movieapp.controller;


import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieapp.exceptions.MovieNotFoundException;
import com.stackroute.movieapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/")
public class MovieController {
    @Autowired
    MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("movie")
    public ResponseEntity<?> saveMovie(@RequestBody Movie movie) throws MovieAlreadyExistsException {
        ResponseEntity responseEntity;
        try {
            movieService.saveMovie(movie);
            return new ResponseEntity<String>("Successfully added", HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("movie")
    public ResponseEntity<?> getAllMovies() throws MovieNotFoundException {
        ResponseEntity responseEntity;
        try {
            return new ResponseEntity<List<Movie>>(movieService.getAllMovies(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("movie/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable int id) throws MovieNotFoundException{
        ResponseEntity responseEntity;
        try {
            movieService.deleteMovie(id);
            return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("movie/{id}")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie, @PathVariable int id) throws MovieNotFoundException {
        ResponseEntity responseEntity;
        try {
            movieService.updateMovie(movie, id);
            return new ResponseEntity<String>("Successfully updated", HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("movie/movieName={movieName}")
    public ResponseEntity<?> movieByName(@PathVariable String movieName) throws MovieNotFoundException {
        ResponseEntity responseEntity;
        try {
            return new ResponseEntity<List<Movie>>(movieService.movieByName(movieName), HttpStatus.FOUND);
        }
        catch (Exception ex) {
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
