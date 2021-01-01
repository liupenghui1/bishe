package com.lph.dao;

import com.lph.entity.GameConn;

public interface GameConnMapper {
	public GameConn findGameConnSetByUserId(int uid);
	public int insertConnUser(int uid);
	public int updateConnUser(int uid,int nowset);
}
