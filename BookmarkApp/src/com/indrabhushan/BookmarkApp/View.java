package com.indrabhushan.BookmarkApp;

import java.util.List;

import com.indrabhushan.BookmarkApp.constants.KidFriendlyStatus;
import com.indrabhushan.BookmarkApp.constants.UserType;
import com.indrabhushan.BookmarkApp.controllers.BookmarkController;
import com.indrabhushan.BookmarkApp.entities.Bookmark;
import com.indrabhushan.BookmarkApp.entities.User;
import com.indrabhushan.BookmarkApp.partner.Shareable;

public class View {

	public static void browse(User user, List<List<Bookmark>> bookmarks) {
		System.out.println("\n" + user.getEmail() + " is browsing items...");
		int bookmarkCount = 0;
		for (List<Bookmark> bookmarkType : bookmarks) {
			for (Bookmark bookmark : bookmarkType) {
				// Bookmarking
//				if (bookmarkCount < DataStore.USER_BOOKMARK_LIMIT) {
				boolean isBookmarked = getBookmarkDecision(bookmark);

				if (isBookmarked) {
					bookmarkCount++;

					BookmarkController.getInstance().saveUserBookmark(user, bookmark);

					System.out.println("New Item Bookmarked --" + bookmark);

				}
//				}

				if (user.getUserType().equals(UserType.EDITOR) || user.getUserType().equals(UserType.CHIEF_EDITOR)) {

					// Mark as kid-friendly
					if (bookmark.isKidFriendlyEligible()
							&& bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {
						KidFriendlyStatus kidFriendlyStatus = getKidFriendlyStatusDecision();
						if (!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)) {
							BookmarkController.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);

						}
					}

					// Sharing
					if (bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)
							&& bookmark instanceof Shareable) {
						boolean isShared = getShareDecision();
						if (isShared) {
							BookmarkController.getInstance().share(user, bookmark);
						}

					}
				}

			}
		}

	}

	// TODO: Below methods simulates user input. After IO, we take input via
	// Console.
	private static boolean getShareDecision() {
		return Math.random() < 0.5 ? true : false;

	}

	private static KidFriendlyStatus getKidFriendlyStatusDecision() {
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
