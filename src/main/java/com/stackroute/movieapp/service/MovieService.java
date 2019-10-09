package com.stackroute.movieapp.service;


import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieapp.exceptions.MovieNotFoundException;

import java.util.List;

public interface MovieService {

    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException;

    public List<Movie> getAllMovies() throws MovieNotFoundException;

    public void deleteMovie(int movieId) throws MovieNotFoundException;

    public Movie updateMovie(Movie movie, int id) throws MovieNotFoundException;

    public List<Movie> movieByName(String movieName) throws MovieNotFoundException;
}
