package com.indrabhushan.BookmarkApp.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.indrabhushan.BookmarkApp.managers.BookmarkManager;

class WeblinkTest {

	@Test
	void testIsKidFriendlyEligible() {
		// Test 1: porn in url -- false
		WebLink weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-porn--part-2.html",
				"http://www.javaworld.com");

		boolean isKidFriendlyEligible = weblink.isKidFriendlyEligible();

		assertFalse(isKidFriendlyEligible, "For porn in url, isKidFriendlyEligible must return false");

		// Test 2: porn in title -- false
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming porn, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");

		isKidFriendlyEligible = weblink.isKidFriendlyEligible();

		assertFalse(isKidFriendlyEligible, "For porn in title, isKidFriendlyEligible must return false");

		// Test 3: adult in host -- false
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html", "http://www.adult.com");

		isKidFriendlyEligible = weblink.isKidFriendlyEligible();

		assertFalse(isKidFriendlyEligible, "For adult in host, isKidFriendlyEligible must return false");

		// Test 4: adult in url but not in host part-- true
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/adult-tiger--part-2.html",
				"http://www.javaworld.com");

		isKidFriendlyEligible = weblink.isKidFriendlyEligible();

		assertTrue(isKidFriendlyEligible,
				"For adult in url but not in host part, isKidFriendlyEligible must return true");

		// Test 5: adult in title only -- true
		weblink = BookmarkManager.getInstance().createWebLink(2000, "Taming adult, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");

		isKidFriendlyEligible = weblink.isKidFriendlyEligible();

		assertTrue(isKidFriendlyEligible, "For adult in title only, isKidFriendlyEligible must return true");

	}

}
