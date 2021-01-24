package com.example.studyidiom.sxn.activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.example.studyidiom.R;
import com.example.studyidiom.entity.Animal;
import com.example.studyidiom.sxn.util.DialogUtil;

import java.util.ArrayList;
import java.util.List;


public class AnimalAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Animal> urls;

    public AnimalAdapter(Context context, int layout,List<Animal> urls) {
        this.context = context;
        this.urls = urls;
        this.layout=layout;
    }

    public void setUrls(List<Animal> urls) {
        this.urls = urls;
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public Animal getItem(int i) {
        return urls.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(layout, null);
            vh = new ViewHolder();
            vh.title=view.findViewById(R.id.sxn_tvName);
            view.setTag(vh);
        }
        vh = (ViewHolder) view.getTag();
        if (urls != null && urls.size() > 0) {
            vh.title.setText(urls.get(i).getName());
        }
        return view;
    }
    class ViewHolder{
        TextView title;
    }


}

