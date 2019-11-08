package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Edit_like extends AppCompatActivity {

    Food_Ranking food_ranking = new Food_Ranking();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_like);

        final CheckBox cb1 = (CheckBox)findViewById(R.id.chk_Korean);
        final CheckBox cb2 = (CheckBox)findViewById(R.id.chk_Snack);
        final CheckBox cb3 = (CheckBox)findViewById(R.id.chk_dessert);
        final CheckBox cb4 = (CheckBox)findViewById(R.id.chk_curtlet);
        final CheckBox cb5 = (CheckBox)findViewById(R.id.chk_chicken);
        final CheckBox cb6 = (CheckBox)findViewById(R.id.chk_pizza);
        final CheckBox cb7 = (CheckBox)findViewById(R.id.chk_asian);
        final CheckBox cb8 = (CheckBox)findViewById(R.id.chk_china);
        final CheckBox cb9 = (CheckBox)findViewById(R.id.chk_pork);
        final CheckBox cb10 = (CheckBox)findViewById(R.id.chk_soup);
        final CheckBox cb11 = (CheckBox)findViewById(R.id.chk_lunch_box);
        final CheckBox cb12 = (CheckBox)findViewById(R.id.chk_fast_food);

        Button button = (Button)findViewById(R.id.btn_edit_like);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User").child(uid).child("Food_Rank");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                food_ranking = dataSnapshot.getValue(Food_Ranking.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        ref.addValueEventListener(eventListener);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cb1.isChecked() == true) ref.child("Korean").setValue(food_ranking.Korean + 1);
                if(cb2.isChecked() == true) ref.child("Snack").setValue(food_ranking.Snack + 1);
                if(cb3.isChecked() == true) ref.child("dessert").setValue(food_ranking.dessert + 1);
                if(cb4.isChecked() == true) ref.child("curtlet").setValue(food_ranking.curtlet + 1);
                if(cb5.isChecked() == true) ref.child("chicken").setValue(food_ranking.chicken + 1);
                if(cb6.isChecked() == true) ref.child("pizza").setValue(food_ranking.pizza + 1);
                if(cb7.isChecked() == true) ref.child("asian").setValue(food_ranking.asian + 1);
                if(cb8.isChecked() == true) ref.child("china").setValue(food_ranking.china + 1);
                if(cb9.isChecked() == true) ref.child("pork").setValue(food_ranking.pork + 1);
                if(cb10.isChecked() == true) ref.child("soup").setValue(food_ranking.soup + 1);
                if(cb11.isChecked() == true) ref.child("lunch_box").setValue(food_ranking.lunch_box + 1);
                if(cb12.isChecked() == true) ref.child("fast_food").setValue(food_ranking.fast_food + 1);

                Toast.makeText(Edit_like.this, "설정되었습니다", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
