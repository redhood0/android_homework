package com.homework.activity;
import android.content.Intent;
import android.os.Bundle;

import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.homework.R;
import com.homework.fragment.PersonFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class HeadPicActivity extends AppCompatActivity {
    public final static int TO_WELCOME_PAGE_CODE = 2033;
    public static int icons[] = {R.drawable.icon1, R.drawable.icon2, R.drawable.icon3,
            R.drawable.icon4, R.drawable.icon5, R.drawable.icon6, R.drawable.icon7,
            R.drawable.icon8, R.drawable.icon9};
    private GridView gridView;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headpic_gridview);

        gridView = findViewById(R.id.gv_headpic);
        //初始化数据
        initData();
        setListener();

    }

    private void initData() {
        //图标
//        int icons[] = {R.drawable.icon1, R.drawable.icon2, R.drawable.icon3,
//                R.drawable.icon4, R.drawable.icon5, R.drawable.icon6, R.drawable.icon7,
//                R.drawable.icon8, R.drawable.icon9};
        //图标下的文字
        String names[] = {"image1", "image2", "image3", "image4", "image5", "image6", "image7", "image8", "image9"};

        dataList = new ArrayList<Map<String, Object>>(9);

        for (int i = 0; i < icons.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>(2);
            map.put("img", icons[i]);
            map.put("text", names[i]);
            dataList.add(map);
        }

        int[] ints = {R.id.img, R.id.text};
        String[] strs = {"img", "text"};
        adapter = new SimpleAdapter(this, dataList, R.layout.activity_item4headpic, strs, ints);
        gridView.setAdapter(adapter);
    }

    private void setListener() {
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            //Log.d("GridView", parent.toString() + "--" + view + "--" + position + "--" + id + "--" + icons[position]);
            PersonFragment fragment = PersonFragment.pf;
            Bundle bundle = new Bundle();
            bundle.putInt("data",icons[position]);
            Intent intent = new Intent(HeadPicActivity.this,WelcomeActivity.class);
            intent.putExtras(bundle);
            this.setResult(HeadPicActivity.TO_WELCOME_PAGE_CODE,intent);

            finish();
        });
    }

}
