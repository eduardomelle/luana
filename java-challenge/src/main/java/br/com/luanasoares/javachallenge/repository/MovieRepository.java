package br.com.luanasoares.javachallenge.repository;

import br.com.luanasoares.javachallenge.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
