package com.homework.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.homework.R;
import com.homework.activity.HeadPicActivity;
import com.homework.activity.PersonListViewActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PersonFragment extends Fragment {

    public static PersonFragment pf;

    private TextView tv_name;
    private ImageView iv_pic;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_person_msg,container,false);
        pf = this;
        init(v);

        return v;
    }

    public void init(View v){
        tv_name = v.findViewById(R.id.tv_name);
        iv_pic = v.findViewById(R.id.iv_pic);
        //接收部分(在fragment里面接收）
//        Bundle bundle = getActivity().getIntent().getExtras();
//        if(bundle != null){
//            int iconId = bundle.getInt("data");
//            if(iconId == 0){
//                Log.d("GridView", ">>>>>frag data1：" + iconId);
//                iv_headicon.setImageResource(R.drawable.zq1);
//            }else{
//                Log.d("GridView", ">>>>>frag data2：" + iconId);
//                iv_headicon.setImageResource(iconId);
//            }
//        }
        tv_name.setOnClickListener(n ->  startActivity(new Intent(getActivity(), PersonListViewActivity.class)));
        iv_pic.setOnClickListener(n -> startActivityForResult(new Intent(getActivity(), HeadPicActivity.class),HeadPicActivity.TO_WELCOME_PAGE_CODE));
    }

    public void callBack(int iconId) {
        if(iconId == 0){
            iv_pic.setImageResource(R.drawable.zq1);
        }else{
            iv_pic.setImageResource(iconId);
        }
    }
}
