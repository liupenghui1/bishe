package com.example.studyidiom.sxn.dao;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.studyidiom.R;
import com.example.studyidiom.entity.Animal;
import com.example.studyidiom.fragment.SearchFragment;
import com.example.studyidiom.sxn.activity.AnimalAdapter;
import com.example.studyidiom.sxn.util.DialogUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import androidx.annotation.NonNull;

public class AnimalSearchDao {
    private List<Animal> animals;
    private String info;
    private ListView lvQueryAfter;
    private Context mc;
    private Gson gson;
    private AnimalAdapter animalAdapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case R.layout.sxn_idiom_item:
                    info = (String) msg.obj;
                    gson=new Gson();
                    animals = gson.fromJson(info, new TypeToken<List<Animal>>() {
                    }.getType());
                    animalAdapter = new AnimalAdapter(mc,R.layout.sxn_idiom_item,animals);
                    lvQueryAfter.setAdapter(animalAdapter);
                    lvQueryAfter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            /* 定义对话框中提示语句 */
                            Animal animal=animals.get(position);
                            String result = animal.getName() + "\n" + animal.getPronounce()
                            + "\n【解释】：" + animal.getExplain() + "\n【近义词】："
                            + animal.getHomoionym() + "\n【反义词】："
                            + animal.getAntonym() + "\n【来源】："
                            + animal.getDerivation() + "\n【示例】："
                            + animal.getExamples();
                            Log.e("ddd",result+"//////////");
                            DialogUtil.showDialog(result,mc);
                        }
                    });
                    break;
            }
        }
    };
    public void sendToServerSearch(final String str, final int layout, final ListView lv) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(str);
                    URLConnection conn = url.openConnection();
                    Log.e("1",conn.toString());
                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
                    String info = reader.readLine();
                    Message msg = Message.obtain();
                    msg.obj = info;
                    lvQueryAfter=lv;
                    lvQueryAfter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Log.e("点点","sssssssssssss");
                        }
                    });
                    msg.what=layout;
                    handler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
    public AnimalSearchDao(Context mc) {
        super();
        this.mc = mc;
    }
}
