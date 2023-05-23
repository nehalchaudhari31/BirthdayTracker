package com.BirthdayTracker.Entities;

import org.springframework.web.multipart.MultipartFile;

public class UserImageFile {

	private MultipartFile file;
	private User user;
	public UserImageFile(MultipartFile file, User user) {
		super();
		this.file = file;
		this.user = user;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
