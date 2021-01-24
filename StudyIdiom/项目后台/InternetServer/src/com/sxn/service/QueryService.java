package com.sxn.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lph.entity.User;
import com.sxn.dao.QueryMapper;
import com.sxn.entity.Animal;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
@Transactional(readOnly = false)
public class QueryService {
	@Resource
	private QueryMapper queryMapper;
	//根据部分关键字查询单词
	public List<Animal> findIdiomsByInfo(String info){
		System.out.println(info+"1:::::::::");
		return this.queryMapper.findIdiomsByInfo(info);
	}
	//转json传输
	public String toJsonArray(List<Animal> animals) {
		JSONArray array = new JSONArray();
		for (int i = 0; i < animals.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("id", animals.get(i).getId());
			obj.put("name", animals.get(i).getName());
			obj.put("pronounce", animals.get(i).getPronounce());
			obj.put("antonym", animals.get(i).getAntonym());
			obj.put("homoionym", animals.get(i).getHomoionym());
			obj.put("derivation", animals.get(i).getDerivation());
			obj.put("example", animals.get(i).getexample());
			obj.put("explain", animals.get(i).getExplain());
			array.add(obj);
		}
		JSONObject objt = new JSONObject();
		objt.put("list", array);
		return objt.toString();
	}
}
