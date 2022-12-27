package com.indrabhushan.BookmarkApp;

import java.util.ArrayList;
import java.util.List;

import com.indrabhushan.BookmarkApp.constants.BookGenre;
import com.indrabhushan.BookmarkApp.constants.Gender;
import com.indrabhushan.BookmarkApp.constants.MovieGenre;
import com.indrabhushan.BookmarkApp.constants.UserType;
import com.indrabhushan.BookmarkApp.entities.Bookmark;
import com.indrabhushan.BookmarkApp.entities.User;
import com.indrabhushan.BookmarkApp.entities.UserBookmark;
import com.indrabhushan.BookmarkApp.managers.BookmarkManager;
import com.indrabhushan.BookmarkApp.managers.UserManager;
import com.indrabhushan.BookmarkApp.util.IOUtil;

public class DataStore {

	private static List<User> users = new ArrayList<>();

	public static List<User> getUsers() {
		return users;
	}

	private static List<List<Bookmark>> bookmarks = new ArrayList<>();

	public static List<List<Bookmark>> getBookmarks() {
		return bookmarks;
	}

	private static List<UserBookmark> userBookmarks = new ArrayList<>();
//	private static int bookmarkIndex;

	public static void loadData() {
		loadUsers();
		loadWebLinks();
		loadMovies();
		loadBooks();
	}

	private static void loadWebLinks() {
		/*
		 * bookmarks[0][0] = BookmarkManager.getinstance().createWebLink(2000,
		 * "Taming Tiger, Part 2",
		 * "http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
		 * "http://www.javaworld.com"); bookmarks[0][1] =
		 * BookmarkManager.getinstance().createWebLink(2001,
		 * "How do I import a pre-existing Java project into Eclipse and get up and running?"
		 * ,
		 * "http://stackoverflow.com/questions/142863/how-do-i-import-a-pre-existing-java-project-into-eclipse-and-get-up-and-running",
		 * "http://www.stackoverflow.com"); bookmarks[0][2] =
		 * BookmarkManager.getinstance().createWebLink(2002,
		 * "Interface vs Abstract Class",
		 * "http://mindprod.com/jgloss/interfacevsabstract.html",
		 * "http://mindprod.com"); bookmarks[0][3] =
		 * BookmarkManager.getinstance().createWebLink(2003,
		 * "NIO tutorial by Greg Travis",
		 * "http://cs.brown.edu/courses/cs161/papers/j-nio-ltr.pdf",
		 * "http://cs.brown.edu"); bookmarks[0][4] =
		 * BookmarkManager.getinstance().createWebLink(2004,
		 * "Virtual Hosting and Tomcat",
		 * "http://tomcat.apache.org/tomcat-6.0-doc/virtual-hosting-howto.html",
		 * "http://tomcat.apache.org");
		 */
//		String[] data = new String[BOOKMARK_COUNT_PER_TYPE];
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "WebLink");

