package com.lph.service;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lph.dao.GameGuessMapper;
import com.lph.entity.GameGuess;

@Service
@Transactional(readOnly = false)
public class GameGuessService {
	@Resource
	private GameGuessMapper gameGuessMapper;

	public GameGuess findGameGuessSetByUserId(int uid){
		return this.gameGuessMapper.findGameGuessSetByUserId(uid);
	}
	public int insertGuessUser(int uid) {
		return this.gameGuessMapper.insertGuessUser(uid);
	}
	public int updateGuessUser(int uid,int nowset) {
		return this.gameGuessMapper.updateGuessUser(uid, nowset);
	}
}
