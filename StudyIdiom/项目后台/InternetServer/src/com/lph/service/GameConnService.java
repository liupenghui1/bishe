package com.lph.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lph.dao.GameConnMapper;
import com.lph.entity.GameConn;



@Service
@Transactional(readOnly = false)
public class GameConnService {
	@Resource
	private GameConnMapper gameConnMapper;

	public GameConn findGameConnSetByUserId(int uid){
		return this.gameConnMapper.findGameConnSetByUserId(uid);
	}
	public int insertConnUser(int uid) {
		return this.gameConnMapper.insertConnUser(uid);
	}
	public int updateConnUser(int uid,int nowset) {
		return this.gameConnMapper.updateConnUser(uid, nowset);
	}
}
