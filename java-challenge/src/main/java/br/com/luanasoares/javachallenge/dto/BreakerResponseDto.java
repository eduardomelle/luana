package br.com.luanasoares.javachallenge.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BreakerResponseDto implements Serializable {
    private List<ResultBreakerDto> results;
    private Integer total_pages;
    private Integer total_results;
}