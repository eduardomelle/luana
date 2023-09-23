package br.com.luanasoares.javachallenge.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MovieDto implements Serializable {

    private String title;

    private Integer star;

}
