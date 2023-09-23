package br.com.luanasoares.javachallenge.controller;

import br.com.luanasoares.javachallenge.dto.MovieDto;
import br.com.luanasoares.javachallenge.model.Movie;
import br.com.luanasoares.javachallenge.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    public final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/initial-load")
    public ResponseEntity<String> initialLoad() {
        return ResponseEntity.ok(this.movieService.initialLoad() + " filmes carregados com sucesso na base de dados.");
    }

    @GetMapping
    public ResponseEntity<List<Movie>> findAll() {
        return ResponseEntity.ok(this.movieService.findAll());
    }

    @GetMapping("/top-10")
    public ResponseEntity<List<MovieDto>> findByTop() {
        return ResponseEntity.ok(this.movieService.findByTop());
    }

}
