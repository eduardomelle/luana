package br.com.luanasoares.javachallenge.service.impl;

import br.com.luanasoares.javachallenge.client.MovieClient;
import br.com.luanasoares.javachallenge.dto.NowPlayingResponseDto;
import br.com.luanasoares.javachallenge.model.Movie;
import br.com.luanasoares.javachallenge.repository.MovieRepository;
import br.com.luanasoares.javachallenge.service.MovieService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Value("${token}")
    private String token;

    private final MovieClient movieClient;

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieClient movieClient, MovieRepository movieRepository) {
        this.movieClient = movieClient;
        this.movieRepository = movieRepository;
    }

    @Override
    public Integer initialLoad() {
        NowPlayingResponseDto nowPlayingResponseDto  = this.movieClient.nowPlaying(this.token, 1);
        for (int page = 1; page <= nowPlayingResponseDto.getTotal_pages(); page++) {
            nowPlayingResponseDto = this.movieClient.nowPlaying(this.token, page);
            nowPlayingResponseDto.getResults().forEach(result -> {
                Movie movie = new Movie();
                movie.setOverview(result.getOverview());
                movie.setOriginalTitle(result.getOriginal_title());
                movie.setTitle(result.getTitle());
                this.movieRepository.save(movie);
            });
        }

        return this.movieRepository.findAll().size();
    }

    @Override
    public List<Movie> findAll() {
        return this.movieRepository.findAll();
    }

}
