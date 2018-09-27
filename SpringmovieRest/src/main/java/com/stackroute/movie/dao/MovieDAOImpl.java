package com.stackroute.movie.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.movie.model.Movie;

/*
 * This class is implementing the NoteDAO interface. This class has to be annotated with @Repository
 * annotation.
 * @Repository - is an annotation that marks the specific class as a Data Access Object, thus
 *                  clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database
 *                     transaction. The database transaction happens inside the scope of a persistence
 *                     context. 
 * */
@Repository
@Transactional
public class MovieDAOImpl implements MovieDAO {
	private SessionFactory sessionFactory;
	List<Movie> list = null;
	Movie movieObj = new Movie();
	boolean bool = false;

	/*
	 * Autowiring should be implemented for the SessionFactory.
	 */
	@Autowired
	public MovieDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*
	 * Save the note in the database(note) table.
	 */

	public boolean saveMovie(Movie movie) {
		Session session = sessionFactory.getCurrentSession();
		session.save(movie);
		session.flush();
		return true;

	}

	/*
	 * Remove the note from the database(note) table.
	 */

	public boolean deleteMovie(int movieId) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(getMovieById(movieId));
		session.flush();
		return true;
	}

	/*
	 * retrieve all existing notes sorted by created Date in descending
	 * order(showing latest note first)
	 */
	public List<Movie> getAllMovies() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("from Movie order by createdAt DESC");
		@SuppressWarnings("unchecked")
		List<Movie> resultList = query.getResultList();
		return resultList;

	}

	/*
	 * retrieve specific note from the database(note) table
	 */
	@SuppressWarnings("deprecation")
	public Movie getMovieById(int movieId) {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("rawtypes")
		Query query = session.createQuery("from Movie where movieId = :movieId");
		query.setInteger("movieId", movieId);
		Movie movie = (Movie) query.uniqueResult();
		return movie;
	}

	/* Update existing note */

	public boolean updateMovie(Movie movie) {
		System.out.println("Update note is getting called");
		sessionFactory.getCurrentSession().update(movie);
		return true;
	}

}