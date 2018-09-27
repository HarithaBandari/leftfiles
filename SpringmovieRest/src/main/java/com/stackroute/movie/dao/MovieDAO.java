package com.stackroute.movie.dao;

import java.util.List;

import com.stackroute.movie.model.Movie;

public interface MovieDAO {
	
	/* You Should not modify this interface.  You have to implement these methods in corresponding Impl class*/

	public boolean saveMovie(Movie movie);

	public boolean deleteMovie(int movieId);

	public List<Movie> getAllMovies();

	public Movie getMovieById(int movieId);

	//public boolean UpdateNote(Note note);

	public boolean updateMovie(Movie movie);

}
