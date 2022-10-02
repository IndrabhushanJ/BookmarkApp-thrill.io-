package com.indrabhushan.BookmarkApp;

import com.indrabhushan.BookmarkApp.constants.KidFriendlyStatus;
import com.indrabhushan.BookmarkApp.constants.UserType;
import com.indrabhushan.BookmarkApp.controllers.BookmarkController;
import com.indrabhushan.BookmarkApp.entities.Bookmark;
import com.indrabhushan.BookmarkApp.entities.User;

public class View {

	public static void browse(User user, Bookmark[][] bookmarks) {
		System.out.println("\n" + user.getEmail() + " is browsing items...");
		int bookmarkCount = 0;
		for (Bookmark[] bookmarkType : bookmarks) {
			for (Bookmark bookmark : bookmarkType) {
				// Bookmarking
				if (bookmarkCount < DataStore.USER_BOOKMARK_LIMIT) {
					boolean isBookmarked = getBookmarkDecision(bookmark);

					if (isBookmarked) {
						bookmarkCount++;

						BookmarkController.getInstance().saveUserBookmark(user, bookmark);

						System.out.println("New Item Bookmarked --" + bookmark);

					}
				}
				// Mark as kid-friendly
				if (user.getUserType().equals(UserType.EDITOR) || user.getUserType().equals(UserType.CHIEF_EDITOR)) {
					if (bookmark.isKidFriendlyEligible()
							&& bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {
						String kidFriendlyStatus = getKidFriendlyStatusDecision();
						if (!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)) {
							bookmark.setKidFriendlyStatus(kidFriendlyStatus);
							System.out.println("Kid-friendly Status: " + kidFriendlyStatus + "," + bookmark);
						}
					}
				}
			}
		}

	}

	private static String getKidFriendlyStatusDecision() {
		double randomVal = Math.random();

		return randomVal < 0.4 ? KidFriendlyStatus.APPROVED
				: (randomVal >= 0.4 && randomVal < 0.8) ? KidFriendlyStatus.REJECTED : KidFriendlyStatus.UNKNOWN;

		/*
		 * //removal due to performance issue return Math.random() < 0.4 ?
		 * KidFriendlyStatus.APPROVED : (Math.random() >= 0.4 && Math.random() < 0.8) ?
		 * KidFriendlyStatus.REJECTED : KidFriendlyStatus.UNKNOWN;
		 */

	}

	private static boolean getBookmarkDecision(Bookmark bookmark) {
		return Math.random() < 0.5 ? true : false;

	}

	/*
	 * public static void bookmark(User user, Bookmark[][] bookmarks) {
	 * System.out.println("\n" + user.getEmail() + " is bookmarking"); for (int i =
	 * 0; i < DataStore.USER_BOOKMARK_LIMIT; i++) { int typeOffset = (int)
	 * (Math.random() * DataStore.BOOKMARK_TYPE_COUNT); int bookmarkOffset = (int)
	 * (Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE);
	 * 
	 * Bookmark bookmark = bookmarks[typeOffset][bookmarkOffset];
	 * 
	 * BookmarkController.getInstance().saveUserBookmark(user, bookmark);
	 * 
	 * System.out.println(bookmark); } }
	 */
}
