package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        Button rec_food = (Button)findViewById(R.id.btn_recommend);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid()).child("Food_Rank");
        final int[] max_value = {0};
        final String[] menu = {""};

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
        ref.addListenerForSingleValueEvent(eventListener);


        final TextView textView = (TextView)findViewById(R.id.tv_recommend);
        rec_food.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                textView.setText(menu[0]);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
