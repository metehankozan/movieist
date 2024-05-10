package com.metehankozan.movieist.exception;

public class MovieNotFoundException extends RuntimeException{

    public MovieNotFoundException(String imdbId) {
        super("Movie not found with imdbId : " + imdbId);
    }
}
