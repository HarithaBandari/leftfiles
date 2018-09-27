package com.stackroute.movie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.movie.dao.MovieDAO;
import com.stackroute.movie.model.Movie;


/*
* Annotate the class with @Controller annotation.@Controller annotation is used to mark
* any POJO class as a controller so that Spring can recognize this class as a Controller
*/

@RestController
@RequestMapping(value = "/api/v1/springmovierest/")
public class MovieController {

	/*
	 * From the problem statement, we can understand that the application requires
	 * us to implement the following functionalities.
	 * 
	 * 1. display the list of existing notes from the persistence data. Each note
	 * should contain Note Id, title, content, status and created date. 2. Add a new
	 * note which should contain the note id, title, content and status. 3. Delete
	 * an existing note 4. Update an existing note
	 * 
	 */

	/*
	 * Autowiring should be implemented for the NoteDAO. Create a Note object.
	 * 
	 */

	private MovieDAO movieDao;

	@Autowired
	public MovieController(MovieDAO movieDao) {

		this.movieDao= movieDao;
	}

	/*
	 * Define a handler method to read the existing notes from the database and add
	 * it to the ModelMap which is an implementation of Map, used when building
	 * model data for use with views. it should map to the default URL i.e. "/index"
	 */

	@RequestMapping(value = "/movies", method = RequestMethod.GET)
	public ResponseEntity<?>  getAllMovies() {
		List<Movie> list=movieDao.getAllMovies();
		return new ResponseEntity<List<Movie>>(list,HttpStatus.OK);

	}

	/*
	 * Define a handler method which will read the NoteTitle, NoteContent,
	 * NoteStatus from request parameters and save the note in note table in
	 * database. Please note that the CreatedAt should always be auto populated with
	 * system time and should not be accepted from the user. Also, after saving the
	 * note, it should show the same along with existing messages. Hence, reading
	 * note has to be done here again and the retrieved notes object should be sent
	 * back to the view using ModelMap This handler method should map to the URL
	 * "/add".
	 */
	@RequestMapping(value = "/movie", method = RequestMethod.POST, produces = { "application/json" })
	public ResponseEntity<?> add(@RequestBody Movie movie) {

		if (movieDao.saveMovie(movie)) {
			return new ResponseEntity<String>("{ \"message\": \"" + "success" + "\"}", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("{ \"message\": \"" + "failure" + "\"}", HttpStatus.CONFLICT);
		}

	}

	/*
	 * Define a handler method which will read the NoteId from request parameters
	 * and remove an existing note by calling the deleteNote() method of the
	 * NoteRepository class.This handler method should map to the URL "/delete".
	 */
	@RequestMapping(value = "/movie/{movieId}", method = RequestMethod.DELETE, produces = { "application/json" })
	public ResponseEntity<?> delete(@PathVariable int movieId) {
		boolean flag = movieDao.deleteMovie(movieId);
		if (flag) {
			return new ResponseEntity<String>("{ \"message\": \"" + "deleted" + "\"}", HttpStatus.OK);
		} else
		{
			return new ResponseEntity<String>("{ \"message\": \"" + "not deleted" + "\"}", HttpStatus.CONFLICT);
		}
	}

	/*
	 * Define a handler method which will update the existing note. This handler
	 * method should map to the URL "/update".
	 */
	@RequestMapping(value = "/movie", method = RequestMethod.PUT, produces = { "application/json" })
	public ResponseEntity<?> update(@RequestParam int movieId,@RequestBody Movie movie) {
		
		boolean result=movieDao.updateMovie(movie);
		if(result) {
		
		return new ResponseEntity<String>("{ \"message\": \"" + "updated" + "\"}", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("{ \"message\": \"" + "error while updating" + "\"}", HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(value = "/movie", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<?> getMovieById(@RequestParam int movieId) {
		Movie movie=movieDao.getMovieById(movieId);
		
		return new ResponseEntity<Movie>(movie,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/")
		public String index() {
		return "hello world";
	}

}