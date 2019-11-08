package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends Activity {
    ListView mListView;

    TextView mTextView1;
    TextView mTextView2;
    TextView mTextView3;

    Button mButton;
    Button mButton1;
    Button mButton2;
    Button mButton3;


    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        final LayoutInflater inflater = getLayoutInflater();
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        inflater.inflate(R.layout.my_layout, linearLayout, true);

        mListView = findViewById(R.id.listview);
        mTextView1 = findViewById(R.id.main_page1);
        mTextView2 = findViewById(R.id.main_page2);
        mTextView3 = findViewById(R.id.main_page3);
        mButton = findViewById(R.id.addgroup);
        mButton1 = findViewById(R.id.move_to_friends);
        mButton2 = findViewById(R.id.move_to_Like);
        mButton3 = findViewById(R.id.move_to_recommend);


        ArrayList<String> data1 = new ArrayList<>();
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<>(getApplicationContext(), R.layout.fragment1, data1);
        //mListView.setAdapter(adapter1);

        //databaseReference.child("group").push().setValue("1");
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        String uid = currentUser.getUid();

        databaseReference.child("User").child(uid).child("group").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String groupData = dataSnapshot.getValue().toString();
                adapter1.add(groupData);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) { }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) { }

            @Override
            public void onCancelled(DatabaseError databaseError) { }
        });

        mTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                //databaseReference.child("group").push().setValue("1");
                mListView.setAdapter(adapter1);
            }
        });
        mTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                ArrayList<Integer> data2 = new ArrayList<>();
                //data2.add(R.mipmap.ic_launcher);
                ImageArrayAdapter adapter2 = new ImageArrayAdapter(getApplicationContext(), R.layout.fragment2, data2);
                mListView.setAdapter(adapter2);
            }
        });
        mTextView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view3) {
                ArrayList<String> data = new ArrayList<>();
                ArrayAdapter<String> adapter3 = new ArrayAdapter<>(getApplicationContext(), R.layout.fragment3, data);
                mListView.setAdapter(adapter3);
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GroupActivity.class);
                startActivity(intent);
            }
        });

        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FriendActivity.class);
                startActivity(intent);
            }
        });

        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Edit_like.class);
                startActivity(intent);
            }
        });

        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecommendActivity.class);
                startActivity(intent);
            }
        });

    }
}
