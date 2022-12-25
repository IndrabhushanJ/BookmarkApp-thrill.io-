package com.indrabhushan.BookmarkApp.dao;

import java.util.List;

import com.indrabhushan.BookmarkApp.DataStore;
import com.indrabhushan.BookmarkApp.entities.User;

public class UserDao {
	public List<User> getUsers() {
		return DataStore.getUsers();
	}
}
