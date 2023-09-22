package br.com.luanasoares.javachallenge.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class NowPlayingResponseDto implements Serializable {

    private List<ResultDto> results;

    private Integer total_pages;

    private Integer total_results;

}
