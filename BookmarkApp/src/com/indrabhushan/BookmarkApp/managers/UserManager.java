package com.indrabhushan.BookmarkApp.managers;

import java.util.List;

import com.indrabhushan.BookmarkApp.constants.Gender;
import com.indrabhushan.BookmarkApp.constants.UserType;
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

	public User createUser(long id, String email, String password, String firstName, String lastName, Gender gender,
			UserType userType) {
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

	public List<User> getUsers() {
		return dao.getUsers();
	}

}
