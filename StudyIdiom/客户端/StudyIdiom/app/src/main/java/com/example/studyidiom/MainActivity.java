package com.example.studyidiom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTabHost;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.studyidiom.fragment.GameFragment;
import com.example.studyidiom.fragment.MyFragment;
import com.example.studyidiom.fragment.SearchFragment;
import com.example.studyidiom.fragment.StudyFragment;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Map<String, ImageView> imageViewMap=new HashMap<>();
    private Map<String, TextView> textViewHashMap=new HashMap<>();
    private FragmentTabHost fragmentTabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentTabHost=findViewById(android.R.id.tabhost);
        Intent intent = getIntent();
        fragmentTabHost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);
        TabHost.TabSpec tabSpec1=fragmentTabHost.newTabSpec("tag1")
                .setIndicator(getTabSpecView("tag1",R.drawable.gray_study,"学习"));
        fragmentTabHost.addTab(tabSpec1, StudyFragment.class, null);
        TabHost.TabSpec tabSpec2=fragmentTabHost.newTabSpec("tag2")
                .setIndicator(getTabSpecView("tag2",R.drawable.gray_soso,"搜索"));
        fragmentTabHost.addTab(tabSpec2, SearchFragment.class, null);
        TabHost.TabSpec tabSpec3=fragmentTabHost.newTabSpec("tag3")
                .setIndicator(getTabSpecView("tag3",R.drawable.gray_game,"游戏"));
        fragmentTabHost.addTab(tabSpec3, GameFragment.class, null);
        TabHost.TabSpec tabSpec4=fragmentTabHost.newTabSpec("tag4")
                .setIndicator(getTabSpecView("tag4",R.drawable.gray_me,"我的"));
        fragmentTabHost.addTab(tabSpec4, MyFragment.class, null);

        //根据不同intent的action，返回指定的fragment
        if(intent!=null){
            switch (intent.getAction()){
//                case "mistake"://返回错题本fragment
//                    fragmentTabHost.setCurrentTab(1);
//                    imageViewMap.get("tag2").setImageResource(R.mipmap.mistakenblue);
//                    textViewHashMap.get("tag2").setTextColor(Color.argb(100,26,168,215));
//                    break;
                case "loginBackGame"://返回我的fragment
                    fragmentTabHost.setCurrentTab(2);
                    imageViewMap.get("tag3").setImageResource(R.drawable.blue_game);
                    textViewHashMap.get("tag3").setTextColor(Color.argb(100,26,168,215));
                    break;
//                case "跳广告"://跳广告转跳到首页：包括点击和自动播完
//                    fragmentTabHost.setCurrentTab(0);
//                    imageViewMap.get("tag1").setImageResource(R.mipmap.indexblue);
//                    textViewHashMap.get("tag1").setTextColor(Color.argb(100,26,168,215));
//                    break;
//                case "backTonear":
//                    fragmentTabHost.setCurrentTab(2);
//                    imageViewMap.get("tag3").setImageResource(R.mipmap.nearblue);
//                    textViewHashMap.get("tag3").setTextColor(Color.argb(100,26,168,215));
//                    break;
            }
        }else{
            fragmentTabHost.setCurrentTab(0);
            imageViewMap.get("tag1").setImageResource(R.drawable.blue_study);
            textViewHashMap.get("tag1").setTextColor(Color.argb(100,18,150,219));
        }

        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                textViewHashMap.get(tabId).setTextColor(Color.argb(100,18,150,219));
                switch (tabId){
                    case "tag1":
                        imageViewMap.get("tag1").setImageResource(R.drawable.blue_study);
                        imageViewMap.get("tag2").setImageResource(R.drawable.gray_soso);
                        imageViewMap.get("tag3").setImageResource(R.drawable.gray_game);
                        imageViewMap.get("tag4").setImageResource(R.drawable.gray_me);
                        textViewHashMap.get("tag2").setTextColor(getResources().getColor(android.R.color.darker_gray));
                        textViewHashMap.get("tag3").setTextColor(getResources().getColor(android.R.color.darker_gray));
                        textViewHashMap.get("tag4").setTextColor(getResources().getColor(android.R.color.darker_gray));
                        break;
                    case "tag2":
                        imageViewMap.get("tag1").setImageResource(R.drawable.gray_study);
                        imageViewMap.get("tag2").setImageResource(R.drawable.blue_soso);
                        imageViewMap.get("tag3").setImageResource(R.drawable.gray_game);
                        imageViewMap.get("tag4").setImageResource(R.drawable.gray_me);
                        textViewHashMap.get("tag1").setTextColor(getResources().getColor(android.R.color.darker_gray));
                        textViewHashMap.get("tag3").setTextColor(getResources().getColor(android.R.color.darker_gray));
                        textViewHashMap.get("tag4").setTextColor(getResources().getColor(android.R.color.darker_gray));
                        break;
                    case "tag3":
                        imageViewMap.get("tag1").setImageResource(R.drawable.gray_study);
                        imageViewMap.get("tag2").setImageResource(R.drawable.gray_soso);
                        imageViewMap.get("tag3").setImageResource(R.drawable.blue_game);
                        imageViewMap.get("tag4").setImageResource(R.drawable.gray_me);
                        textViewHashMap.get("tag1").setTextColor(getResources().getColor(android.R.color.darker_gray));
                        textViewHashMap.get("tag2").setTextColor(getResources().getColor(android.R.color.darker_gray));
                        textViewHashMap.get("tag4").setTextColor(getResources().getColor(android.R.color.darker_gray));
                        break;
                    case "tag4":
                        imageViewMap.get("tag1").setImageResource(R.drawable.gray_study);
                        imageViewMap.get("tag2").setImageResource(R.drawable.gray_soso);
                        imageViewMap.get("tag3").setImageResource(R.drawable.gray_game);
                        imageViewMap.get("tag4").setImageResource(R.drawable.blue_me);
                        textViewHashMap.get("tag1").setTextColor(getResources().getColor(android.R.color.darker_gray));
                        textViewHashMap.get("tag2").setTextColor(getResources().getColor(android.R.color.darker_gray));
                        textViewHashMap.get("tag3").setTextColor(getResources().getColor(android.R.color.darker_gray));
                        break;
                }
            }
        });
    }
    //加载选项卡布局:
    public View getTabSpecView(String tagId, int imageResId, String title){
        LayoutInflater layoutInflater=getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.tabspec_layout,null);
        ImageView imageView=view.findViewById(R.id.iv_icon);
        imageView.setImageResource(imageResId);
        imageViewMap.put(tagId,imageView);
        TextView textView=view.findViewById(R.id.tv_title);
        textView.setText(title);
        textViewHashMap.put(tagId,textView);
        return  view;
    }
}