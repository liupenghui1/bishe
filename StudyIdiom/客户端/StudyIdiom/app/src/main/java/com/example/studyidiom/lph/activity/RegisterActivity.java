package com.example.studyidiom.lph.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.studyidiom.MainActivity;
import com.example.studyidiom.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class RegisterActivity extends AppCompatActivity {
    private EditText ed_name_res;
    private EditText ed_pwd_res;
    private Button btn_register;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String checkMessage = (String) msg.obj;
            switch (msg.what) {
                case 100:
                    Log.e("handler", 100 + "");
                    if (checkMessage.startsWith("right")) {
                        Toast.makeText(getApplicationContext(),"用户注册成功",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setAction("loginBackGame");
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(),"用户名或密码注册错误",Toast.LENGTH_LONG).show();
                    }
                    break;
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register_layout);
        ed_name_res = findViewById(R.id.ed_name_res);
        ed_pwd_res= findViewById(R.id.ed_pwd_res);
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ed_name_res.getText().toString();
                String pwd = ed_pwd_res.getText().toString();
                Log.e("name", name);
                Log.e("pwd", pwd);
                checkRegisterUsers(name, pwd);

            }
        });
    }
    private void checkRegisterUsers(String name, String pwd) {
        new Thread() {
            @Override
            public void run() {
                try {
                    String string=getResources().getString(R.string.lphipConfig);
                    URL url = new URL("http://" +string + ":8080/InternetServer/lph/checkRegisterUsers?name="+name+"&pwd="+pwd);
                    Log.e("url",url.toString());
                    URLConnection conn = url.openConnection();
                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
                    String info = reader.readLine();
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
}