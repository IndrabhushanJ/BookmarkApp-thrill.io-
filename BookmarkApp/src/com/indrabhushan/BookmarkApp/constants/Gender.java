package com.indrabhushan.BookmarkApp.constants;

public enum Gender {

	MALE(0), FEMALE(1), TRANGENDER(2);

	private Gender(int value) {
		this.value = value;
	}

	private int value;

	public int getValue() {
		return value;
	}
}
