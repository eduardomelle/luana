package br.com.luanasoares.javachallenge.client;

import br.com.luanasoares.javachallenge.dto.NowPlayingResponseDto;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@Headers("Authorization: {token}")
@FeignClient(value = "movieClient", url = "https://api.themoviedb.org/3/movie/now_playing")
public interface MovieClient {

    @RequestLine("GET ?page={page}")
    NowPlayingResponseDto nowPlaying(@Param("token") String token, @Param("page") Integer page);

}
