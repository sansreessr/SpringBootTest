package com.stackroute.movieapp.onstartup;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:db.properties")
public class Initialize {

    private MovieRepository repository;

    @Autowired
    Environment env;

    public Initialize(MovieRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void usingValue(@Value("${id1}") int id,
                            @Value("${name1}") String name,
                            @Value("${lang1}") String lang,
                            @Value("${genre1}") String genre,
                            @Value("${date1}") String date,
                            @Value("${rate1}") Double rate,
                            @Value("${count1}") Long count) {

        repository.save(new Movie(id,name,lang,genre,date,rate,count));

    }

    @Autowired
    public void usingEnv(@Value("${id2}") int id,
                                 @Value("${name2}") String name,
                                 @Value("${lang2}") String lang,
                                 @Value("${genre2}") String genre,
                                 @Value("${date2") String date,
                                 @Value("${rate2}") Double rate,
                                 @Value("${count2}") Long count) {
        repository.save(new Movie(id,name,lang,genre,date,rate,count));
    }

    @Autowired
    public void usingPropSrcEnv() {
        repository.save(new Movie(Integer.parseInt(env.getProperty("id3")), env.getProperty("name3"), env.getProperty("lang3"),
                env.getProperty("genre3"), env.getProperty("date3"), Double.parseDouble(env.getProperty("rate3")), Long.parseLong(env.getProperty("count3"))));
    }
}
