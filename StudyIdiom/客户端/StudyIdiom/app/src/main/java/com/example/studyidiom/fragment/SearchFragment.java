package com.example.studyidiom.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studyidiom.R;
import com.example.studyidiom.entity.Animal;
import com.example.studyidiom.sxn.activity.AnimalAdapter;
import com.example.studyidiom.sxn.dao.AnimalQueryDao;
import com.example.studyidiom.sxn.util.DialogUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment {
    @Nullable
    private List<Animal> animals;
    private AnimalQueryDao queryDao;
    private ListView lvQueryAfter;
    private EditText etSearch;
    private Button btnSearch;
    private TextView tvName;
    private View view;
    private String elem;
    private String info;
    private Gson gson;

    private AnimalAdapter animalAdapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 100:
                    info = (String) msg.obj;
                    gson=new Gson();
                    Log.e("fgh让他有机会、、、",info);
                    if (info.contains("wrong")){
                        tvName.setText("找不到咯~");
                    }else {
                        animals = gson.fromJson(info,new TypeToken<List<Animal>>(){
                        }.getType());
                        animalAdapter = new AnimalAdapter(getContext(),R.layout.sxn_idiom_item,animals);
                        lvQueryAfter.setAdapter(animalAdapter);
                        lvQueryAfter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                /* 定义对话框中提示语句 */
                                Animal animal=animals.get(position);
                                String result = animal.getName() + "\n" + "\n【类型】："
                                        + animal.getPronounce() + "\n【解释】："
                                        + animal.getExplain() + "\n【近义词】："
                                        + animal.getHomoionym() + "\n【反义词】："
                                        + animal.getAntonym() + "\n【来源】："
                                        + animal.getDerivation() + "\n【示例】："
                                        + animal.getExamples();
                                Log.e("ddd",result+"//////////");
                                DialogUtil.showDialog(result,getContext());
                            }
                        });
                    }

                    break;
            }
        }
    };
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.search_fragment_layout,container,false);
        initById();
        btnSearch.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            search();
        }
        });
        return view;
    }
    private void search() {
        //获得需要查询的元素
        elem = etSearch.getText().toString().trim();
        if (elem!=null && elem!=" ") {
            Log.e("dfg",elem+"=========");
            sendToServerSearch(100);
        }
    }
    private void initById(){
        etSearch =view.findViewById(R.id.etSearch);
        btnSearch =view.findViewById(R.id.btnSearch);
        lvQueryAfter =view.findViewById(R.id.lvQuery);
        tvName=view.findViewById(R.id.sxn_tvName);
    }
    public void sendToServerSearch(final int layout) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    String string=getResources().getString(R.string.lphipConfig);
                    URL url = new URL("http://" +string +":8080/InternetServer/sxn/findIdiomsByInfo?info="+elem);
                    URLConnection conn = url.openConnection();
                    Log.e("1",conn.toString());
                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
                    String info = reader.readLine();
                    Message msg = Message.obtain();
                    msg.obj = info;
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
}
