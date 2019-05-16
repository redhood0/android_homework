package com.homework.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.homework.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MusicActivity extends AppCompatActivity {
    ListView lv_musiclist;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_music);

        init();
    }
    private void init() {
        lv_musiclist = findViewById(R.id.lv_musiclist);
        String[] strs = new String[]{"num","title","author","pic"};
        int[] ids = new int[]{R.id.tv_num,R.id.tv_title,R.id.tv_author,R.id.iv_pic};
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,getDatas() ,R.layout.listitem_music,strs,ids);
        lv_musiclist.setAdapter(simpleAdapter);
    }

    private ArrayList<HashMap<String,Object>> getDatas(){
        ArrayList<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>(6);
        int i = 1;
        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("num",i++);
        map1.put("title","Lazy Days");
        map1.put("author","Dean Brody");
        map1.put("pic",R.drawable.music_icon);
        list.add(map1);
        HashMap<String,Object> map2 = new HashMap<>();
        map2.put("num",i++);
        map2.put("title","Lazy Days1111");
        map2.put("author","Dean Brody1111");
        map2.put("pic",R.drawable.music_icon);
        list.add(map2);
        return list;
    }
}
