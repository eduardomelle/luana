package br.com.luanasoares.javachallenge.service;

import br.com.luanasoares.javachallenge.dto.MovieDto;
import br.com.luanasoares.javachallenge.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {

    Integer initialLoad();

    List<Movie> findAll();

    List<MovieDto> findByTop();

}
