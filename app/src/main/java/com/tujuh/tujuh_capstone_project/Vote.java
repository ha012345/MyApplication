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
                String a = "" + s;
                String asdf = dataSnapshot.getKey();
                if(asdf.equals("Korean"))
                {
                    asdf = "백반";
                }else if(asdf.equals("Snack"))
                {
                    asdf = "분식";
                }
                else if(asdf.equals("dessert"))
                {
                    asdf = "디저트";
                }else if(asdf.equals("chicken"))
                {
                    asdf = "치킨";
                }else if(asdf.equals("pizza"))
                {
                    asdf = "피자";
                }else if(asdf.equals("asian"))
                {
                    asdf = "초밥";
                }else if(asdf.equals("china"))
                {
                    asdf = "중식";
                }else if(asdf.equals("soup"))
                {
                    asdf = "감자탕";
                }else if(asdf.equals("lunch_box"))
                {
                    asdf = "도시락";
                }else if(asdf.equals("fast_food"))
                {
                    asdf = "양식";
                }else if(asdf.equals("pork"))
                {
                    asdf = "패스드푸드";
                }else if(asdf.equals("curtlet"))
                {
                    asdf = "돈까스";
                }else if(asdf.equals("noodle"))
                {
                    asdf = "국수";
                }else if(asdf.equals("ribs"))
                {
                    asdf = "닭갈비";
                }else if(asdf.equals("gukbap"))
                {
                    asdf = "국밥";
                }else if(asdf.equals("sandwich"))
                {
                    asdf = "샌드위치";
                }else if(asdf.equals("meat"))
                {
                    asdf = "고기류";
                }else if(asdf.equals("tie"))
                {
                    asdf = "동남아식";
                }else if(asdf.equals("cold_noodle"))
                {
                    asdf = "냉면";
                }else if(asdf.equals("udon"))
                {
                    asdf = "우동";
                }else if(asdf.equals("raw_fish"))
                {
                    asdf = "회";
                }else if(asdf.equals("curry"))
                {
                    asdf = "카레";
                }else if(asdf.equals("skewers"))
                {
                    asdf = "양꼬치";
                }else if(asdf.equals("boiled_chicken"))
                {
                    asdf = "백숙";
                }else if(asdf.equals("ramen"))
                {
                    asdf = "라면";
                }else if(asdf.equals("mara"))
                {
                    asdf = "마라탕";
                }else if(asdf.equals("bossam"))
                {
                    asdf = "보쌈";
                }else if(asdf.equals("fish"))
                {
                    asdf = "회";
                }

                if(a.equals("Korean"))
                {
                    a = "백반";
                }else if(a.equals("Snack"))
                {
                    a = "분식";
                }
                else if(a.equals("dessert"))
                {
                    a = "디저트";
                }else if(a.equals("chicken"))
                {
                    a = "치킨";
                }else if(a.equals("pizza"))
                {
                    a = "피자";
                }else if(a.equals("asian"))
                {
                    a = "초밥";
                }else if(a.equals("china"))
                {
                    a = "중식";
                }else if(a.equals("soup"))
                {
                    a = "감자탕";
                }else if(a.equals("lunch_box"))
                {
                    a = "도시락";
                }else if(a.equals("fast_food"))
                {
                    a = "양식";
                }else if(a.equals("pork"))
                {
                    a = "패스드푸드";
                }else if(a.equals("curtlet"))
                {
                    a = "돈까스";
                }else if(a.equals("noodle"))
                {
                    a = "국수";
                }else if(a.equals("ribs"))
                {
                    a = "닭갈비";
                }else if(a.equals("gukbap"))
                {
                    a = "국밥";
                }else if(a.equals("sandwich"))
                {
                    a = "샌드위치";
                }else if(a.equals("meat"))
                {
                    a = "고기류";
                }else if(a.equals("tie"))
                {
                    a = "동남아식";
                }else if(a.equals("cold_noodle"))
                {
                    a = "냉면";
                }else if(a.equals("udon"))
                {
                    a = "우동";
                }else if(a.equals("raw_fish"))
                {
                    a = "회";
                }else if(a.equals("curry"))
                {
                    a = "카레";
                }else if(a.equals("skewers"))
                {
                    a = "양꼬치";
                }else if(a.equals("boiled_chicken"))
                {
                    a = "백숙";
                }else if(a.equals("ramen"))
                {
                    a = "라면";
                }else if(a.equals("mara"))
                {
                    a = "마라탕";
                }else if(a.equals("bossam"))
                {
                    a = "보쌈";
                }else if(a.equals("fish"))
                {
                    a = "회";
                }
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
                if(asdf.equals("Korean"))
                {
                    asdf = "백반";
                }else if(asdf.equals("Snack"))
                {
                    asdf = "분식";
                }
                else if(asdf.equals("dessert"))
                {
                    asdf = "디저트";
                }else if(asdf.equals("chicken"))
                {
                    asdf = "치킨";
                }else if(asdf.equals("pizza"))
                {
                    asdf = "피자";
                }else if(asdf.equals("asian"))
                {
                    asdf = "초밥";
                }else if(asdf.equals("china"))
                {
                    asdf = "중식";
                }else if(asdf.equals("soup"))
                {
                    asdf = "감자탕";
                }else if(asdf.equals("lunch_box"))
                {
                    asdf = "도시락";
                }else if(asdf.equals("fast_food"))
                {
                    asdf = "양식";
                }else if(asdf.equals("pork"))
                {
                    asdf = "패스드푸드";
                }else if(asdf.equals("curtlet"))
                {
                    asdf = "돈까스";
                }else if(asdf.equals("noodle"))
                {
                    asdf = "국수";
                }else if(asdf.equals("ribs"))
                {
                    asdf = "닭갈비";
                }else if(asdf.equals("gukbap"))
                {
                    asdf = "국밥";
                }else if(asdf.equals("sandwich"))
                {
                    asdf = "샌드위치";
                }else if(asdf.equals("meat"))
                {
                    asdf = "고기류";
                }else if(asdf.equals("tie"))
                {
                    asdf = "동남아식";
                }else if(asdf.equals("cold_noodle"))
                {
                    asdf = "냉면";
                }else if(asdf.equals("udon"))
                {
                    asdf = "우동";
                }else if(asdf.equals("raw_fish"))
                {
                    asdf = "회";
                }else if(asdf.equals("curry"))
                {
                    asdf = "카레";
                }else if(asdf.equals("skewers"))
                {
                    asdf = "양꼬치";
                }else if(asdf.equals("boiled_chicken"))
                {
                    asdf = "백숙";
                }else if(asdf.equals("ramen"))
                {
                    asdf = "라면";
                }else if(asdf.equals("mara"))
                {
                    asdf = "마라탕";
                }else if(asdf.equals("bossam"))
                {
                    asdf = "보쌈";
                }else if(asdf.equals("fish"))
                {
                    asdf = "회";
                }

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
        SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");
        SimpleDateFormat format2 = new SimpleDateFormat("HH");
        Date time = new Date();
        String a = format2.format(time);
        final Long hour = Long.parseLong(a);

        if(hour > 16)
        {
            dating.setText(format.format(time)+ " 저녁 투표");
        }else if(hour > 11)
        {
            dating.setText(format.format(time)+ " 점심 투표");
        }else{
            dating.setText(format.format(time)+ " 아침 투표");
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
                        vote.setText("다시 투표하기");
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("pork").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("soup").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("pizza").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("lunch_box").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("fast_food").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("dessert").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("curtlet").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("china").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("asian").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("Snack").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("Korean").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("chicken").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("noodle")) {
                        ref.child("noodle").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("noodle").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }
                    else if(btn1.getText().toString().equals("chicken")) {
                        ref.child("chicken").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("chicken").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("ribs")) {
                        ref.child("ribs").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("ribs").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("gukbap")) {
                        ref.child("gukbap").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("gukbap").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }
                    else if(btn1.getText().toString().equals("sandwich")) {
                        ref.child("sandwich").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("sandwich").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("meat")) {
                        ref.child("meat").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("meat").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("tie")) {
                        ref.child("tie").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("tie").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("cold_noodle")) {
                        ref.child("cold_noodle").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("cold_noodle").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("udon")) {
                        ref.child("udon").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("udon").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("raw_fish")) {
                        ref.child("raw_fish").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("raw_fish").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("curry")) {
                        ref.child("curry").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("curry").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("skewers")) {
                        ref.child("skewers").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("skewers").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("boiled_chicken")) {
                        ref.child("boiled_chicken").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("boiled_chicken").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("ramen")) {
                        ref.child("ramen").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("ramen").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("mara")) {
                        ref.child("mara").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("mara").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("bossam")) {
                        ref.child("bossam").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("bossam").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn1.getText().toString().equals("fish")) {
                        ref.child("fish").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("fish").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("pork").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("soup").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("pizza").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("lunch_box").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("fast_food").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("dessert").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("curtlet").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("china").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("asian").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("Snack").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("Korean").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("chicken").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("noodle")) {
                        ref.child("noodle").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("noodle").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }
                    else if(btn2.getText().toString().equals("chicken")) {
                        ref.child("chicken").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("chicken").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("ribs")) {
                        ref.child("ribs").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("ribs").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("gukbap")) {
                        ref.child("gukbap").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("gukbap").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }
                    else if(btn2.getText().toString().equals("sandwich")) {
                        ref.child("sandwich").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("sandwich").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("meat")) {
                        ref.child("meat").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("meat").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("tie")) {
                        ref.child("tie").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("tie").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("cold_noodle")) {
                        ref.child("cold_noodle").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("cold_noodle").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("udon")) {
                        ref.child("udon").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("udon").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("raw_fish")) {
                        ref.child("raw_fish").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("raw_fish").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("curry")) {
                        ref.child("curry").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("curry").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("skewers")) {
                        ref.child("skewers").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("skewers").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("boiled_chicken")) {
                        ref.child("boiled_chicken").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("boiled_chicken").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("ramen")) {
                        ref.child("ramen").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("ramen").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("mara")) {
                        ref.child("mara").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("mara").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("bossam")) {
                        ref.child("bossam").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("bossam").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn2.getText().toString().equals("fish")) {
                        ref.child("fish").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("fish").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("pork").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("soup").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("pizza").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("lunch_box").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("fast_food").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("dessert").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("curtlet").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("china").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("asian").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("Snack").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("Korean").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
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
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("chicken").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("noodle")) {
                        ref.child("noodle").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("noodle").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }
                    else if(btn3.getText().toString().equals("chicken")) {
                        ref.child("chicken").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("chicken").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("ribs")) {
                        ref.child("ribs").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("ribs").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("gukbap")) {
                        ref.child("gukbap").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("gukbap").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }
                    else if(btn3.getText().toString().equals("sandwich")) {
                        ref.child("sandwich").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("sandwich").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("meat")) {
                        ref.child("meat").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("meat").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("tie")) {
                        ref.child("tie").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("tie").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("cold_noodle")) {
                        ref.child("cold_noodle").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("cold_noodle").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("udon")) {
                        ref.child("udon").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("udon").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("raw_fish")) {
                        ref.child("raw_fish").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("raw_fish").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("curry")) {
                        ref.child("curry").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("curry").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("skewers")) {
                        ref.child("skewers").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("skewers").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("boiled_chicken")) {
                        ref.child("boiled_chicken").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("boiled_chicken").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("ramen")) {
                        ref.child("ramen").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("ramen").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("mara")) {
                        ref.child("mara").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("mara").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("bossam")) {
                        ref.child("bossam").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("bossam").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }else if(btn3.getText().toString().equals("fish")) {
                        ref.child("fish").runTransaction(new Transaction.Handler() {
                            @NonNull
                            @Override
                            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                Long value = mutableData.getValue(Long.class);
                                if (value == null) {
                                    mutableData.setValue(0);
                                } else {
                                    mutableData.setValue(value + 10);
                                    final DatabaseReference lap = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child("voting").child(Long.toString(value));
                                    ref.child("fish").runTransaction(new Transaction.Handler() {
                                        @NonNull
                                        @Override
                                        public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                                            Long valuee = mutableData.getValue(Long.class);
                                            lap.setValue(valuee+1);

                                            return Transaction.success(mutableData);
                                        }

                                        @Override
                                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                        }
                                    });
                                }
                                return Transaction.success(mutableData);
                            }

                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                            }
                        });
                    }
                }
                if(c1.isChecked() == true)
                    c1.setText("1");
                else
                    c1.setText("0");
                if(c2.isChecked() == true)
                    c2.setText("1");
                else
                    c2.setText("0");
                if(c3.isChecked() == true)
                    c3.setText("1");
                else
                    c3.setText("0");
                Toast.makeText(Vote.this, "투표 완료되었습니다.", Toast.LENGTH_SHORT).show();
                reff.setValue(hour);
                vote.setEnabled(false);
                vote.setText("다시 투표하기");
            }
        });
    }
}