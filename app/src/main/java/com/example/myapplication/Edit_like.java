package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
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


public class Edit_like extends AppCompatActivity {

    CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9, cb10;
    Food_Ranking food_ranking = new Food_Ranking();
    public void koreanClick(View view){
        cb1.toggle();
    }
    public void snackClick(View view){
        cb2.toggle();
    }
    public void asianClick(View view){
        cb6.toggle();
    }
    public void chickenClick(View view){
        cb4.toggle();
    }
    public void pizzaClick(View view){
        cb5.toggle();
    }
    public void soupClick(View view){
        cb8.toggle();
    }
    public void lunchboxClick(View view){
        cb9.toggle();
    }
    public void fastfoodClick(View view){
        cb10.toggle();
    }
    public void dessertClick(View view){
        cb3.toggle();
    }
    public void chinaClick(View view){
        cb7.toggle();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_like);

        cb1 = (CheckBox) findViewById(R.id.chk_Korean);
        cb2 = (CheckBox) findViewById(R.id.chk_Snack);
        cb3 = (CheckBox) findViewById(R.id.chk_dessert);
        //final CheckBox cb4 = (CheckBox)findViewById(R.id.chk_curtlet);
        cb4 = (CheckBox) findViewById(R.id.chk_chicken);
        cb5 = (CheckBox) findViewById(R.id.chk_pizza);
        cb6 = (CheckBox) findViewById(R.id.chk_asian);
        cb7 = (CheckBox) findViewById(R.id.chk_china);
        //final CheckBox cb9 = (CheckBox)findViewById(R.id.chk_pork);
        cb8 = (CheckBox) findViewById(R.id.chk_soup);
        cb9 = (CheckBox) findViewById(R.id.chk_lunch_box);
        cb10 = (CheckBox) findViewById(R.id.chk_fast_food);

        ImageButton button = (ImageButton) findViewById(R.id.btn_edit_like);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User").child(uid).child("today_hate_food");
        //final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User").child(uid).child("Food_Rank");

        /*ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                food_ranking = dataSnapshot.getValue(Food_Ranking.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        ref.addValueEventListener(eventListener);*/

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long now = System.currentTimeMillis();
                // 현재시간을 date 변수에 저장한다.
                Date date = new Date(now);
                // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
                SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                // nowDate 변수에 값을 저장한다.
                String formatDate = sdfNow.format(date);

                if (cb1.isChecked() == true) ref.child("Korean").setValue(formatDate);
                if (cb2.isChecked() == true) ref.child("Snack").setValue(formatDate);
                if (cb3.isChecked() == true) ref.child("dessert").setValue(formatDate);
                if (cb4.isChecked() == true) ref.child("chicken").setValue(formatDate);
                if (cb5.isChecked() == true) ref.child("pizza").setValue(formatDate);
                if (cb6.isChecked() == true) ref.child("asian").setValue(formatDate);
                if (cb7.isChecked() == true) ref.child("china").setValue(formatDate);
                if (cb8.isChecked() == true) ref.child("soup").setValue(formatDate);
                if (cb9.isChecked() == true) ref.child("lunch_box").setValue(formatDate);
                if (cb10.isChecked() == true) ref.child("fast_food").setValue(formatDate);

                Toast.makeText(Edit_like.this, "설정되었습니다.", Toast.LENGTH_LONG).show();

                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
