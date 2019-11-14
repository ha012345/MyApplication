package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecommendActivity extends AppCompatActivity {

    private String groupname, groupkey;
    private TextView mGroupName;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    ArrayList<String> member = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        Intent intent = getIntent();
        groupname = (String) intent.getExtras().get("groupname");
        groupkey = (String) intent.getExtras().get("groupkey");

        Button rec_food = (Button)findViewById(R.id.btn_recommend);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid()).child("Food_Rank");
        final int[] max_value = {0};
        final String[] menu = {""};

        databaseReference.child("group").child(groupkey).child(groupname).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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


        final ImageView imageview = (ImageView)findViewById(R.id.imageView);
        final TextView textView = (TextView)findViewById(R.id.tv_recommend);
        rec_food.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(menu[0].equals("Korean"))
                {
                    imageview.setImageResource(R.drawable.rice);
                }else if(menu[0].equals("Snack"))
                {
                    imageview.setImageResource(R.drawable.snack);
                }else if(menu[0].equals("asian"))
                {
                    imageview.setImageResource(R.drawable.sushi);
                }else if(menu[0].equals("chicken"))
                {
                    imageview.setImageResource(R.drawable.chicken);
                }else if(menu[0].equals("china"))
                {
                    imageview.setImageResource(R.drawable.china);
                }else if(menu[0].equals("dessert"))
                {
                    imageview.setImageResource(R.drawable.dessert);
                }else if(menu[0].equals("fast_food"))
                {
                    imageview.setImageResource(R.drawable.steak);
                }else if(menu[0].equals("lunch_box"))
                {
                    imageview.setImageResource(R.drawable.lunch_box);
                }else if(menu[0].equals("pizza"))
                {
                    imageview.setImageResource(R.drawable.pizza);
                }else if(menu[0].equals("soup"))
                {
                    imageview.setImageResource(R.drawable.soup);
                }
                textView.setText(menu[0]);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}