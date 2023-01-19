package com.indrabhushan.BookmarkApp.managers;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import com.indrabhushan.BookmarkApp.constants.BookGenre;
import com.indrabhushan.BookmarkApp.constants.KidFriendlyStatus;
import com.indrabhushan.BookmarkApp.constants.MovieGenre;
import com.indrabhushan.BookmarkApp.dao.BookmarkDao;
import com.indrabhushan.BookmarkApp.entities.Book;
import com.indrabhushan.BookmarkApp.entities.Bookmark;
import com.indrabhushan.BookmarkApp.entities.Movie;
import com.indrabhushan.BookmarkApp.entities.User;
import com.indrabhushan.BookmarkApp.entities.UserBookmark;
import com.indrabhushan.BookmarkApp.entities.WebLink;
import com.indrabhushan.BookmarkApp.util.HttpConnect;
import com.indrabhushan.BookmarkApp.util.IOUtil;

public class BookmarkManager {
	// Singleton Pattern - Class has only one instance
	private static BookmarkManager instance = new BookmarkManager();
	private static BookmarkDao dao = new BookmarkDao();

	private BookmarkManager() {
	}

	public static BookmarkManager getInstance() {
		return instance;
	}

	public Movie createMovie(long id, String title, int releaseYear, String[] cast, String[] directors,
			MovieGenre genre, double imdbRating) {
		Movie movie = new Movie();
		movie.setId(id);
		movie.setTitle(title);
		movie.setReleaseYear(releaseYear);
		movie.setCast(cast);
		movie.setDirectors(directors);
		movie.setGenre(genre);
		movie.setImdbRating(imdbRating);

		return movie;
	}

	public Book createBook(long id, String title, int publicationYear, String publisher, String[] authors,
			BookGenre genre, double amazonRating) {
		Book book = new Book();
		book.setId(id);
		book.setTitle(title);
		book.setPublicationYear(publicationYear);
		book.setPublisher(publisher);
		book.setAuthors(authors);
		book.setGenre(genre);
		book.setAmazonRating(amazonRating);

		return book;
	}

	public WebLink createWebLink(long id, String title, String url, String host) {
		WebLink weblink = new WebLink();
		weblink.setId(id);
		weblink.setTitle(title);
		weblink.setUrl(url);
		weblink.setHost(host);

		return weblink;

	}

	public List<List<Bookmark>> getbookmarks() {
		return dao.getBookmarks();
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		UserBookmark userBookmark = new UserBookmark();
		userBookmark.setUser(user);
		userBookmark.setBookmark(bookmark);

		/*
		 * if (bookmark instanceof WebLink) { try { String url = ((WebLink)
		 * bookmark).getUrl(); if (!url.endsWith(".pdf")) { String webpage =
		 * HttpConnect.download(((WebLink) bookmark).getUrl()); if (webpage != null) {
		 * IOUtil.write(webpage, bookmark.getId()); } } } catch (MalformedURLException
		 * e) {
		 * 
		 * e.printStackTrace(); } catch (URISyntaxException e) {
		 * 
		 * e.printStackTrace(); } }
		 */

		dao.saveUserBookmark(userBookmark);

	}

	public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyStatus, Bookmark bookmark) {
		bookmark.setKidFriendlyStatus(kidFriendlyStatus);
		bookmark.setKidFriendlyMarkedBy(user);

		dao.updateKidFriendlyStatus(bookmark);

		System.out.println(
				"Kid-friendly Status: " + kidFriendlyStatus + ", marked by: " + user.getEmail() + ", " + bookmark);

	}

	public void share(User user, Bookmark bookmark) {
		bookmark.setShareBy(user);

		if (bookmark instanceof Book) {
			System.out.println(((Book) bookmark).getItemData());
		} else if (bookmark instanceof WebLink) {
			System.out.println(((WebLink) bookmark).getItemData());

		}
			dao.sharedByInfo(bookmark);
	}

}
