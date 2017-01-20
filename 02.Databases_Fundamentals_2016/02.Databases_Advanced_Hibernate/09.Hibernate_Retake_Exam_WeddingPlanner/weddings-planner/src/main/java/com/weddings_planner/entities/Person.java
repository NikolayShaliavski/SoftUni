package com.weddings_planner.entities;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import com.weddings_planner.enums.Gender;

@Entity
@Table(name = "persons")
public class Person implements Serializable {

//	private static final DateFormat DATE_FORMAT = 
//			new SimpleDateFormat("YYYY-MM-DD'T'HH:mm:ss");
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name", nullable = false)
	@Size(min = 1, max = 60)
	private String firstName;

	@Column(name = "middle_name_initial", nullable = false)
	private String middleInitial;

	@Column(name = "last_name", nullable = false)
	@Size(min = 2)
	private String lastName;

	@Transient
	private String fullName;

	@Column(name = "gender", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private Gender gender;

	@Column(name = "birthday")
	private Date birthday;

	@Transient
	private Integer age;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	public Person() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public void setMiddleInitial(String middleNameInitial) {
		this.middleInitial = middleNameInitial;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName() {
		this.fullName = 
				this.getFirstName() + " " + this.getMiddleInitial() + " " + this.getLastName();
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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
