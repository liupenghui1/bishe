package com.lph.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lph.entity.GameGuess;
import com.lph.service.GameGuessService;



@Controller
@RequestMapping("/lph")
public class GameGuessController {
	@Resource
	private GameGuessService gameGuessService;
	@ResponseBody
	@RequestMapping(value = "/findGameGuessSetByUserId/{uid}")
	public String findGameGuessSetById(@PathVariable("uid") int uid) {
		System.out.println(uid+"**");
		GameGuess gameGuess = this.gameGuessService.findGameGuessSetByUserId(uid);
		if(gameGuess==null) {
			int n=this.gameGuessService.insertGuessUser(uid);
			System.out.println(n);
			GameGuess gameGuess2 = this.gameGuessService.findGameGuessSetByUserId(uid);
			String string2=String.valueOf(gameGuess2.getNowset());
			System.out.println(string2+"****str2");
			return string2;
		}
		System.out.println(gameGuess.toString());
		String string=gameGuess.getNowset()+"";
		System.out.println(string);
		return string;
	}
	@ResponseBody
	@RequestMapping(value = "/updateGuessUser/{uid}/{nowset}")
	public String updateGuessUser(@PathVariable("uid") int uid,@PathVariable("nowset") int nowset) {
		System.out.println("nowset***"+nowset);
		int n=this.gameGuessService.updateGuessUser(uid, nowset);
		System.out.println(n+"****update");
		return n+"";
	}
}
