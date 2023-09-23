package br.com.luanasoares.javachallenge.repository;

import br.com.luanasoares.javachallenge.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(nativeQuery = true, value = "select top 10 * from movie order by star desc")
    List<Movie> findByTop();

}
