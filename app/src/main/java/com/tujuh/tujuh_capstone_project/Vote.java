package com.tujuh.tujuh_capstone_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
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

        Intent intent = getIntent();
        groupname = (String) intent.getExtras().get("groupname");
        groupkey = (String) intent.getExtras().get("groupkey");

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
        final DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("vote").child(uid);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat format2 = new SimpleDateFormat("HH");
        Date time = new Date();
        String a = format2.format(time);
        final Long hour = Long.parseLong(a);

        if(hour > 16)
        {
            dating.setText(format.format(time)+ " ��표");
        }else if(hour > 11)
        {
            dating.setText(format.format(time)+ " �심 �표");
        }else{
            dating.setText(format.format(time)+ " �침 �표");
        }

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long value = dataSnapshot.getValue(Long.class);
                if(value == null)
                {
                    vote.setEnabled(true);
                }else{
                    Long dif = value - hour;
                    if(dif < 0)
                        dif = dif * -1;

                    if(dif >= 6)
                    {
                        vote.setEnabled(true);
                    }else{
                        vote.setEnabled(false);
                        vote.setText("�시 �표�기");
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        reff.addValueEventListener(postListener);

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
                    if(btn1.getText().toString().equals("pork")) {
                        ref.child("pork").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("soup")) {
                        ref.child("soup").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("pizza")) {
                        ref.child("pizza").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("lunch_box")) {
                        ref.child("lunch_box").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("fast_food")) {
                        ref.child("fast_food").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("dessert")) {
                        ref.child("dessert").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("curtlet")) {
                        ref.child("curtlet").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("china")) {
                        ref.child("china").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("asian")) {
                        ref.child("asian").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("Snack")) {
                        ref.child("Snack").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("Korean")) {
                        ref.child("Korean").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("chicken")) {
                        ref.child("chicken").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }
                }else if(c2.isChecked()==true){
                    if(btn2.getText().toString().equals("pork")) {
                        ref.child("pork").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("soup")) {
                        ref.child("soup").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("pizza")) {
                        ref.child("pizza").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("lunch_box")) {
                        ref.child("lunch_box").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("fast_food")) {
                        ref.child("fast_food").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("dessert")) {
                        ref.child("dessert").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("curtlet")) {
                        ref.child("curtlet").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("china")) {
                        ref.child("china").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("asian")) {
                        ref.child("asian").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("Snack")) {
                        ref.child("Snack").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("Korean")) {
                        ref.child("Korean").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("chicken")) {
                        ref.child("chicken").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }
                }else if(c3.isChecked()==true){
                    if(btn3.getText().toString().equals("pork")) {
                        ref.child("pork").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("soup")) {
                        ref.child("soup").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("pizza")) {
                        ref.child("pizza").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("lunch_box")) {
                        ref.child("lunch_box").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("fast_food")) {
                        ref.child("fast_food").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("dessert")) {
                        ref.child("dessert").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("curtlet")) {
                        ref.child("curtlet").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("china")) {
                        ref.child("china").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("asian")) {
                        ref.child("asian").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("Snack")) {
                        ref.child("Snack").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("Korean")) {
                        ref.child("Korean").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("chicken")) {
                        ref.child("chicken").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }
                }

                Toast.makeText(Vote.this, "�표 �료�었�니", Toast.LENGTH_SHORT).show();
                reff.setValue(hour);
                vote.setEnabled(false);
                vote.setText("�시 �표�기");
            }
        });
    }
}