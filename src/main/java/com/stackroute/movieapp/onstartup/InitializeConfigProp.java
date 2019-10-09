package com.stackroute.movieapp.onstartup;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.repository.MovieRepository;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:global.properties")
@ConfigurationProperties
public class InitializeConfigProp {

    private MovieRepository repository;

    public InitializeConfigProp(MovieRepository repository) {
        this.repository = repository;
    }

    private int id4;
    private String name4;
    private String lang4;
    private String genre4;
    private String date4;
    private Double rate4;
    private Long count4;

    public int getId4() {
        return id4;
    }

    public void setId4(int id4) {
        this.id4 = id4;
    }

    public String getName4() {
        return name4;
    }

    public void setName4(String name4) {
        this.name4 = name4;
    }

    public String getLang4() {
        return lang4;
    }

    public void setLang4(String lang4) {
        this.lang4 = lang4;
    }

    public String getGenre4() {
        return genre4;
    }

    public void setGenre4(String genre4) {
        this.genre4 = genre4;
    }

    public String getDate4() {
        return date4;
    }

    public void setDate4(String date4) {
        this.date4 = date4;
    }

    public Double getRate4() {
        return rate4;
    }

    public void setRate4(Double rate4) {
        this.rate4 = rate4;
    }

    public Long getCount4() {
        return count4;
    }

    public void setCount4(Long count4) {
        this.count4 = count4;
    }

    public void usingConfigProp() {
        repository.save(new Movie(getId4(),getName4(),getLang4(),getGenre4(),getDate4(),getRate4(),getCount4()));
    }

}
