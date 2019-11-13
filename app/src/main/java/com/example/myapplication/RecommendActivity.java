package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RecommendActivity extends AppCompatActivity {

    private String groupname, groupkey;
    private TextView mGroupName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        mGroupName = (TextView) findViewById(R.id.group);

        Intent intent = getIntent();
        //groupkey = (String) intent.getExtras().get("groupkey");
        //groupname = (String) intent.getExtras().get("groupname");
        groupkey = "LtZH0gqqTnzJb9TUGqD";
        groupname = "algtest";

        mGroupName.setText(groupkey + " " + groupname);

        Button rec_food = (Button)findViewById(R.id.btn_recommend);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = root.child("User");//.child(user.getUid()).child("Food_Rank");
        DatabaseReference groupRef = root.child("group");

        /*
        final int[] max_value = {0};
        final String[] member = {""};

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    int rank = ds.getValue().hashCode();
                    if(rank > max_value[0]){
                        max_value[0] = rank;
                        menu[0] = ds.getKey().toString();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        */
        /// make member e-mail list
        final List<String> userInGroup = new ArrayList<>();
        groupRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (@NonNull DataSnapshot dataSnapshot){
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String email = ds.getValue().toString();

                    userInGroup.add(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /// calculate menu score

        final String[] menu = {"Korean", "Snack", "asian", "chicken", "china", "curtlet", "dessert", "fast_food", "lunch_box", "pizza", "pork", "soup"};
        final int[] score = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        final int[] rank = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        ref.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange (@NonNull DataSnapshot dataSnapshot){
               for (DataSnapshot ds : dataSnapshot.getChildren()) {
                   for(String s : userInGroup){
                       if(ds.child("UserEmail").getValue().toString().equals(s)){
                           int i = 0;
                           for(String s1 : menu){
                               int temp = ds.child("Food_Rank").child(s1).getValue().hashCode();
                               score[i] += temp;
                               i++;
                           }
                       }
                   }
               }
               /*
               /// for remove same score : random number add(0-9)
               for(int i = 0; i < 12; i++){
                   int randomValue = (int) (Math.random()*10);
                   score[i] += randomValue;
               }

                */
           }

           @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

           }
        });

        /// sort menu
        for(int i = 0; i < 12; i++){
            for(int j = i+1; j < 12; j++){
                if(score[i] < score[j]) {rank[i]++;}
            }
        }
        final String[] menuRank = {""};
        for(int i = 0; i < 12; i++){
            menuRank[rank[i]] = menu[rank[i]];
        }


        final TextView textView = (TextView)findViewById(R.id.tv_recommend);
        rec_food.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textView.setText(menuRank[0]);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
