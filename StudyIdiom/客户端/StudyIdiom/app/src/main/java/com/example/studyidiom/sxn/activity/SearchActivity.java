package com.example.studyidiom.sxn.activity;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.studyidiom.R;
import com.example.studyidiom.entity.Animal;
import com.example.studyidiom.sxn.dao.AnimalQueryDao;
import com.example.studyidiom.sxn.util.DialogUtil;

import java.util.List;
/*
*界面：查询主页
* 任务：获得需要查询内容并与后台交互、显示查询结果
 */
public class SearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
