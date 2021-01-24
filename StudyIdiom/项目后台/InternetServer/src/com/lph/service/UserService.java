package com.lph.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lph.dao.UserMapper;
import com.lph.entity.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Service
@Transactional(readOnly = false)
public class UserService {
	@Resource
	private UserMapper userMapper;
	@Transactional(readOnly=true)
	public List<User> findAllUser(){
		return this.userMapper.findAllUser();
	}
	@Transactional(readOnly=true)
	public User checkUserByName(String name,String pwd){
		return this.userMapper.checkUserByName(name, pwd);
	}
	public int checkRegisterUsers(String name,String pwd){
		return this.userMapper.checkRegisterUsers(name, pwd);
	}
	public String toJsonArray(List<User> list) {
		JSONArray array = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("id", list.get(i).getId());
			obj.put("name", list.get(i).getName());
			obj.put("pwd", list.get(i).getPwd());
			array.add(obj);
		}
		JSONObject objt = new JSONObject();
		objt.put("list", array);
		return objt.toString();
	}
}
