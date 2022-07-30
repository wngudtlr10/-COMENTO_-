package com.devfun.vo;

public class MovieVO {
	private int movie_id;
	private String movie_name;
	private String director;
	private String type;
	private String moviecol;
	
	public MovieVO() {}
	
	public MovieVO(int movie_id, String movie_name, String director, String type, String moviecol) {
		this.movie_id = movie_id;
		this.movie_name = movie_name;
		this.director = director;
		this.type = type;
		this.moviecol = moviecol;
	}

	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public String getMovie_name() {
		return movie_name;
	}
	public void setMovie_name(String movie_name) {
		this.movie_name = movie_name;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMoviecol() {
		return moviecol;
	}
	public void setMoviecol(String moviecol) {
		this.moviecol = moviecol;
	}
	
	@Override
	public String toString() {
		return "MovieVO [movie_id=" + movie_id + ", movie_name=" + movie_name + ", director=" + director + ", type="
				+ type + ", moviecol=" + moviecol + "]";
	}

}
