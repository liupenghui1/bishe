package com.sxn.dao;

import java.util.List;
import com.sxn.entity.Animal;

public interface QueryMapper {

	public List<Animal> findIdiomsByInfo(String info);

}
