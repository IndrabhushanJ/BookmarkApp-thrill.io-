package com.indrabhushan.BookmarkApp.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.indrabhushan.BookmarkApp.constants.MovieGenre;
import com.indrabhushan.BookmarkApp.managers.BookmarkManager;

class MovieTest {

	@Test
	void testIsKidFriendlyEligible() {
		// Test 1: Horror in genre -- false
		Movie movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", 1941,
				new String[] { "Orson Welles,Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.HORROR, 8.5);

		boolean isKidFriendlyEligible = movie.isKidFriendlyEligible();

		assertFalse(isKidFriendlyEligible, "Horror in genre, isKidFriendlyEligible must return false");

		// Test 2: Thriller in genre -- false
		movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", 1941,
				new String[] { "Orson Welles,Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.THRILLERS,
				8.5);

		isKidFriendlyEligible = movie.isKidFriendlyEligible();

		assertFalse(isKidFriendlyEligible, "Thriller in genre, isKidFriendlyEligible must return false");
	}

}
