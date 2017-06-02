package com.cegeka.xpdays.arduino.rest;

import com.cegeka.xpdays.arduino.command.impl.InfraredCommand;
import com.cegeka.xpdays.arduino.rest.domain.Train;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.util.List;

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
        return asList(new Train("yellow", InfraredCommand.Color.RED, 0),
                new Train("blue", InfraredCommand.Color.BLUE, 0),
                new Train("white", InfraredCommand.Color.RED, 1));
    }

}