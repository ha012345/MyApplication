package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    Food_Ranking food_ranking = new Food_Ranking();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        TextView dating = (TextView)findViewById(R.id.textView2);
        Button btn1 = (Button)findViewById(R.id.button1);
        Button btn2 = (Button)findViewById(R.id.button2);
        Button btn3 = (Button)findViewById(R.id.button3);
        final CheckBox c1 = (CheckBox)findViewById(R.id.checkBox1);
        final CheckBox c2 = (CheckBox)findViewById(R.id.checkBox2);
        final CheckBox c3 = (CheckBox)findViewById(R.id.checkBox3);
        Button vote = (Button)findViewById(R.id.voting);

        Intent intent = getIntent();
        groupname = (String) intent.getExtras().get("groupname");
        groupkey = (String) intent.getExtras().get("groupkey");

        SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");
        SimpleDateFormat format2 = new SimpleDateFormat("HH");
        Date time = new Date();

        btn1.setText("치킨");
        btn2.setText("도시락");
        btn3.setText("양식");

        String a = format2.format(time);
        int hour = Integer.parseInt(a);

        if(hour > 16)
        {
            dating.setText(format.format(time)+ " 저녁 투표");
        }else if(hour > 11)
        {
            dating.setText(format.format(time)+ " 점심 투표");
        }else{
            dating.setText(format.format(time)+ " 아침 투표");
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c1.toggle();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c2.toggle();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c3.toggle();
            }
        });

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("food_pick");

        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (c1.isChecked() == true) ref.child("Korean").setValue(1);
                if (c2.isChecked() == true) ref.child("Snack").setValue(1);
                if (c3.isChecked() == true) ref.child("dessert").setValue(1);
                Toast.makeText(Vote.this, "투표 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}