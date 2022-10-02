package com.indrabhushan.BookmarkApp.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.indrabhushan.BookmarkApp.constants.BookGenre;
import com.indrabhushan.BookmarkApp.managers.BookmarkManager;

class BookTest {

	@Test
	void testIsKidFriendlyEligible() {
		// Test 1: For Philosophy genre
		Book book = BookmarkManager.getinstance().createBook(4000, "Walden", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.PHILOSOPHY, 4.3);
		boolean isKidFriendlyEligible = book.isKidFriendlyEligible();

		assertFalse(isKidFriendlyEligible, "For Philosophy genre, isKidFriendlyEligible must return false");

		// Test 1: For Self Help genre
		BookmarkManager.getinstance().createBook(4000, "Walden", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.SELF_HELP, 4.3);
		isKidFriendlyEligible = book.isKidFriendlyEligible();

		assertFalse(isKidFriendlyEligible, "For Self Help genre, isKidFriendlyEligible must return false");
	}

}
