package com.example.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class today extends Fragment {

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

    public FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public DatabaseReference databaseReference = firebaseDatabase.getReference();
    public DatabaseReference databaseReference1 = firebaseDatabase.getReference();

    public String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    public DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User").child(uid).child("today_hate_food");
    public FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    Map<String, String> group = new HashMap<String, String>();
    int value;
    int[] score = {0};

    public View view;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseReference.child("User").child(uid).child("group").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String id = dataSnapshot.getKey().toString();
                String name = dataSnapshot.getValue().toString();
                group.put(id, name);
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_today, container, false);

        cb1 = (CheckBox) view.findViewById(R.id.chk_Korean_f3);
        cb2 = (CheckBox) view.findViewById(R.id.chk_Snack_f3);
        cb3 = (CheckBox) view.findViewById(R.id.chk_dessert_f3);
        //final CheckBox cb4 = (CheckBox)findViewById(R.id.chk_curtlet);
        cb4 = (CheckBox) view.findViewById(R.id.chk_chicken_f3);
        cb5 = (CheckBox) view.findViewById(R.id.chk_pizza_f3);
        cb6 = (CheckBox) view.findViewById(R.id.chk_asian_f3);
        cb7 = (CheckBox) view.findViewById(R.id.chk_china_f3);
        //final CheckBox cb9 = (CheckBox)findViewById(R.id.chk_pork);
        cb8 = (CheckBox) view.findViewById(R.id.chk_soup_f3);
        cb9 = (CheckBox) view.findViewById(R.id.chk_lunch_box_f3);
        cb10 = (CheckBox) view.findViewById(R.id.chk_fast_food_f3);

        ImageButton button = (ImageButton) view.findViewById(R.id.btn_edit_like_f3);
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
                DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("group");
                DatabaseReference db1 = FirebaseDatabase.getInstance().getReference().child("User").child(uid).child("Food_Rank");
                Set key = group.keySet();
                if (cb1.isChecked() == true) {
                    ref.child("Korean").setValue(formatDate);

                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("Korean").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                int val = dataSnapshot.getValue(Integer.class);
                                dataSnapshot.getRef().setValue(val-1);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                    db1.child("Korean").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            int val = dataSnapshot.getValue(Integer.class);
                            dataSnapshot.getRef().setValue(val-1);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (cb2.isChecked() == true) {
                    ref.child("Snack").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("Snack").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                int val = dataSnapshot.getValue(Integer.class);
                                dataSnapshot.getRef().setValue(val-1);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                    db1.child("Snack").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            int val = dataSnapshot.getValue(Integer.class);
                            dataSnapshot.getRef().setValue(val-1);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (cb3.isChecked() == true) {
                    ref.child("dessert").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("dessert").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                int val = dataSnapshot.getValue(Integer.class);
                                dataSnapshot.getRef().setValue(val-1);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                    db1.child("dessert").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            int val = dataSnapshot.getValue(Integer.class);
                            dataSnapshot.getRef().setValue(val-1);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (cb4.isChecked() == true) {
                    ref.child("chicken").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("chicken").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                int val = dataSnapshot.getValue(Integer.class);
                                dataSnapshot.getRef().setValue(val-1);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                    db1.child("chicken").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            int val = dataSnapshot.getValue(Integer.class);
                            dataSnapshot.getRef().setValue(val-1);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (cb5.isChecked() == true) {
                    ref.child("pizza").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("pizza").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                int val = dataSnapshot.getValue(Integer.class);
                                dataSnapshot.getRef().setValue(val-1);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                    db1.child("pizza").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            int val = dataSnapshot.getValue(Integer.class);
                            dataSnapshot.getRef().setValue(val-1);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (cb6.isChecked() == true) {
                    ref.child("asian").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("asian").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                int val = dataSnapshot.getValue(Integer.class);
                                dataSnapshot.getRef().setValue(val-1);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                    db1.child("asian").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            int val = dataSnapshot.getValue(Integer.class);
                            dataSnapshot.getRef().setValue(val-1);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (cb7.isChecked() == true) {
                    ref.child("china").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("china").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                int val = dataSnapshot.getValue(Integer.class);
                                dataSnapshot.getRef().setValue(val-1);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                    db1.child("china").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            int val = dataSnapshot.getValue(Integer.class);
                            dataSnapshot.getRef().setValue(val-1);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (cb8.isChecked() == true) {
                    ref.child("soup").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("soup").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                int val = dataSnapshot.getValue(Integer.class);
                                dataSnapshot.getRef().setValue(val-1);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                    db1.child("soup").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            int val = dataSnapshot.getValue(Integer.class);
                            dataSnapshot.getRef().setValue(val-1);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (cb9.isChecked() == true) {
                    ref.child("lunch_box").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("lunch_box").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                int val = dataSnapshot.getValue(Integer.class);
                                dataSnapshot.getRef().setValue(val-1);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                    db1.child("lunch_box").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            int val = dataSnapshot.getValue(Integer.class);
                            dataSnapshot.getRef().setValue(val-1);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                if (cb10.isChecked() == true) {
                    ref.child("fast_food").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("fast_food").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                int val = dataSnapshot.getValue(Integer.class);
                                dataSnapshot.getRef().setValue(val-1);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                    db1.child("fast_food").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            int val = dataSnapshot.getValue(Integer.class);
                            dataSnapshot.getRef().setValue(val-1);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

                Toast.makeText(getActivity(), "설정되었습니다.", Toast.LENGTH_LONG).show();

            }
        });
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
