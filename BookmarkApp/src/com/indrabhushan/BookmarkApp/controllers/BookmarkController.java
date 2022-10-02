package com.indrabhushan.BookmarkApp.controllers;

import com.indrabhushan.BookmarkApp.entities.Bookmark;
import com.indrabhushan.BookmarkApp.entities.User;
import com.indrabhushan.BookmarkApp.managers.BookmarkManager;

public class BookmarkController {
	private static BookmarkController instance = new BookmarkController();

	private BookmarkController() {
	}

	public static BookmarkController getInstance() {
		return instance;
	}

	public void saveUserBookmark(User user, Bookmark bookmark) {
		BookmarkManager.getinstance().saveUserBookmark(user, bookmark);

	}

	public void setKidFriendlyStatus(User user, String kidFriendlyStatus, Bookmark bookmark) {
		BookmarkManager.getinstance().setKidFriendlyStatus(user,kidFriendlyStatus,bookmark);
		
	}

	public void share(User user, Bookmark bookmark) {
		BookmarkManager.getinstance().share(user, bookmark);
		
	}
}
