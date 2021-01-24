package com.example.studyidiom.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.studyidiom.R;
import com.example.studyidiom.lph.activity.LoginActivity;
import com.example.studyidiom.lph.activity.RegisterActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class MyFragment extends Fragment {
    private int id;
    private View view;
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
                    Intent intent=new Intent(getContext(), RegisterActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });

            return view;
        }
        view=inflater.inflate(R.layout.my_fragment_layout,container,false);
        return view;
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
}
