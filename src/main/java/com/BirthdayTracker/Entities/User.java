package com.BirthdayTracker.Entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")
public class User {

	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int Id;
	private String uname;
	@DateTimeFormat
	private String birthdate;
	private String ulocation;
	private String umail;
	private String uInstaHandle;
	private String uFacebookHandle;
	private String uContact;
	private String profilePicName;
	
	public User(int id, String uname, String birthdate, String ulocation, String umail, String uInstaHandle,
			String uFacebookHandle, String uContact, String profilePicName) {
		super();
		Id = id;
		this.uname = uname;
		this.birthdate = birthdate;
		this.ulocation = ulocation;
		this.umail = umail;
		this.uInstaHandle = uInstaHandle;
		this.uFacebookHandle = uFacebookHandle;
		this.uContact = uContact;
		this.profilePicName = profilePicName;
	}

	public String getProfilePicName() {
		return profilePicName;
	}

	public void setProfilePicName(String profilePicName) {
		this.profilePicName = profilePicName;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getuContact() {
		return uContact;
	}

	public void setuContact(String uContact) {
		this.uContact = uContact;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUlocation() {
		return ulocation;
	}

	public void setUlocation(String ulocation) {
		this.ulocation = ulocation;
	}

	public String getUmail() {
		return umail;
	}

	public void setUmail(String umail) {
		this.umail = umail;
	}

	public String getuInstaHandle() {
		return uInstaHandle;
	}

	public void setuInstaHandle(String uInstaHandle) {
		this.uInstaHandle = uInstaHandle;
	}

	public String getuFacebookHandle() {
		return uFacebookHandle;
	}

	public void setuFacebookHandle(String uFacebookHandle) {
		this.uFacebookHandle = uFacebookHandle;
	}

	public User() {
		super();
	}

	 

}
