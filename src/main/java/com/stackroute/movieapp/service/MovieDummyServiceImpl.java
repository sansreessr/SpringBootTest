package com.stackroute.movieapp.service;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieapp.exceptions.MovieNotFoundException;
import com.stackroute.movieapp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieDummyServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieDummyServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistsException {
        if(movieRepository.existsById(movie.getMovieId())) {
            throw new MovieAlreadyExistsException("Movie already exists");
        }
        Movie savedMovie = movieRepository.save(movie);
        if(savedMovie == null) {
            throw new MovieAlreadyExistsException("Movie already exists");
        }
        return savedMovie;
    }

    @Override
    public List<Movie> getAllMovies() throws MovieNotFoundException {
        List<Movie> allMovies = movieRepository.findAll();
        if(allMovies.isEmpty()) {
            throw new MovieNotFoundException("No movie found");
        }
        return null;
    }

    @Override
    public void deleteMovie(int movieId) throws MovieNotFoundException{
        if(!movieRepository.existsById(movieId)) {
            throw new MovieNotFoundException("Movie not found");
        }
        movieRepository.deleteById(movieId);
    }

    @Override
    public Movie updateMovie(Movie movie, int id) throws MovieNotFoundException {
        if(!movieRepository.existsById(id)) {
            throw new MovieNotFoundException("Movie not found");
        }
        movieRepository.deleteById(id);
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> movieByName(String movieName) throws MovieNotFoundException {
        List<Movie> search = movieRepository.findByName(movieName);
        if(search.isEmpty()) {
            throw new MovieNotFoundException("Movie not found");
        }
        return search;
    }
}
