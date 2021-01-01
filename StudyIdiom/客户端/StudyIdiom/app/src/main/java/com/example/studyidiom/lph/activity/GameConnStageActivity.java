package com.example.studyidiom.lph.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.example.studyidiom.R;
import com.example.studyidiom.lph.view.GameStageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class GameConnStageActivity extends AppCompatActivity {
    private GameStageView gameStageView;
    private int nowPosition=-1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 200:
                    String message2 = (String) msg.obj;
                    Log.e("Connres",message2);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lph_activity_game_conn_stage);
        gameStageView=findViewById(R.id.gsv);
        Intent intent=getIntent();
        nowPosition = Integer.parseInt(intent.getStringExtra("conn_nowPosition"));
        Log.e("conn_nowPositionStage",nowPosition+"");
        if(nowPosition>0){
            gameStageView.setNowPosition(nowPosition);
            gameStageView.setOnGameItemClickListener(new GameStageView.OnGameItemClickListener() {
                @Override
                public void onGameItemClick(int position, boolean isLock) {
                    if (position > nowPosition + 1) {
                        Toast.makeText(getApplicationContext(), "请先完成之前的关卡", Toast.LENGTH_SHORT).show();
                    } else if (position == nowPosition + 1) {
                        Intent intent = new Intent(GameConnStageActivity.this, GameConnActivity.class);
                        intent.putExtra("Conncur", position);
                        startActivityForResult(intent,100);
                    } else {
                        Intent intent = new Intent(GameConnStageActivity.this, GameConnActivity.class);
                        intent.putExtra("Conncur", position);
                        startActivityForResult(intent,101);
                    }
                }
            });
        }
    }
    private void plusGameConnSet(String uId,int nowset) {
        new Thread() {
            @Override
            public void run() {
                try {
                    String string = getResources().getString(R.string.lphipConfig);
                    URL url = new URL("http://" + string + ":8080/InternetServer/lph/updateConnUser/" + uId+"/"+nowset);
                    Log.e("url", url.toString());
                    URLConnection conn = url.openConnection();
                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf-8"));
                    String info = reader.readLine();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 && resultCode==200){
            nowPosition+=1;
            gameStageView.setNowPosition(nowPosition);
            SharedPreferences sharedPreferences=getSharedPreferences("userInfo",MODE_PRIVATE);
            String uid=sharedPreferences.getString("uId","");
            plusGameConnSet(uid,nowPosition);
            Log.e("hello",nowPosition+"");
        }
    }

}