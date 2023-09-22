package br.com.luanasoares.javachallenge.config;

import br.com.luanasoares.javachallenge.client.MovieClient;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public MovieClient movieClient() {
        MovieClient movieClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(MovieClient.class))
                .logLevel(Logger.Level.FULL)
                .target(MovieClient.class, "https://api.themoviedb.org/3/movie/now_playing");

        return movieClient;
    }

}
