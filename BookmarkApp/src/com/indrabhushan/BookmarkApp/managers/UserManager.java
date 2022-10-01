package com.indrabhushan.BookmarkApp.managers;

import com.indrabhushan.BookmarkApp.dao.UserDao;
import com.indrabhushan.BookmarkApp.entities.User;

public class UserManager {
	// Singleton Pattern - Class has only one instance
	private static UserManager instance = new UserManager();
	private static UserDao dao = new UserDao();

	private UserManager() {
	}

	public static UserManager getInstance() {
		return instance;
	}

	public User createUser(long id, String email, String password, String firstName, String lastName, int gender,
			String userType) {
		User user = new User();
		user.setId(id);
		user.setEmail(email);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setGender(gender);
		user.setUserType(userType);

		return user;

	}

	public User[] getUsers() {
		return dao.getUsers();
	}

}
