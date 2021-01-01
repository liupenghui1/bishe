package com.lph.entity;

public class GameConn {
	private int id;
	private int nowset;
	private int userId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNowset() {
		return nowset;
	}
	public void setNowset(int nowset) {
		this.nowset = nowset;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "GameConn [id=" + id + ", nowset=" + nowset + ", userId=" + userId + "]";
	}
	
}
