package com.example.studyidiom.lph.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.studyidiom.lph.Model.ChengYu;

import java.util.ArrayList;
import java.util.List;


public class ChengYuDBAdapter {
	public DBManager dbHelper;
	private Context context;
	private SQLiteDatabase db;
	private static final String Table_Name = "cy_index";

	public ChengYuDBAdapter(Context context) {
		this.context = context;
		dbHelper = new DBManager(context);
		db = dbHelper.openDatabase();
	}



	/*
	 * 查询
	 */
	@SuppressWarnings("resource")
	public List<ChengYu> Query(ChengYu model) {
		
		Cursor c = null;
		String sql="select * from cy_index where 1=1";
		if (model != null && model.getId() > 0) {
			sql+=" and id="+model.getId();
			
		}
		if (model != null && model.getHead() != null && model.getHead() != "") {
			
			sql+=" and head='"+model.getHead()+"'";
		}
		if (model != null && model.getRear() != null && model.getRear() != "") {
			sql+=" and rear='"+model.getRear()+"'";
		
		}
		if (model != null && model.getName() != null && model.getName() != "") {
			sql+=" and name='"+model.getName()+"'";
		}
		Log.e("sql",sql);
		c=db.rawQuery(sql, null);
		List<ChengYu> links = new ArrayList<>();
		Log.e("c.count",c.getCount()+"");
		for (int i = 0; i < c.getCount(); i++) {
			
			c.moveToPosition(i);
			ChengYu link = new ChengYu();
			link.setId(c.getInt(c.getColumnIndex("id")));
			link.setName(c.getString(c.getColumnIndex("name")));
			link.setHead(c.getString(c.getColumnIndex("head")));
			link.setRear(c.getString(c.getColumnIndex("rear")));
			links.add(link);
			
		}
        Log.e("linksbef",links.toString());
        Log.e("linksbef",links.size()+"");

        //筛选出四字成语
        for (int i = 0; i < links.size(); i++) {
            int nlength=links.get(i).getName().length();
            if ( nlength!= 4) {
                links.remove(i);
            }
        }
        Log.e("linksaf",links.toString());
        Log.e("linksaf",links.size()+"");
		c.close();
	
		return links;

	}

	// 增加操作
	public boolean Insert(ChengYu model) {
	
		/* ContentValues */
		ContentValues cv = new ContentValues();
		cv.put("name", model.getName());
		cv.put("head", model.getHead());
		cv.put("rear", model.getRear());
		long row = db.insert(Table_Name, null, cv);
	
		return row > 0;
	}

	// 删除操作
	public boolean Delete(int id) {
		int result = 0;
	
		String where = id + " = ?";
		String[] whereValue = { Integer.toString(id) };
		result = db.delete(Table_Name, where, whereValue);
	
		return result > 0;
	}

	// 修改操作
	public boolean Update(ChengYu model) {
		
		String where = "id = ?";
		String[] whereValue = { Integer.toString(model.getId()) };
		ContentValues cv = new ContentValues();
		cv.put("name", model.getName());
		cv.put("head", model.getHead());
		cv.put("rear", model.getRear());
		int result = 0;
		result = db.update(Table_Name, cv, where, whereValue);
		
		return result > 0;
	}
}
