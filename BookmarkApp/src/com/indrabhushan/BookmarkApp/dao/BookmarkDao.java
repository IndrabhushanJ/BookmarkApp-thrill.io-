package com.indrabhushan.BookmarkApp.dao;

import java.util.ArrayList;
import java.util.List;

import com.indrabhushan.BookmarkApp.DataStore;
import com.indrabhushan.BookmarkApp.entities.Bookmark;
import com.indrabhushan.BookmarkApp.entities.UserBookmark;
import com.indrabhushan.BookmarkApp.entities.WebLink;

public class BookmarkDao {
	public List<List<Bookmark>> getBookmarks() {
		return DataStore.getBookmarks();
	}

	public void saveUserBookmark(UserBookmark userBookmark) {
		DataStore.add(userBookmark);

	}

	public List<WebLink> getAllWebLinks() {
		List<WebLink> result = new ArrayList<>();
		List<List<Bookmark>> bookmarks = DataStore.getBookmarks();
		List<Bookmark> allWebLinks = bookmarks.get(0);

		for (Bookmark bookmark : allWebLinks) {
			result.add((WebLink) bookmark);
		}
		return result;
	}

	public List<WebLink> getWebLinks(WebLink.DownloadStatus downloadStatus) {
		List<WebLink> result = new ArrayList<>();

		List<WebLink> allWebLinks = getAllWebLinks();

		for (WebLink weblink : allWebLinks) {
			if (weblink.getDownloadStatus().equals(downloadStatus)) {
				result.add(weblink);
			}
		}

		return result;
	}
}
