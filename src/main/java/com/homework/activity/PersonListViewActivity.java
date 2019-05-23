package com.homework.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.homework.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PersonListViewActivity extends AppCompatActivity {
    private ListView lv_4homework;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview4homework);
        init();
    }

    private void init() {
        lv_4homework = findViewById(R.id.lv_4homework);

        SimpleAdapter simpleAdapter = new SimpleAdapter(PersonListViewActivity.this, getDatas(),
                R.layout.activity_item4homework, getStrs(), getInts());
        lv_4homework.setAdapter(simpleAdapter);
    }

    private int[] getInts() {
        int[] ints = {R.id.tv_name, R.id.tv_place, R.id.tv_career, R.id.tv_class};
        return ints;
    }

    private String[] getStrs() {
        String[] strs = {"name", "place", "career", "class"};
        return strs;
    }

    private List<? extends Map<String, ?>> getDatas() {
        List<Map<String, Object>> list = new ArrayList<>(5);

        Map<String, Object> map1 = new HashMap<>(4);
        map1.put("name", "赵乾");
        map1.put("place", "江苏南通");
        map1.put("career", "移动应用开发");
        map1.put("class", "移动1811");
        list.add(map1);
        Map<String, Object> map2 = new HashMap<>(4);
        map2.put("name", "周明清");
        map2.put("place", "江苏盐城");
        map2.put("career", "移动应用开发");
        map2.put("class", "移动1811");
        list.add(map2);
        Map<String, Object> map3 = new HashMap<>(4);
        map3.put("name", "汉城和");
        map3.put("place", "江苏泰州");
        map3.put("career", "移动应用开发");
        map3.put("class", "移动1811");
        list.add(map3);
        Map<String, Object> map4 = new HashMap<>(4);
        map4.put("name", "成科宇");
        map4.put("place", "江苏南京");
        map4.put("career", "移动应用开发");
        map4.put("class", "移动1811");
        list.add(map4);
        Map<String, Object> map5 = new HashMap<>(4);
        map5.put("name", "蒋元生");
        map5.put("place", "江苏徐州");
        map5.put("career", "移动应用开发");
        map5.put("class", "移动1811");
        list.add(map5);
        Map<String, Object> map6 = new HashMap<>(4);
        map6.put("name", "蒋元生1");
        map6.put("place", "江苏徐州");
        map6.put("career", "移动应用开发");
        map6.put("class", "移动1811");
        list.add(map6);
        Map<String, Object> map7 = new HashMap<>(4);
        map7.put("name", "蒋元生2");
        map7.put("place", "江苏徐州");
        map7.put("career", "移动应用开发");
        map7.put("class", "移动1811");
        list.add(map7);
        Map<String, Object> map8 = new HashMap<>(4);
        map8.put("name", "蒋元生3");
        map8.put("place", "江苏徐州");
        map8.put("career", "移动应用开发");
        map8.put("class", "移动1811");
        list.add(map8);

        return list;
    }

}
