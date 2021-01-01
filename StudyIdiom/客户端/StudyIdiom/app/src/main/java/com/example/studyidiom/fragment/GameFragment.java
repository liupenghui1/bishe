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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studyidiom.R;
import com.example.studyidiom.lph.activity.GameConnStageActivity;
import com.example.studyidiom.lph.activity.GameGuessStageActivity;
import com.example.studyidiom.lph.activity.LoginActivity;
import com.example.studyidiom.lph.activity.PlayGuessActivity;
import com.example.studyidiom.lph.view.GameStageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;


public class GameFragment extends Fragment {
    private TextView tvGameConn;
    private TextView  tvGameGuess;
    private int id;
    private View view;
    private int nowPosition;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    String message = (String) msg.obj;
                    nowPosition = Integer.parseInt(message);
                    Log.e("guess_nowPosition", message);
                    Intent intent=new Intent(getContext(), GameGuessStageActivity.class);
                    intent.putExtra("guess_nowPosition",nowPosition+"");
                    startActivity(intent);
                    getActivity().finish();
                    break;
                case 200:
                    String message2 = (String) msg.obj;
                    int nowPosition2 = Integer.parseInt(message2);
                    Log.e("conn_nowPosition", message2);
                    Intent intent2=new Intent(getContext(), GameConnStageActivity.class);
                    intent2.putExtra("conn_nowPosition",nowPosition2+"");
                    startActivity(intent2);
                    getActivity().finish();
                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //用户是否登录
        if(!checkUserIsLogin()){//未登录
            view = inflater.inflate(R.layout.login_register, container,false);
            Button btn_subfirst=view.findViewById(R.id.btn_subfirst);
            Button btn_resfirst=view.findViewById(R.id.btn_resfirst);
            btn_subfirst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
            btn_resfirst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            return view;
        }
            view=inflater.inflate(R.layout.lph_game_fragment_layout,container,false);
            //初始化控件
            initController();
            return view;


    }
    //初始化控件
    private void initController() {
        tvGameGuess=view.findViewById(R.id.tvGameGuess);
        tvGameConn=view.findViewById(R.id.tvGameConn);
        tvGameGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGameGuessSet();
            }
        });
        tvGameConn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGameConnSet();
            }
        });
    }
    //用户是否登录
    private boolean checkUserIsLogin() {
        SharedPreferences sharedPreferences=getContext().getSharedPreferences("userInfo",MODE_PRIVATE);
        String uName=sharedPreferences.getString("name","");
        String uTel=sharedPreferences.getString("phone","");
        if (uName.equals("") && uTel.equals("")){//用户名或者密码两个都为空，就是用户没登录
            Log.e("pp",false+"");
            return false;
        }else{//只要用户名或者密码有一个不为空，就是用户登录了
            id= Integer.parseInt(getContext().getSharedPreferences("userInfo",MODE_PRIVATE).getString("uId","0"));
            Log.e("pp",true+"");
            return true;
        }
    }
    private void getGameGuessSet() {
        new Thread() {
            @Override
            public void run() {
                try {
                    String string = getResources().getString(R.string.lphipConfig);
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("userInfo", MODE_PRIVATE);
                    String uId = sharedPreferences.getString("uId", "-1");
                    URL url = new URL("http://" + string + ":8080/InternetServer/lph/findGameGuessSetByUserId/" + uId);
                    Log.e("url", url.toString());
                    URLConnection conn = url.openConnection();
                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
                    String info = reader.readLine();
                    Log.e("info",info);
                    Message message = Message.obtain();
                    message.what = 100;
                    message.obj = info;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    private void getGameConnSet() {
        new Thread() {
            @Override
            public void run() {
                try {
                    String string = getResources().getString(R.string.lphipConfig);
                    SharedPreferences sharedPreferences = getContext().getSharedPreferences("userInfo", MODE_PRIVATE);
                    String uId = sharedPreferences.getString("uId", "-1");
                    URL url = new URL("http://" + string + ":8080/InternetServer/lph/findGameConnSetByUserId/" + uId);
                    Log.e("url", url.toString());
                    URLConnection conn = url.openConnection();
                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
                    String info = reader.readLine();
                    Log.e("info",info);
                    Message message = Message.obtain();
                    message.what = 200;
                    message.obj = info;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    //用户是否登录
//    private boolean checkUserIsLogin() {
//        SharedPreferences sharedPreferences=getContext().getSharedPreferences("userInfo",MODE_PRIVATE);
//        String uName=sharedPreferences.getString("name","");
//        String uTel=sharedPreferences.getString("phone","");
//        if (uName.equals("") && uTel.equals("")){//用户名或者密码两个都为空，就是用户没登录
//            return false;
//        }else{//只要用户名或者密码有一个不为空，就是用户登录了
//            id= Integer.parseInt(getContext().getSharedPreferences("userInfo",MODE_PRIVATE).getString("uId","0"));
//            return true;
//        }
//    }
}
