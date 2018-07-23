package com.udacity.gradle.builditbigger.backend;

import com.example.javajoke.JavaJoke;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    JavaJoke joke=new JavaJoke();

    @ApiMethod(name = "gceJokes")
    public Joke gceJokes(){
        Joke res=new Joke();
        String jokeJava= joke.getJoke();
        res.setJokes(jokeJava);
        return res;
    }
}
