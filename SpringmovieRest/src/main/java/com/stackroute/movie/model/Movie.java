package com.stackroute.movie.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
 * The class "Note" will be acting as the data model for the note Table in the database. Please
 * note that this class is annotated with @Entity annotation. Hibernate will scan all package for 
 * any Java objects annotated with the @Entity annotation. If it finds any, then it will begin the 
 * process of looking through that particular Java object to recreate it as a table in your database.
 */
@Entity
public class Movie {

	@Id
	private int movieId;
	private String movieTitle;
	private String movieContent;
	private String movieStatus;
	private LocalDateTime createdAt;

	public Movie() {

	}

	public Movie(int i, String string, String string2, String string3, LocalDateTime localdate) {
		this.movieId = i;
		this.movieTitle = string;
		this.movieContent = string2;
		this.movieStatus = string3;
		this.createdAt = localdate;
	}

	public int getNoteId() {
		return movieId ;
	}

	public void setNoteId(int parseInt) {
		this.movieId  = parseInt;
	}

	public String getNoteTitle() {
		return movieTitle;
	}

	public void setNoteTitle(String parameter) {
		this.movieTitle = parameter;
	}

	public String getNoteContent() {
		return movieContent;
	}

	public void setNoteContent(String parameter) {
		this.movieContent = parameter;
	}

	public String getNoteStatus() {
		return movieStatus;
	}

	public void setNoteStatus(String parameter) {
		this.movieStatus = parameter;
	}

	public void setCreatedAt(LocalDateTime createdAt) {

	}

	
}
