package com.homework.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.homework.R;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText et_name;
    private EditText et_password;
    private Button btn_login;
    private Button btn_regist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        et_name = findViewById(R.id.et_name);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        btn_regist = findViewById(R.id.btn_regist);

        //跳转到注册界面
        btn_regist.setOnClickListener(n -> startActivity(new Intent(LoginActivity.this,RegistActivity.class)));
        //登陆点击事件
        btn_login.setOnClickListener(n ->{
            String userName = et_name.getText().toString();
            String password = et_password.getText().toString();
            if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)){
                Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(LoginActivity.this,WelcomeActivity.class);
                intent.putExtra("username",userName);
                startActivity(intent);
            }
        });

    }
}
