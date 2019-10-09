package com.stackroute.movieapp.service;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.exceptions.MovieAlreadyExistsException;
import com.stackroute.movieapp.exceptions.MovieNotFoundException;
import com.stackroute.movieapp.repository.MovieRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovieServiceImplTest {

    Movie movie;

    //Create a mock for MovieRepository
    @Mock
    MovieRepository movieRepository;

    //Inject the mocks as dependencies into MovieServiceImpl
    @InjectMocks
    MovieServiceImpl movieService;
    List<Movie> list= null;

    @Before
    public void setUp() throws Exception {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        movie = new Movie();
        movie.setMovieId(5);
        movie.setMovieName("Matrix");
        movie.setLanguage("English");
        movie.setGenres("Science Fiction");
        movie.setReleaseDate("30 Sept 1998");
        movie.setRating(7.9);
        movie.setVoteCount(4354345);
        list = new ArrayList();

        list.add(movie);
    }

    @Test
    public void saveMovie() throws MovieAlreadyExistsException {
        when(movieRepository.save((Movie)any())).thenReturn(movie);
        Movie savedMovie = movieService.saveMovie(movie);
        Assert.assertEquals(movie,savedMovie);

        //verify here verifies that movieRepository save method is only called once
        verify(movieRepository,times(1)).save(movie);
    }

    @Test(expected = MovieAlreadyExistsException.class)
    public void saveMovieFailure() throws MovieAlreadyExistsException {
        when(movieRepository.save((Movie)any())).thenReturn(null);
        Movie savedMovie = movieService.saveMovie(movie);
        System.out.println("savedMovie" + savedMovie);
    }

    @Test
    public void getAllMovies() throws MovieNotFoundException {
        movieRepository.save(movie);
        //stubbing the mock to return specific data
        when(movieRepository.findAll()).thenReturn(list);
        List<Movie> movieList = movieService.getAllMovies();
        Assert.assertEquals(list,movieList);
    }

    @Test(expected = MovieNotFoundException.class)
    public void getAllMoviesFailure() throws MovieNotFoundException {
        when(movieRepository.save((Movie)any())).thenReturn(null);
        List<Movie> movieList = movieService.getAllMovies();
        System.out.println("movieList" + movieList);
    }

    @Test
    public void deleteMovie() throws MovieNotFoundException {
        movieService.deleteMovie(5);
        verify(movieRepository,times(1)).deleteById(5);
    }


    @Test
    public void updateMovie() throws MovieNotFoundException {
        when(movieRepository.save(any())).thenReturn(movie);
        Movie updatedMovie = movieService.updateMovie(movie,5);
        verify(movieRepository,times(1)).deleteById(5);
        verify(movieRepository,times(1)).save(movie);
    }


    @Test
    public void movieByName() throws MovieNotFoundException {
        when(movieRepository.findByName(anyString())).thenReturn(list);
        List<Movie> ls = movieService.movieByName("Matrix");
        verify(movieRepository,times(1)).findByName("Matrix");
    }

}