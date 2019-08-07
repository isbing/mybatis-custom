package com.isbing.entity;

/**
 * Created by song bing
 * Created time 2019/8/7 14:51
 */
public class User {
	private Long id;
	private String userName;
	private String address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", userName='" + userName + '\'' +
				", address='" + address + '\'' +
				'}';
	}
}
