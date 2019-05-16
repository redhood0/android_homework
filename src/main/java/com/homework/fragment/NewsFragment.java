package com.homework.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.homework.R;
import com.homework.activity.LessonListActivity;
import com.homework.activity.MusicActivity;
import com.homework.activity.WelcomeActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NewsFragment extends Fragment {
    ImageView iv_lessonlist;
    ImageView iv_musiclist;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news,container,false);

        iv_lessonlist = v.findViewById(R.id.iv_lessonlist);
        iv_musiclist = v.findViewById(R.id.iv_musiclist);

        iv_lessonlist.setOnClickListener(n -> startActivity(new Intent(WelcomeActivity.welcomeActivity, LessonListActivity.class)));
        iv_musiclist.setOnClickListener(n -> startActivity(new Intent(WelcomeActivity.welcomeActivity, MusicActivity.class)));
        return v;
    }
}
