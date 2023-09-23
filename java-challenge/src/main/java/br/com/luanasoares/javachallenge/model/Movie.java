package br.com.luanasoares.javachallenge.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String originalTitle;

    @Column(nullable = false)
    private String title;

    @Size(max = 1337)
    @Column(nullable = false)
    private String overview;

    @Column(nullable = false)
    private Integer star;

}
