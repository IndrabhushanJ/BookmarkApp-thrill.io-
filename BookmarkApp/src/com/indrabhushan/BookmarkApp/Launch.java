package com.indrabhushan.BookmarkApp;

import java.util.List;

import com.indrabhushan.BookmarkApp.bgjobs.WebPageDownloaderTask;
import com.indrabhushan.BookmarkApp.entities.Bookmark;
import com.indrabhushan.BookmarkApp.entities.User;
import com.indrabhushan.BookmarkApp.managers.BookmarkManager;
import com.indrabhushan.BookmarkApp.managers.UserManager;

public class Launch {
	private static List<User> users;
	private static List<List<Bookmark>> bookmarks;

	private static void loadData() {
		System.out.println("1. Loading Data...");
		DataStore.loadData();

		System.out.println("2. Fetching Data...");
		users = UserManager.getInstance().getUsers();
		bookmarks = BookmarkManager.getInstance().getbookmarks();

//		System.out.println("Printig Data...");
//		printUserData();
//		printBookmarkData();
	}

	private static void printBookmarkData() {
		for (List<Bookmark> bookmarksType : bookmarks) {
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

	private static void start() {
		System.out.println("\nBrowsing...");
		for (User user : users) {
			View.browse(user, bookmarks);
		}

	}

	public static void main(String[] args) {
		loadData();
		start();
		runDownloaderJob();

	}

	private static void runDownloaderJob() {
		WebPageDownloaderTask task = new WebPageDownloaderTask(true);
		(new Thread(task)).start();
	}

}
