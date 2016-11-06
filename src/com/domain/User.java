package com.domain;

import java.io.Serializable;

public class User implements Serializable{
	private String ID;
	private String name;
	private String photopath;
	private String department;
	private String position;
	public String getID() {
		return ID;
	}
	public void setID(String id) {
		ID = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhotopath() {
		return photopath;
	}
	public void setPhotopath(String photopath) {
		this.photopath = photopath;
	}
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	
	
}