		List<Bookmark> bookmarkList = new ArrayList<>();
		for (String row : data) {
			String[] values = row.split("\t");

			Bookmark bookmark = BookmarkManager.getInstance().createWebLink(Long.parseLong(values[0]), values[1],
					values[2], values[3]);
			bookmarkList.add(bookmark);

		}
		bookmarks.add(bookmarkList);
	}

	private static void loadMovies() {
		/*
		 * bookmarks[1][0] = BookmarkManager.getInstance().createMovie(3000,
		 * "Citizen Kane", 1941, new String[] { "Orson Welles,Joseph Cotten" }, new
		 * String[] { "Orson Welles" }, MovieGenre.CLASSICS, 8.5); bookmarks[1][1] =
		 * BookmarkManager.getInstance().createMovie(3001, "The Grapes of Wrath", 1940,
		 * new String[] { "Henry Fonda,Jane Darwell" }, new String[] { "John Ford" },
		 * MovieGenre.CLASSICS, 8.2); bookmarks[1][2] =
		 * BookmarkManager.getInstance().createMovie(3002, "A Touch of Greatness", 2004,
		 * new String[] { "Albert Cullum" }, new String[] { "Leslie Sullivan" },
		 * MovieGenre.DOCUMENTARIES, 7.3); bookmarks[1][3] =
		 * BookmarkManager.getInstance().createMovie(3003, "The Big Bang Theory", 2007,
		 * new String[] { "Kaley Cuoco,Jim Parsons" }, new String[] {
		 * "Chuck Lorre,Bill Prady" }, MovieGenre.TV_SHOWS, 8.7); bookmarks[1][4] =
		 * BookmarkManager.getInstance().createMovie(3004, "Ikiru", 1952, new String[] {
		 * "Takashi Shimura,Minoru Chiaki" }, new String[] { "Akira Kurosawa" },
		 * MovieGenre.FOREIGN_MOVIES, 8.4);
		 */
//		String[] data = new String[BOOKMARK_COUNT_PER_TYPE];
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "Movie");

		List<Bookmark> bookmarkList = new ArrayList<>();
		for (String row : data) {
			String[] values = row.split("\t");
			String[] cast = values[3].split(",");
			String[] directors = values[4].split(",");
			Bookmark bookmark = BookmarkManager.getInstance().createMovie(Long.parseLong(values[0]), values[1],
					Integer.parseInt(values[2]), cast, directors, MovieGenre.valueOf(values[5]),
					Double.parseDouble(values[6])/* , values[7] */);
			bookmarkList.add(bookmark);
		}
		bookmarks.add(bookmarkList);
	}

	private static void loadBooks() {
		/*
		 * bookmarks[2][0] = BookmarkManager.getInstance().createBook(4000, "Walden",
		 * 1854, "Wilder Publications", new String[] { "Henry David Thoreau" },
		 * BookGenre.PHILOSOPHY, 4.3); bookmarks[2][1] =
		 * BookmarkManager.getInstance().createBook(4001,
		 * "Self-Reliance and Other Essays", 1993, "Dover Publications", new String[] {
		 * "Ralph Waldo Emerson" }, BookGenre.PHILOSOPHY, 4.5); bookmarks[2][2] =
		 * BookmarkManager.getInstance().createBook(4002, "Light From Many Lamps", 1988,
		 * "Touchstone", new String[] { "Lillian Eichler Watson" },
		 * BookGenre.PHILOSOPHY, 5.0); bookmarks[2][3] =
		 * BookmarkManager.getInstance().createBook(4003, "Head First Design Patterns",
		 * 2004, "O'Reilly Media", new String[] {
		 * "Eric Freeman,Bert Bates,Kathy Sierra,Elisabeth Robson" },
		 * BookGenre.TECHNICAL, 4.5); bookmarks[2][4] =
		 * BookmarkManager.getInstance().createBook(4004,
		 * "Effective Java Programming Language Guide", 2007, "Prentice Hall", new
		 * String[] { "Joshua Bloch" }, BookGenre.TECHNICAL, 4.9);
		 */

		List<String> data = new ArrayList<>();
		IOUtil.read(data, "Book");
		List<Bookmark> bookmarkList = new ArrayList<>();
		for (String row : data) {
			String[] values = row.split("\t");
			String[] authors = values[4].split(",");
			Bookmark boookmark = BookmarkManager.getInstance().createBook(Long.parseLong(values[0]), values[1],
					Integer.parseInt(values[2]), values[3], authors, BookGenre.valueOf(values[5]),
					Double.parseDouble(values[6])/* , values[7] */);
			bookmarkList.add(boookmark);
		}
		bookmarks.add(bookmarkList);
	}

	private static void loadUsers() {
		/*
		 * users[0] = UserManager.getInstance().createUser(1000,
		 * "user0@semanticsquare.com", "test", "John", "M", Gender.MALE, UserType.USER);
		 * users[1] = UserManager.getInstance().createUser(1001,
		 * "user1@semanticsquare.com", "test", "Sam", "M", Gender.MALE, UserType.USER);
		 * users[2] = UserManager.getInstance().createUser(1002,
		 * "user2@semanticsquare.com", "test", "Anita", "M", Gender.FEMALE,
		 * UserType.EDITOR); users[3] = UserManager.getInstance().createUser(1003,
		 * "user3@semanticsquare.com", "test", "Sara", "M", Gender.FEMALE,
		 * UserType.EDITOR); users[4] = UserManager.getInstance().createUser(1004,
		 * "user4@semanticsquare.com", "test", "Dheeru", "M", Gender.MALE,
		 * UserType.CHIEF_EDITOR);
		 */

//		String[] data = new String[TOTAL_USER_COUNT];
		List<String> data = new ArrayList<>();
		IOUtil.read(data, "User");

		for (String row : data) {
			String[] values = row.split("\t");

			Gender gender = Gender.MALE;
			if (values[5].equals("f")) {
				gender = Gender.FEMALE;
			} else if (values[5].equals("t")) {
				gender = Gender.TRANGENDER;
			}
			User user = UserManager.getInstance().createUser(Long.parseLong(values[0]), values[1], values[2], values[3],
					values[4], gender, UserType.valueOf(values[6]));

			users.add(user);
		}
	}

	public static void add(UserBookmark userBookmark) {
		userBookmarks.add(userBookmark);

	}
}
