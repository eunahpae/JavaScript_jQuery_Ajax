package com.eunah.model.dto;

import java.sql.Date;

public class MemberDTO implements java.io.Serializable {

	private String code;
	private String name;
	private char gender;
	private int age;
	private Date enrollDate;

	public MemberDTO() {
		super();
	}

	public MemberDTO(String code, String name, char gender, int age) {
		super();
		this.code = code;
		this.name = name;
		this.gender = gender;
		this.age = age;
	}

	public MemberDTO(String code, String name, char gender, int age, Date enrollDate) {
		super();
		this.code = code;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.enrollDate = enrollDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "MemberDTO [code=" + code + ", name=" + name + ", gender=" + gender + ", age=" + age + ", enrollDate="
				+ enrollDate + "]";
	}

}
