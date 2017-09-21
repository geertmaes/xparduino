package com.cegeka.xparduino.rest;

import com.cegeka.xparduino.domain.Color;
import com.cegeka.xparduino.domain.Train;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.List;

import static com.cegeka.xparduino.domain.Color.RED;
import static java.util.Arrays.asList;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public List<Train> trains(){
        return asList(new Train("yellow", RED, 0),
                new Train("blue", Color.BLUE, 0),
                new Train("white", RED, 1));
    }

}