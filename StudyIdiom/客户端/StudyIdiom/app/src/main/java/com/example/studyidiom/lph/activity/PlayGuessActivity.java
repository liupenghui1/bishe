package com.example.studyidiom.lph.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.studyidiom.R;
import com.example.studyidiom.dao.AnimalDao;
import com.example.studyidiom.dao.GameDao;
import com.example.studyidiom.entity.Animal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class PlayGuessActivity extends AppCompatActivity {
    int score = 0;// 总成绩
    int number = 1;// 题目数目
    int index = 0;//从数据库中选出成语的id数组下标
    private boolean flag = false;
    private TextView tvRightOrWrong;
    private ImageButton ibNext, ibSubmit;
    private TextView tvExplain;
    private RadioButton rbPhraseOne, rbPhraseTwo, rbPhraseThree, rbPhraseFour;//获取控件对象
    private Random random;// 随机数
    private AnimalDao animalDao;
    private GameDao gameDao;
    private List<Animal> animalList;
    private Animal animal, animalTwo, animalThree, animalFour;// 随机生成的成语对象
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private int[] array = {0, 1, 2, 3};// 整形数组，用于生成随机顺序显示选项
    private String answer;// 选择的答案
    private int[] idiom_arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};//从数据库中选出成语的id
    private int cur;//获取当前游戏的关卡数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lph_activity_play_guess);
        getViews();
        cur = getIntent().getIntExtra("cur", -1);
        Log.e("curpg", cur + "");
        pref = getSharedPreferences("FourPhrase", MODE_PRIVATE);// 将正确成语和随机生成的三个成语保存到文档
        editor = pref.edit();// 获取SharedPreferences.Editor对象
        //从数据库中选出成语的id
        currentArray(idiom_arr);
        /* 获取随机值对应的成语解释 */
        getNumber(idiom_arr[index]);

        /* 显示解释到TextView */
        showPhrase();

        /* 获取三个随机成语和一个正确成语的选项 */
        getFourPhrase();

        /* 显示到RadioButton */
        showRadioButton();

    }

    private void getViews() {
        tvExplain = findViewById(R.id.tvExplain);
        rbPhraseOne = findViewById(R.id.rbPhraseOne);
        rbPhraseTwo = findViewById(R.id.rbPhraseTwo);
        rbPhraseThree = findViewById(R.id.rbPhraseThree);
        rbPhraseFour = findViewById(R.id.rbPhraseFour);
        tvRightOrWrong = findViewById(R.id.tvRightOrWrong);
        ibSubmit = findViewById(R.id.ibSubmit);
        ibNext = findViewById(R.id.ibNext);
    }

    /* 获取随机值对应的成语 */
    private void getNumber(int idx) {

        animalList = new ArrayList<Animal>();
        animalDao = AnimalDao.getInstance(this);
        animalList = animalDao.getAllAnimals();
        String id = Integer.toString(idx);
        Log.e("num", id);
        gameDao = GameDao.getInstance(this);
        animal = gameDao.getPhrase(id);
        Log.e("answer",animal.getName());
        animalList = new ArrayList<Animal>();
        animalDao = AnimalDao.getInstance(this);
        animalList = animalDao.getAllAnimals();
    }

    /* 显示解释到页面 */
    private void showPhrase() {
        tvExplain.setText(animal.getExplain());
    }

    /* 获取四个成语选项 */
    private void getFourPhrase() {
        random = new Random();

        String two = null, three = null, four = null;
        two = Integer.toString(random.nextInt(animalList.size()));
        three = Integer.toString(random.nextInt(animalList.size()));
        four = Integer.toString(random.nextInt(animalList.size()));

        gameDao = GameDao.getInstance(this);
        animalTwo = gameDao.getPhrase(two); // 获取随机生成值对应的成语
        animalThree = gameDao.getPhrase(three);
        animalFour = gameDao.getPhrase(four);


        editor.putString("0", animal.getName());// 将四个成语存入文档
        editor.putString("1", animalTwo.getName());
        editor.putString("2", animalThree.getName());
        editor.putString("3", animalFour.getName());
        editor.commit();

        // 随机生成四个不重复的值作为显示顺序
        Log.i("chengyut", animal.getName() + "," + animalTwo.getName() + "," + animalThree.getName() + "," + animalFour.getName());
        shuffle(array);
        Log.i("MainActivity", "*****" + array[0] + "," + array[1] + ","
                + array[2] + "," + array[3]);
    }

    private void currentArray(int[] arr) {

        if (cur != -1) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] += 18 * (cur - 1);
            }
            Log.e("bef", Arrays.toString(arr));
            shuffle(arr);
            Log.e("aft", Arrays.toString(arr));
        }
    }
    /*随机生成四个不重复的值作为显示顺序
    * 原理:
    * 循环遍历该数组，在每次遍历中产生一个0 ~ length - 1的数，该数代表本次循环要随机交换的位置。
        将本次循环当前位置的数和随机位置的数进行交换。 */

    private void shuffle(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            int currentRandom = new Random().nextInt(len - 1);
            int current = arr[i];
            arr[i] = arr[currentRandom];
            arr[currentRandom] = current;
        }
    }

    /* 显示到RadioButton */
    private void showRadioButton() {
        rbPhraseOne.setText(pref.getString(Integer.toString(array[0]), ""));
        rbPhraseTwo.setText(pref.getString(Integer.toString(array[1]), ""));
        rbPhraseThree.setText(pref.getString(Integer.toString(array[2]), ""));
        rbPhraseFour.setText(pref.getString(Integer.toString(array[3]), ""));
    }

    /* 提交答案 */
    public void AnswerSubmit(View view) {
        switch (view.getId()) {
            case R.id.ibSubmit:

                if (rbPhraseOne.isChecked())
                    answer = rbPhraseOne.getText().toString();
                else if (rbPhraseTwo.isChecked())
                    answer = rbPhraseTwo.getText().toString();
                else if (rbPhraseThree.isChecked())
                    answer = rbPhraseThree.getText().toString();
                else
                    answer = rbPhraseFour.getText().toString();
                if (answer == animal.getName()) {
                    flag = true;
                    tvRightOrWrong.setText("真棒，回答正确");
                    tvRightOrWrong.setTextColor(Color.rgb(7, 200, 12));
                    ibSubmit.setClickable(false);      //回答正确或错误，提交按钮不能被点击，防止一个一个尝试获取答案加分
                    score += 10;
                    index++;
                    Toast.makeText(this, "当前得分为：" + score + ",所做题数为：" + number, Toast.LENGTH_SHORT).show();
                } else {
                    flag = true;
                    tvRightOrWrong.setText("啊偶，回答错了");
                    tvRightOrWrong.setTextColor(Color.rgb(255, 00, 00));
                    ibSubmit.setClickable(false);
                    index++;
//                    Toast.makeText(this,"当前得分为："+score, Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "当前得分为：" + score + ",所做题数为：" + number, Toast.LENGTH_SHORT).show();
                }

                if (score == Integer.parseInt(getResources().getString(R.string.score_30)) && number <= 10) {
                    tvRightOrWrong.setText("恭喜，闯关成功");
                    tvRightOrWrong.setTextColor(Color.rgb(7, 200, 12));
//                    ibNext.setClickable(false);      //闯关成功或失败，下一道题的按钮不能被点击
                    ibNext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(PlayGuessActivity.this, GameGuessStageActivity.class);
                            setResult(200, intent);
                            finish();
                        }
                    });
