package com.sxn.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxn.entity.Animal;
import com.sxn.service.QueryService;

@Controller
@RequestMapping("/sxn")
public class QueryController {
	@Resource
	private QueryService queryService;
	String str;
	@ResponseBody
	@RequestMapping(value = "/findIdiomsByInfo",produces = "text/json;charset=utf-8") 
	public String findIdiomsByInfo(@RequestParam(value = "info") String info) {
		List<Animal> animals = this.queryService.findIdiomsByInfo(info);		
		if(animals.size()!=0) {
			str = this.queryService.toJsonArray(animals);
			str=str.substring(8,str.length()-1);
			return str;
		}else {
			return "wrong";
		}
		//@PathVariable("queryInfo") String info 
		
	}
}
