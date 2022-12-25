package com.indrabhushan.BookmarkApp.dao;

import java.util.List;

import com.indrabhushan.BookmarkApp.DataStore;
import com.indrabhushan.BookmarkApp.entities.Bookmark;
import com.indrabhushan.BookmarkApp.entities.UserBookmark;

public class BookmarkDao {
	public List<List<Bookmark>> getBookmarks() {
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		DataStore.add(userBookmark);

	}
}
