package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Vote extends AppCompatActivity {

    private String groupname, groupkey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        Intent intent = getIntent();
        groupname = (String) intent.getExtras().get("groupname");
        groupkey = (String) intent.getExtras().get("groupkey");

        SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
        Date time = new Date();

        TextView dating = (TextView)findViewById(R.id.textView2);
        dating.setText(format.format(time));
    }
}