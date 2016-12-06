package com.web.libraries.imdbquery.api.model.movie;

/**
 * @author Fabrizio
 *
 */
public class Movie {

	private String uid;
	
	private String title;

	private String director;

	private Integer year;
	
	private String pictureURL;
	
	private String movieURL;

	private String provider;
	
	/**
	 * Default Constructor
	 * @param uid Movie XMDB Manager Unique Reference
	 * @param title Movie title
	 * @param director Movie director
	 * @param year Movie year
	 * @param pictureURL Movie picture URL
	 * @param movieURL Movie site URL
	 * @param provider Movie site provider
	 */
	public Movie(String uid, String title, String director,Integer year, String pictureURL, String movieURL, String provider) {
		super();
		this.uid = uid;
		this.title = title;
		this.director = director;
		this.year = year;
		this.pictureURL = pictureURL;
		this.movieURL = movieURL;
		this.provider = provider;
	}

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the director
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @return the pictureURL
	 */
	public String getPictureURL() {
		return pictureURL;
	}

	/**
	 * @return the movieURL
	 */
	public String getMovieURL() {
		return movieURL;
	}

	/**
	 * @return the provider
	 */
	public String getProvider() {
		return provider;
	}
	

}
