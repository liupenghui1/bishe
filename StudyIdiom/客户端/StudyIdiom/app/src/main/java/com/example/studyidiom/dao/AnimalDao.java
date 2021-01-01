package com.example.studyidiom.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.studyidiom.db.DBOpenHelper;
import com.example.studyidiom.entity.Animal;

import java.util.ArrayList;
import java.util.List;


public class AnimalDao {
    private static AnimalDao animalDao;
    private SQLiteDatabase db;

    /**
     * 将构建方法私有化
     */
    private AnimalDao(Context context) {
        DBOpenHelper dbHelper = new DBOpenHelper(context);
        db = dbHelper.openDatabase();
    }

    /**
     * 获取AnimalDao的实例
     */
    public synchronized static AnimalDao getInstance(Context context) {
        if (animalDao == null) {
            animalDao = new AnimalDao(context);
        }
        return animalDao;
    }

    /**
     * 从数据库读取所有的动物类成语
     */
    public List<Animal> getAllAnimals() {
        List<Animal> list = new ArrayList<Animal>();
        Cursor cursor = db.query("animal", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Animal animal = new Animal();
                animal.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                animal.setName(cursor.getString(cursor.getColumnIndex("name")));
                animal.setPronounce(cursor.getString(cursor
                        .getColumnIndex("pronounce")));
                animal.setAntonym(cursor.getString(cursor
                        .getColumnIndex("antonym")));
                animal.setHomoionym(cursor.getString(cursor
                        .getColumnIndex("homoionym")));
                animal.setDerivation(cursor.getString(cursor
                        .getColumnIndex("derivation")));
                animal.setExplain(cursor.getString(cursor
                        .getColumnIndex("examples")));
                list.add(animal);
            } while (cursor.moveToNext());
        }
        return list;
    }

}
