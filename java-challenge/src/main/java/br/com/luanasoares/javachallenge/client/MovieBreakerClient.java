package br.com.luanasoares.javachallenge.client;

import br.com.luanasoares.javachallenge.dto.NowPlayingResponseDto;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

@Headers("Authorization: {token}")
@FeignClient(value = "movieBreakerClient", url = "https://moviesdatabase.p.rapidapi.com/titles")
public interface MovieBreakerClient {

    @RequestLine("GET ?page={page}")
    NowPlayingResponseDto nowPlaying(@Param("token") String token, @Param("page") Integer page);

}
