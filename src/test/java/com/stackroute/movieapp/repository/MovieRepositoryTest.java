package com.stackroute.movieapp.repository;

import com.stackroute.movieapp.domain.Movie;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;
    Movie movie;

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
    }

    @After
    public void tearDown() throws Exception {
        movieRepository.deleteAll();
    }

    @Test
    public void testSave() {
        movieRepository.save(movie);
        Movie fetchMovie = movieRepository.findById(movie.getMovieId()).get();
        Assert.assertEquals(5, fetchMovie.getMovieId());
    }
    @Test
    public void testSaveFailure() {
        Movie testMovie = new Movie(12,"Matrix","Hindi","Fiction","30 Sept 1998", 6.8, 4354345);
        movieRepository.save(movie);
        Movie fetchMovie = movieRepository.findById(movie.getMovieId()).get();
        Assert.assertNotSame(testMovie,movie);
    }

    @Test
    public void testFindAll() {
        Movie testMovie1 = new Movie(10,"Matrix","Hindi","Fiction","30 Sept 1998", 6.8, 4354345);
        Movie testMovie2 = new Movie(11,"Matrix","Hindi","Fiction","30 Sept 1998", 6.8, 4354345);
        movieRepository.save(testMovie1);
        movieRepository.save(testMovie2);
        List<Movie> list = movieRepository.findAll();
        Assert.assertEquals("Matrix",list.get(0).getMovieName());
    }

    @Test
    public void testDeleteById() {
        movieRepository.save(movie);
        movieRepository.deleteById(movie.getMovieId());
        Assert.assertEquals(movieRepository.existsById(movie.getMovieId()),false);
    }
    @Test
    public void testDeleteByIdFailure() {
        movieRepository.save(movie);
        movieRepository.deleteById(movie.getMovieId());
        Assert.assertNotEquals(movieRepository.existsById(movie.getMovieId()),true);
    }

    @Test
    public void findByName() {
        movieRepository.save(movie);
        Assert.assertEquals(movieRepository.findAll(),movieRepository.findByName("Matrix"));
    }
    @Test
    public void findByNameFailure() {
        movieRepository.save(movie);
        Assert.assertNotEquals(movieRepository.findAll(),movieRepository.findByName("Lion"));
    }

}