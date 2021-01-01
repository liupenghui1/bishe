package com.example.studyidiom.lph.Bll;

import android.content.Context;
import android.util.Log;


import com.example.studyidiom.R;
import com.example.studyidiom.lph.Dao.ChengYuDBAdapter;
import com.example.studyidiom.lph.Model.ChengYu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


//通用方法
public class CommonBll {
    private final Context context;
    private final ChengYuDBAdapter cydll;

    public CommonBll(Context con) {
        this.context = con;
        cydll = new ChengYuDBAdapter(context);

    }

    // 随机产生一个汉字
    public String getChineseCharacter() throws Exception {
        Random r = new Random();
        long seed = r.nextInt(9999);

        String str = null;
        int highPos, lowPos;
        Random random = new Random(seed);
        highPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = 161 + Math.abs(random.nextInt(93));
        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(highPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();
        str = new String(b, "GBK");
        Log.e("随机产生的汉字为：", str);
        return str;
    }

    /*
     * 根据首字获取包含正确成语和指定个数的汉字随机打乱顺序
     */

    public ArrayList<String> GetChoicAnswer(String str, int choicCount)
            throws Exception {

        ArrayList<String> arr = new ArrayList<>();
        ChengYu model = new ChengYu();
        model.setHead(str);
        Log.e("mod", model.toString());
        List<ChengYu> list = cydll.Query(model);

        int zhenDaanCount = list.size() * 3;
        Log.e("listsize", list.size() + "");
        arr.add(str);
        if (zhenDaanCount < choicCount - 1) {

            for (int i = 0; i < list.size(); i++) {
                arr.add(String.valueOf(list.get(i).getName().toCharArray()[1]));
                arr.add(String.valueOf(list.get(i).getName().toCharArray()[2]));
                arr.add(String.valueOf(list.get(i).getName().toCharArray()[3]));
                String teststr = String.valueOf(list.get(i).getName()
                        .toCharArray()[1]);
                teststr += String
                        .valueOf(list.get(i).getName().toCharArray()[2]);
                teststr += String
                        .valueOf(list.get(i).getName().toCharArray()[3]);
                Log.i("teststr<choicCount", teststr);
            }
            for (int j = 0; j < choicCount - zhenDaanCount - 1; j++) {
                String teststr = getChineseCharacter();
//				Log.i("测试产生的-1位置随机产生汉子（zhenDaanCount<choicCount）：", teststr);
                arr.add(teststr);

            }
        } else {

            int cyCount = choicCount / 3 - 1;
            for (int i = 0; i < cyCount; i++) {

                arr.add(String.valueOf(list.get(i).getName().toCharArray()[1]));
                arr.add(String.valueOf(list.get(i).getName().toCharArray()[2]));
                arr.add(String.valueOf(list.get(i).getName().toCharArray()[3]));
                String teststr = String.valueOf(list.get(i).getName()
                        .toCharArray()[1]);
                teststr += String
                        .valueOf(list.get(i).getName().toCharArray()[2]);
                teststr += String
                        .valueOf(list.get(i).getName().toCharArray()[3]);
                Log.i("teststr>choicCount：", teststr);

            }
            for (int i = 0; i < choicCount - 1 - cyCount * 3; i++) {
                String teststr = getChineseCharacter();
//				Log.i("测试产生的-1位置随机产生汉子（zhenDaanCount>choicCount）：", teststr);
                arr.add(teststr);
            }

        }

        //将生成的汉字进行随机排序；
        Collections.shuffle(arr);
        return arr;
    }

    /*
     * 随机获取一个成语的开头
     */
    public String GetHeadRandom() {
        Random ra = new Random();
        String headRandom = context.getResources().getString(R.string.headRandom);
        int index = ra.nextInt(Integer.parseInt(headRandom));
        if (index == 0)
            index = 1;
        ChengYu model = new ChengYu();
        model.setId(index);
        Log.i("查询条件Id", "错误1" + index);
        String result = cydll.Query(model).get(0).getHead();
        Log.i("查询条件result", "错误1" + result);
        return result;
    }

    /*
     * 根据选中的结果查询是否该成语存在
     */
    public boolean GetHeadByDaan(String result) {
        ChengYu model = new ChengYu();
        model.setName(result);
        return cydll.Query(model).size() > 0;

    }

    /*
     * 通过成语的head获取匹配的成语数量
     */
    public int GetChengYuCountByHead(String head) {
        ChengYu model = new ChengYu();
        model.setHead(head);
        return cydll.Query(model).size();

    }

    /*
     * 通过head查询条件获取成语列表
     */
    public List<ChengYu> GetChengYuListByHead(String head) {
        ChengYu model = new ChengYu();
        model.setHead(head);
        return cydll.Query(model);

    }
}
