package com.lph.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lph.entity.GameConn;
import com.lph.service.GameConnService;




@Controller
@RequestMapping("/lph")
public class GameConnController {
	@Resource
	private GameConnService gameConnService;
	@ResponseBody
	@RequestMapping(value = "/findGameConnSetByUserId/{uid}")
	public String findGameConnSetById(@PathVariable("uid") int uid) {
		System.out.println(uid+"**");
		GameConn gameConn = this.gameConnService.findGameConnSetByUserId(uid);
		if(gameConn==null) {
			int n=this.gameConnService.insertConnUser(uid);
			System.out.println(n);
			GameConn gameConn2 = this.gameConnService.findGameConnSetByUserId(uid);
			String string2=String.valueOf(gameConn2.getNowset());
			System.out.println(string2+"****str2");
			return string2;
		}
		System.out.println(gameConn.toString());
		String string=gameConn.getNowset()+"";
		System.out.println(string);
		return string;
	}
	@ResponseBody
	@RequestMapping(value = "/updateConnUser/{uid}/{nowset}")
	public String updateConnUser(@PathVariable("uid") int uid,@PathVariable("nowset") int nowset) {
		System.out.println("nowset***"+nowset);
		int n=this.gameConnService.updateConnUser(uid, nowset);
		System.out.println(n+"****update");
		return n+"";
	}
}
