package br.com.luanasoares.javachallenge.client;

import br.com.luanasoares.javachallenge.dto.NowPlayingResponseDto;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Headers("Authorization: {token}")
public interface MovieClient {

    @RequestLine("GET ?page={page}")
    NowPlayingResponseDto nowPlaying(@Param("token") String token, @Param("page") Integer page);

}
