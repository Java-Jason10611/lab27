package com.Movie.Movies;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieApiController {
	@Autowired
	private MovieDao dao;

	@GetMapping("/Movies")
	public List<Movie> listMovies(@RequestParam(required = false) String name,
			@RequestParam(required = false) String catagory) {
		if (name == null || name.isEmpty()) {
			return dao.findAll();
		} else {
			return dao.findByNameContainsIgnoreCase(name);
		}
	}

	@GetMapping("/Movies/catagory")
	public List<Movie> listMoviesFromCatagory(@RequestParam(required = false) String catagory) {
		if (catagory == null || catagory.isEmpty()) {
			return dao.findAll();
		} else {
			return dao.FindCatagoryIgnoreCase(catagory);
		}
	}

	@GetMapping("/Movie/{id}")
	public Movie oneMovie(@PathVariable("id") Long id) {
		return dao.findById(id).get();
	}

	@GetMapping("/random-movie")
	public Movie randomMovieFromCatagory(@RequestParam(required = false) String category, Long id) {
		Random rand = new Random();
		Long count=  dao.count();
		int res=rand.nextInt(count.intValue())+1;
		
		
		if (category == null || category.isEmpty()) {
			return  dao.findById(Long.valueOf(res)).get();
		} else {
			List<Movie> movie=dao.FindCatagoryIgnoreCase(category);
			res = rand.nextInt(movie.size());
			return movie.get(res);

		}
	}

	@GetMapping("/random-movies")
	public List<Movie> randomMovieList(@RequestParam("size") Integer size) {
		List<Movie> list = new ArrayList<>();
		
		Long count = dao.count();
		Random rand = new Random();
		int res;

		for (int i = 0; i < size; i++) {
			res = rand.nextInt(count.intValue()) + 1;
			list.add(dao.findById(Long.valueOf(res)).get());
		}
		return list;
	}

	@GetMapping("/categories")
	public List<Movie> catagories(@RequestParam(required = true) String category) {
		return dao.findCategory(category);

	}
}
