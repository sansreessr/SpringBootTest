package com.stackroute.movieapp.onstartup;

import com.stackroute.movieapp.domain.Movie;
import com.stackroute.movieapp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PreFillData2 implements CommandLineRunner {

    private InitializeConfigProp configProp;

    @Autowired
    public PreFillData2(InitializeConfigProp configProp) {
        this.configProp = configProp;
    }

    @Override
    public void run(String... args) throws Exception {
        configProp.usingConfigProp();
    }
}