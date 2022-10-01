package com.indrabhushan.BookmarkApp.dao;

import com.indrabhushan.BookmarkApp.DataStore;
import com.indrabhushan.BookmarkApp.entities.User;

public class UserDao {
	public User[] getUsers() {
		return DataStore.getUsers();
	}
}
