package com.weddings_planner.dtos;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.google.gson.annotations.Expose;
import com.weddings_planner.enums.Gender;

public class PersonImportDto implements Serializable {
			
	@Expose
	@NotNull
	@Size(min = 1, max = 60)
	private String firstName;
	
	@Expose
	@NotNull
	@Size(min = 2)
	private String lastName;
	
	@Expose
	@NotNull
	@Size(max = 1)
	private String middleInitial;
	
	@Expose
	private Gender gender;
	
	@Expose
	private String birthday;
	
	@Expose
	private String phone;
	
	@Expose
	private String email;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
