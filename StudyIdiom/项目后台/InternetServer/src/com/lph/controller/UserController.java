package com.lph.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lph.entity.User;
import com.lph.service.UserService;


@Controller
@RequestMapping("/lph")
public class UserController {
	@Resource
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value = "/findAllUser", produces = "text/json;charset=utf-8")
	public String findComments() {
		List<User> users = this.userService.findAllUser();
		String string = this.userService.toJsonArray(users);
		System.out.println("users+***");
		System.out.println(string);
		return string;
	}
	@ResponseBody
	@RequestMapping(value = "/checkUserByName")
	public String checkUserByName(@RequestParam(value = "name") String name, @RequestParam(value="pwd") String pwd) {
		System.out.println("name="+name);
		System.out.println("pwd="+pwd);
		User user = this.userService.checkUserByName(name, pwd);
		if(user!=null) {
			String string="right"+user.getId();
			System.out.println(string);
			return string;
		}
		return "wrong";
	}
}
