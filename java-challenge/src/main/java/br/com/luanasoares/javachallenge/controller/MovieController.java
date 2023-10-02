package br.com.luanasoares.javachallenge.controller;

import br.com.luanasoares.javachallenge.dto.MovieDto;
import br.com.luanasoares.javachallenge.model.Movie;
import br.com.luanasoares.javachallenge.service.MovieService;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    public final MovieService movieService;

    @Autowired
    private HazelcastInstance hazelcastInstance;

    private ConcurrentMap<String,List<MovieDto>> retrieveMap() {
        return hazelcastInstance.getMap("map");
    }

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
        List<MovieDto> movies = null;
        if (this.retrieveMap().get("top-10") == null) {
            movies = this.movieService.findByTop();
            this.retrieveMap().put("top-10", movies);
        } else {
            movies = this.retrieveMap().get("top-10");
        }
        return ResponseEntity.ok(movies);
    }

}
