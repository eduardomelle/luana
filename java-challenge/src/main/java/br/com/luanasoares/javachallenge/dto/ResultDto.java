package br.com.luanasoares.javachallenge.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultDto implements Serializable {

    private Long id;

    private String original_title;

    private String title;

    private String overview;

}
