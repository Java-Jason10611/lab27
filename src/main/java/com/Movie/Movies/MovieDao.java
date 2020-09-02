package com.Movie.Movies;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


	public interface MovieDao extends JpaRepository<Movie, Long> {

		List<Movie> FindCatagoryIgnoreCase(String category);

		List<Movie> findByNameContainsIgnoreCase(String name);

		List<Movie> findCategory(String category);

	}

