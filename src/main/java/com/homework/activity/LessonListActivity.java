package com.homework.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.homework.R;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LessonListActivity extends AppCompatActivity {
    ListView lv_lessonlist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_lesson);

        init();
    }

    private void init() {
        lv_lessonlist = findViewById(R.id.lv_lessonlist);
        lv_lessonlist.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data()));
    }
    private List<String> data(){
        String[] strs = {"数学","语文","英语","计算机","数学","语文","英语","计算机","数学","语文","英语","计算机",
                "数学","语文","英语","计算机","数学","语文","英语","计算机"};
        return Arrays.asList(strs);
    }
}
