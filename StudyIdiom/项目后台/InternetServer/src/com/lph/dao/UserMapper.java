package com.lph.dao;

import java.util.List;

import com.lph.entity.User;


public interface UserMapper {
	public List<User> findAllUser();
	public User checkUserByName(String name,String pwd);
}
