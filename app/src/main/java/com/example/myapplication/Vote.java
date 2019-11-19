package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Vote extends AppCompatActivity {

    private String groupname, groupkey;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference1 = firebaseDatabase.getReference();
    Food_Ranking food_ranking = new Food_Ranking();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);
        final TextView dating = (TextView)findViewById(R.id.textView2);
        final Button btn1 = (Button)findViewById(R.id.button1);
        final Button btn2 = (Button)findViewById(R.id.button2);
        final Button btn3 = (Button)findViewById(R.id.button3);
        final CheckBox c1 = (CheckBox)findViewById(R.id.checkBox1);
        final CheckBox c2 = (CheckBox)findViewById(R.id.checkBox2);
        final CheckBox c3 = (CheckBox)findViewById(R.id.checkBox3);
        final Button vote = (Button)findViewById(R.id.voting);
        final TextView textView = (TextView)findViewById(R.id.textView);

        Intent intent = getIntent();
        groupname = (String) intent.getExtras().get("groupname");
        groupkey = (String) intent.getExtras().get("groupkey");
        vote.setEnabled(false);

        final DatabaseReference ref3 = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("data");
        ref3.orderByValue().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String a = s;
                String asdf = dataSnapshot.getKey();
                btn1.setText(asdf);
                btn2.setText(a);
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

        databaseReference1.child("group").child(groupkey).child("data").orderByValue().limitToFirst(10).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String asdf = dataSnapshot.getKey();
                btn3.setText(asdf);
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

        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        final DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("User").child(uid).child("vote").child(uid);

        final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd);
        final SimpleDateFormat format2 = new SimpleDateFormat("HH");
        final Date time = new Date();

        reff.runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                String value = mutableData.getValue(String.class);
                String a = format2.format(time);
                textView.setText("a"+value+"a");

                int hour = Integer.parseInt(a);
                if(hour > 16)
                {
                    dating.setText(format.format(time)+ " Ä¨Ìëú");
                    if(value.equals("Ä))
                    {
                        vote.setEnabled(false);
                    }else{
                        vote.setEnabled(true);
                    }
                    reff.setValue("Ä);
                }else if(hour > 11)
                {
                    dating.setText(format.format(time)+ " êÏã¨ ¨Ìëú");
                    if(value.equals("êÏã¨"))
                    {
                        vote.setEnabled(false);
                    }else{
                        vote.setEnabled(true);
                    }
                    reff.setValue("êÏã¨");
                }else{
                    dating.setText(format.format(time)+ " ÑÏπ® ¨Ìëú");
                    if(value.equals("ÑÏπ®"))
                    {
                        vote.setEnabled(false);
                    }else{
                        vote.setEnabled(true);
                    }
                    reff.setValue("ÑÏπ®");
                }
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

            }
        });

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

        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("data");

        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (c1.isChecked() == true)
                {
                    if(btn1.getText().toString().equals("pork"))
                    {
                        ref.child("pork").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if(value==null){
                                    mutableData.setValue(0);
                                }else{
                                    mutableData.setValue(value+10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }
                }
                //if (c2.isChecked() == true) ref.child("Snack").setValue(1);
                //if (c3.isChecked() == true) ref.child("dessert").setValue(1);

                Toast.makeText(Vote.this, "¨Ìëú ÑÎ£åòÏóàµÎãà", Toast.LENGTH_SHORT).show();
                vote.setEnabled(false);
            }
        });
    }
}