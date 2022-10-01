package com.indrabhushan.BookmarkApp;

import com.indrabhushan.BookmarkApp.entities.Bookmark;
import com.indrabhushan.BookmarkApp.entities.User;
import com.indrabhushan.BookmarkApp.managers.BookmarkManager;
import com.indrabhushan.BookmarkApp.managers.UserManager;

public class Launch {
	private static User[] users;
	private static Bookmark[][] bookmarks;

	private static void loadData() {
		System.out.println("1. Loading Data...");
		DataStore.loadData();

		System.out.println("2. Fetching Data...");
		users = UserManager.getInstance().getUsers();
		bookmarks = BookmarkManager.getinstance().getbookmarks();

		System.out.println("Printig Data...");
		printUserData();
		printBookmarkData();
	}

	private static void printBookmarkData() {
		for (Bookmark[] bookmarksType : bookmarks) {
			for (Bookmark bookmark : bookmarksType) {
				System.out.println(bookmark);
			}
		}

	}

	private static void printUserData() {
		for (User user : users) {
			System.out.println(user);
		}

	}

	private static void startBookmarking() {
		System.out.println("\nBookmarking...");
		for (User user : users) {
			View.bookmark(user, bookmarks);
		}

	}

	public static void main(String[] args) {
		loadData();
		startBookmarking();

	}

}