//                    Toast.makeText(this,"最终得分为："+score, Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "所做题数为：" + number, Toast.LENGTH_SHORT).show();
                } else if (score <= Integer.parseInt(getResources().getString(R.string.score_30)) && number >= 10) {
                    tvRightOrWrong.setText("抱歉，闯关失败");
                    tvRightOrWrong.setTextColor(Color.rgb(255, 00, 00));
//                    ibNext.setClickable(false);
                    ibNext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(PlayGuessActivity.this, GameGuessStageActivity.class);
                            setResult(300, intent);
                            finish();
                        }
                    });
//                    Toast.makeText(this,"最终得分为："+score, Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "最终得分为：" + score + ",所做题数为：" + number, Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.ibNext:   //生成下一道题
                if (flag) {
                    clearPhrase();//清空TextView和RadioButton属性
                    getNumber(idiom_arr[index]);
                    showPhrase();
                    getFourPhrase();
                    showRadioButton();
                    number += 1;  //题目数目加一
                } else {
                    Toast.makeText(getApplicationContext(), "请先验证答案", Toast.LENGTH_SHORT).show();
                }
                flag = false;
                break;
        }
    }

    public void clearPhrase() {     //跳到下一道题，删除之前控件的属性
        tvRightOrWrong.setText(" ");      //清空提示语
        ibSubmit.setClickable(true);    //提交按钮可以点击
        rbPhraseOne.setChecked(false);    //单选按钮都不被选中
        rbPhraseTwo.setChecked(false);
        rbPhraseThree.setChecked(false);
        rbPhraseFour.setChecked(false);
    }

}