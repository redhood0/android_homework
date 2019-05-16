package com.homework.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.homework.R;
import com.homework.fragment.CaculatorFragment;
import com.homework.fragment.MsgFragment;
import com.homework.fragment.NewsFragment;
import com.homework.fragment.PersonFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class WelcomeActivity extends AppCompatActivity {
    public static WelcomeActivity welcomeActivity;

    TextView tv_welcome_word;
    Button btn_calculator;
    Button btn_news;
    Button btn_msg;
    Button btn_person;

    CaculatorFragment caculatorFragment;
    NewsFragment newsFragment;
    MsgFragment msgFragment;
    PersonFragment personFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
        welcomeActivity = this;

        tv_welcome_word = findViewById(R.id.tv_welcome_word);
        String username = getIntent().getStringExtra("username");
        tv_welcome_word.setText(username+" 同学\n,欢迎来到南工院!");

        btn_calculator = findViewById(R.id.btn_calculator);
        btn_news = findViewById(R.id.btn_news);
        btn_msg = findViewById(R.id.btn_msg);
        btn_person = findViewById(R.id.btn_person);

        FragmentManager fragmentManager = getSupportFragmentManager();

        btn_calculator.setOnClickListener(n -> {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if(caculatorFragment == null){
                caculatorFragment = new CaculatorFragment();
                transaction.add(R.id.frame4change,caculatorFragment);
            }
            transaction.replace(R.id.frame4change,caculatorFragment);
            transaction.commit();
        });

        btn_news.setOnClickListener(n ->{
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if(newsFragment == null){
                newsFragment = new NewsFragment();
                transaction.add(R.id.frame4change,newsFragment);
            }
            transaction.replace(R.id.frame4change,newsFragment);
            transaction.commit();
        });

        btn_msg.setOnClickListener(n -> {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if(msgFragment == null){
                msgFragment = new MsgFragment();
                transaction.add(R.id.frame4change,msgFragment);

            }
            transaction.replace(R.id.frame4change,msgFragment);
            transaction.commit();
        });

        btn_person.setOnClickListener(n -> {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if(personFragment == null){
                personFragment = new PersonFragment();
                transaction.add(R.id.frame4change,personFragment);
            }
            transaction.replace(R.id.frame4change,personFragment);
            transaction.commit();
        });

    }

}
