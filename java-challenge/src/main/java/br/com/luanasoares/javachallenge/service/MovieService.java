package br.com.luanasoares.javachallenge.service;

import br.com.luanasoares.javachallenge.model.Movie;

import java.util.List;

public interface MovieService {

    Integer initialLoad();

    List<Movie> findAll();

}
