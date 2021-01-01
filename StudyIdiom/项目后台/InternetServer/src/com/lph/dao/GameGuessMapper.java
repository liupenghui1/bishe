package com.lph.dao;

import com.lph.entity.GameGuess;

public interface GameGuessMapper {
	public GameGuess findGameGuessSetByUserId(int uid);
	public int insertGuessUser(int uid);
	public int updateGuessUser(int uid,int nowset);
}
