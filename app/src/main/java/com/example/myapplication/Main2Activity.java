package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

//이메일검색해서 사용자 찾고 그룹 만들기
public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final LayoutInflater inflater = getLayoutInflater();
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        inflater.inflate(R.layout.my_layout, linearLayout, true);
    }
}
