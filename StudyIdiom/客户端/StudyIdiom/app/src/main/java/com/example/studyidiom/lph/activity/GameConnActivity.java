package com.example.studyidiom.lph.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studyidiom.R;
import com.example.studyidiom.lph.Bll.CommonBll;
import com.example.studyidiom.lph.Model.ChengYu;

import java.util.ArrayList;
import java.util.List;

public class GameConnActivity extends AppCompatActivity {
    private Button daan01, daan02, daan03, daan04;
    private ImageButton btnTiShi, btnNext;
    private CommonBll cbll;
    private Listener listener;
    private int btnId = 0xff;
    private int sCrennWidth, sCreenHeight;
    private Handler mHandler = new Handler();// 全局handler
    private int timeDao = 5;// 5秒到计时
    private int RecordTime = 180;// 游戏时间
    private static int ReCount = 0;// 记录成语接龙接对的个数
    private TextView txtHeadView01, txtRecordTime, txtRecordCount, mTextViewPoints, needRecordCount;
    private LinearLayout line01, line02, line03, line04, lineDaanzone;
    private int TishiCount = 5;//提示的次数
    private int pointsBalance = 100;//当前积分
    private int nowPosition;
    private int totalCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lph_activity_game_conn);
        WindowManager wm = (WindowManager) getBaseContext().getSystemService(
                Context.WINDOW_SERVICE);
        sCrennWidth = wm.getDefaultDisplay().getWidth();
        TishiCount = Integer.parseInt(getResources().getString(R.string.tishiTime));

        Intent request = getIntent();
        nowPosition = request.getIntExtra("Conncur", -1);
        Log.e("Conncur",nowPosition+"");
        if (nowPosition != -1) {
            getViews();
            totalCount = nowPosition * Integer.parseInt(getResources().getString(R.string.finalCount));
            needRecordCount.setText(String.valueOf(totalCount));
            registListener();
            new Thread(new EnterGameTime()).start();
        }
    }

    /*
     * 初始化页面控件
     */
    private void getViews() {
        lineDaanzone = findViewById(R.id.lineDaanzone);
        txtHeadView01 = findViewById(R.id.firCode);
        txtRecordCount = findViewById(R.id.txtRecordCount);
        txtRecordTime = findViewById(R.id.txtRecordTime);
        needRecordCount = findViewById(R.id.needRecordCount);
        btnTiShi = findViewById(R.id.btnTiShi);
        btnNext = findViewById(R.id.btnNext);
        daan01 = findViewById(R.id.daan01);
        daan02 = findViewById(R.id.daan02);
        daan03 = findViewById(R.id.daan03);
        daan04 = findViewById(R.id.daan04);
        line01 = findViewById(R.id.answerZone1);
        line02 = findViewById(R.id.answerZone2);
        line03 = findViewById(R.id.answerZone3);
        line04 = findViewById(R.id.answerZone4);
        listener = new Listener();
    }

    //注册监听器
    private void registListener() {
        daan01.setOnClickListener(listener);
        daan02.setOnClickListener(listener);
        daan03.setOnClickListener(listener);
        daan04.setOnClickListener(listener);
        btnTiShi.setOnClickListener(listener);
    }
    private class Listener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.daan01:
                    break;
                case R.id.daan02:
                    daan02.setText("");
                    break;
                case R.id.daan03:
                    daan03.setText("");
                    break;
                case R.id.daan04:
                    daan04.setText("");
                    break;
                case R.id.btnTiShi:
                    if (TishiCount > 0) {
                        GiveTishiInfo();
                    } else {
                        DisplayToast("提示的机会已用完,不能再提示了。去做任务钻取积分可以再获得提示机会，每50个积分一次提示机会哦！当前积分：" + pointsBalance);
                    }

                    break;
            }
        }
    }
    private void GiveTishiInfo() {


        String head = daan01.getText().toString();

        List<ChengYu> list = cbll.GetChengYuListByHead(head);

        if (list != null && list.size() > 0) {
            char[] arr = list.get(0).getName().toCharArray();
            if (daan01.getText().length() < 1) {
                daan01.setText(String.valueOf(arr[0]));
            } else if (daan02.getText().length() < 1) {
                daan02.setText(String.valueOf(arr[1]));
            } else if (daan03.getText().length() < 1) {
                daan03.setText(String.valueOf(arr[2]));
            } else if (daan04.getText().length() < 1) {
                daan04.setText(String.valueOf(arr[3]));
            }
            if (daan01.getText().length() > 0 && daan02.getText().length() > 0
                    && daan03.getText().length() > 0
                    && daan04.getText().length() > 0) {
                Btn4HasValue();
            }
        } else {
            DisplayToast("该字开头的成语已经不存在下一个成语，系统将跳转到随机产生个成语首字");
            // 取下一个成语
            String inithead = cbll.GetHeadRandom();
            daan01.setText(inithead);
            txtHeadView01.setText(inithead);
            InitAnSwerChoicZone(inithead);
            daan02.setText("");
            daan03.setText("");
            daan04.setText("");
        }
        TishiCount--;
    }
    /*
     * 提示信息
     */
    private void DisplayToast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
    /*
     * 按钮4有值后将进行校验
     */
    private void Btn4HasValue() {
        if (CheckIsValid()) {
            ReCount++;
            if (ReCount < totalCount) {
                // 取下一个成语
                daan01.setText(daan04.getText());
                txtHeadView01.setText(daan04.getText());
                InitAnSwerChoicZone(daan04.getText().toString());
                txtRecordCount.setText(String.valueOf(ReCount));
                daan02.setText("");
                daan03.setText("");
                daan04.setText("");
            } else {
                txtRecordCount.setText(String.valueOf(ReCount));
                btnTiShi.setVisibility(View.INVISIBLE);
                btnNext.setVisibility(View.VISIBLE);
                DisplayToast("闯关成功，共答对" + ReCount + "道题");
                btnNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClass(GameConnActivity.this, GameConnStageActivity.class);
                        setResult(200, intent);
                        finish();
                    }
                });
                ReCount = 0;
                Log.e("recount", ReCount + "");
            }

        } else {
            if (cbll.GetChengYuCountByHead(daan01.getText().toString()) > 0) {
                DisplayToast("猜错了！");
            } else {
                DisplayToast("该字开头的成语已经不存在下一个成语，系统将跳转到随机产生个成语首字");
            }
            // 取下一个成语
            String inithead = cbll.GetHeadRandom();
            daan01.setText(inithead);
            txtHeadView01.setText(inithead);
            InitAnSwerChoicZone(inithead);
            daan02.setText("");
            daan03.setText("");
            daan04.setText("");


        }

    }
    /*
     * 初始化答案选择区域（共36个汉子选择项）
     */
    private void InitAnSwerChoicZone(String str) {
        daan01.setText(str);
        txtHeadView01.setText(str);
        line01.removeAllViews();
        line02.removeAllViews();
        line03.removeAllViews();
        line04.removeAllViews();
        ArrayList<String> list = new ArrayList<>();
        try {
            list = cbll.GetChoicAnswer(str, 36);

        } catch (Exception e1) {
            // TODO 自动生成的 catch 块
            e1.printStackTrace();
        }
        Log.e("size", list.size() + "");
        for (int i = 0; i < list.size(); i++) {
            Button btn = new Button(this);
            btn.setId(btnId + i);

            btn.setLayoutParams(new ViewGroup.LayoutParams(sCrennWidth / 9, 28 * 5));
            btn.setTextSize(12);
            String ranHanzi = list.get(i);
//            Log.e("ranHanzi",i+"***"+ranHanzi);
            btn.setText(ranHanzi);

            if (i < 9) {
                line01.addView(btn);
            } else if (i >= 9 && i < 18) {
                line02.addView(btn);
            } else if (i >= 18 && i < 27) {
                line03.addView(btn);
            } else {
                line04.addView(btn);
            }
            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    Button bttn = (Button) arg0;
                    if (daan01.getText().length() < 1) {
                        daan01.setText(bttn.getText());
                    } else if (daan02.getText().length() < 1) {
                        daan02.setText(bttn.getText());
                    } else if (daan03.getText().length() < 1) {
                        daan03.setText(bttn.getText());
                    } else if (daan04.getText().length() < 1) {
                        daan04.setText(bttn.getText());
                    }
                    if (daan01.getText().length() > 0
                            && daan02.getText().length() > 0
                            && daan03.getText().length() > 0
                            && daan04.getText().length() > 0) {
                        Btn4HasValue();
                    }

                }
            });
        }

    }
    /*
     * 检查成语选择是否正确
     */
    private boolean CheckIsValid() {
        String result = daan01.getText().toString()
                + daan02.getText().toString() + daan03.getText().toString()
                + daan04.getText().toString();
        return cbll.GetHeadByDaan(result);
    }
    class EnterGameTime implements Runnable {

        @Override
        public void run() {
            // TODO 自动生成的方法存根

            while (timeDao > 0) {

                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO 自动生成的方法存根
                        txtHeadView01.setText(timeDao + "");
                    }
                });
                timeDao--;
                try {
                    Thread.sleep(1000);// 线程休眠一秒钟 这个就是倒计时的间隔时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 下面是倒计时结束逻辑
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    // TODO 自动生成的方法存根
                    cbll = new CommonBll(GameConnActivity.this);
                    String inithead = cbll.GetHeadRandom();
                    Log.e("inithead", inithead);
                    InitAnSwerChoicZone(inithead);
                    new Thread(new StartGame()).start();
                }
            });

        }// 倒计时逻辑子线程
    }
    class StartGame implements Runnable {

        @Override
        public void run() {
            // TODO 自动生成的方法存根
            RecordTime = Integer.parseInt(txtRecordTime.getText().toString());
            while (RecordTime > 0) {
                mHandler.post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO 自动生成的方法存根
                        txtRecordTime.setText(String.valueOf(RecordTime));
                    }
                });
                RecordTime--;

                try {
                    Thread.sleep(1000);// 线程休眠一秒钟 这个就是倒计时的间隔时间
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 下面是倒计时结束逻辑
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    // TODO 自动生成的方法存根
                    if (ReCount < totalCount) {
                        DisplayToast("闯关失败");
                        nowPosition--;
                        btnNext.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.setClass(GameConnActivity.this, GameConnStageActivity.class);
                                setResult(300, intent);
                                finish();
                            }
                        });
                    } else {
                        DisplayToast("闯关成功，共答对" + ReCount + "道题");
                        btnNext.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent.setClass(GameConnActivity.this, GameConnStageActivity.class);
                                setResult(200, intent);
                                finish();
                            }
                        });
                    }
                    ReCount = 0;
                    txtHeadView01.setTextColor(Color.RED);
                    txtHeadView01.setText("Game Over");
                    lineDaanzone.removeAllViews();
                    line01.removeAllViews();
                    line02.removeAllViews();
                    line03.removeAllViews();
                    line04.removeAllViews();
                    btnTiShi.setVisibility(View.INVISIBLE);
                    btnNext.setVisibility(View.VISIBLE);
                }
            });
        }
    }
}