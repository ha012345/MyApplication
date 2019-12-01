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

    CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7, cb8, cb9, cb10, cb11, cb12, cb13, cb14;
    CheckBox cb15, cb16, cb17, cb18, cb19, cb20, cb21, cb22, cb23, cb24, cb25, cb26, cb27, cb28;
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
    public void porkClick(View view){
        cb11.toggle();
    }
    public void curtletClick(View view){
        cb12.toggle();
    }
    public void noodleClick(View view){
        cb13.toggle();
    }
    public void ribsClick(View view){
        cb14.toggle();
    }
    public void gukbapClick(View view){
        cb15.toggle();
    }
    public void sandwichClick(View view){
        cb16.toggle();
    }
    public void meatClick(View view){
        cb17.toggle();
    }
    public void tieClick(View view){
        cb18.toggle();
    }
    public void cold_noodleClick(View view){
        cb19.toggle();
    }
    public void udonClick(View view){
        cb20.toggle();
    }
    public void raw_fishClick(View view){
        cb21.toggle();
    }
    public void curryClick(View view){
        cb22.toggle();
    }
    public void skewersClick(View view){
        cb23.toggle();
    }
    public void boiled_chickenClick(View view){
        cb24.toggle();
    }
    public void ramenClick(View view){
        cb25.toggle();
    }
    public void maraClick(View view){
        cb26.toggle();
    }
    public void bossamClick(View view){
        cb27.toggle();
    }
    public void fishClick(View view){
        cb28.toggle();
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
        cb11 = (CheckBox) findViewById(R.id.chk_pork);
        cb12 = (CheckBox) findViewById(R.id.chk_curtlet);
        cb13 = (CheckBox) findViewById(R.id.chk_noodle);
        cb14 = (CheckBox) findViewById(R.id.chk_ribs);
        cb15 = (CheckBox) findViewById(R.id.chk_gukbap);
        cb16 = (CheckBox) findViewById(R.id.chk_sandwich);
        cb17 = (CheckBox) findViewById(R.id.chk_meat);
        cb18 = (CheckBox) findViewById(R.id.chk_tie);
        cb19 = (CheckBox) findViewById(R.id.chk_cold_noodle);
        cb20 = (CheckBox) findViewById(R.id.chk_udon);
        cb21 = (CheckBox) findViewById(R.id.chk_raw_fish);
        cb22 = (CheckBox) findViewById(R.id.chk_curry);
        cb23 = (CheckBox) findViewById(R.id.chk_skewers);
        cb24 = (CheckBox) findViewById(R.id.chk_boiled_chicken);
        cb25 = (CheckBox) findViewById(R.id.chk_ramen);
        cb26 = (CheckBox) findViewById(R.id.chk_mara);
        cb27 = (CheckBox) findViewById(R.id.chk_bossam);
        cb28 = (CheckBox) findViewById(R.id.chk_fish);

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

<<<<<<< Updated upstream
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
=======
                        }
                    });
                }
                if (cb11.isChecked() == true) {
                    ref.child("pork").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("pork").addListenerForSingleValueEvent(new ValueEventListener() {
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
                    db1.child("pork").addListenerForSingleValueEvent(new ValueEventListener() {
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
                if (cb12.isChecked() == true) {
                    ref.child("curtlet").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("curtlet").addListenerForSingleValueEvent(new ValueEventListener() {
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
                    db1.child("curtlet").addListenerForSingleValueEvent(new ValueEventListener() {
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

                if (cb13.isChecked() == true) {
                    ref.child("noodle").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("noodle").addListenerForSingleValueEvent(new ValueEventListener() {
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
                    db1.child("noodle").addListenerForSingleValueEvent(new ValueEventListener() {
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

                if (cb14.isChecked() == true) {
                    ref.child("ribs").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("ribs").addListenerForSingleValueEvent(new ValueEventListener() {
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
                    db1.child("ribs").addListenerForSingleValueEvent(new ValueEventListener() {
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

                if (cb15.isChecked() == true) {
                    ref.child("gukbap").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("gukbap").addListenerForSingleValueEvent(new ValueEventListener() {
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
                    db1.child("gukbap").addListenerForSingleValueEvent(new ValueEventListener() {
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

                if (cb16.isChecked() == true) {
                    ref.child("sandwich").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("sandwich").addListenerForSingleValueEvent(new ValueEventListener() {
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
                    db1.child("sandwich").addListenerForSingleValueEvent(new ValueEventListener() {
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

                if (cb17.isChecked() == true) {
                    ref.child("meat").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("meat").addListenerForSingleValueEvent(new ValueEventListener() {
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
                    db1.child("meat").addListenerForSingleValueEvent(new ValueEventListener() {
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

                if (cb18.isChecked() == true) {
                    ref.child("tie").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("tie").addListenerForSingleValueEvent(new ValueEventListener() {
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
                    db1.child("tie").addListenerForSingleValueEvent(new ValueEventListener() {
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

                if (cb19.isChecked() == true) {
                    ref.child("cold_noodle").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("cold_noodle").addListenerForSingleValueEvent(new ValueEventListener() {
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
                    db1.child("cold_noodle").addListenerForSingleValueEvent(new ValueEventListener() {
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

                if (cb20.isChecked() == true) {
                    ref.child("udon").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("udon").addListenerForSingleValueEvent(new ValueEventListener() {
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
                    db1.child("udon").addListenerForSingleValueEvent(new ValueEventListener() {
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

                if (cb21.isChecked() == true) {
                    ref.child("raw_fish").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("raw_fish").addListenerForSingleValueEvent(new ValueEventListener() {
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
                    db1.child("raw_fish").addListenerForSingleValueEvent(new ValueEventListener() {
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

                if (cb22.isChecked() == true) {
                    ref.child("curry").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("curry").addListenerForSingleValueEvent(new ValueEventListener() {
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
                    db1.child("curry").addListenerForSingleValueEvent(new ValueEventListener() {
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

                if (cb23.isChecked() == true) {
                    ref.child("skewers").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("skewers").addListenerForSingleValueEvent(new ValueEventListener() {
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
                    db1.child("skewers").addListenerForSingleValueEvent(new ValueEventListener() {
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

                if (cb24.isChecked() == true) {
                    ref.child("boiled_chicken").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("boiled_chicken").addListenerForSingleValueEvent(new ValueEventListener() {
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
                    db1.child("boiled_chicken").addListenerForSingleValueEvent(new ValueEventListener() {
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

                if (cb25.isChecked() == true) {
                    ref.child("ramen").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("ramen").addListenerForSingleValueEvent(new ValueEventListener() {
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
                    db1.child("ramen").addListenerForSingleValueEvent(new ValueEventListener() {
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

                if (cb26.isChecked() == true) {
                    ref.child("mara").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("mara").addListenerForSingleValueEvent(new ValueEventListener() {
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
                    db1.child("mara").addListenerForSingleValueEvent(new ValueEventListener() {
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

                if (cb27.isChecked() == true) {
                    ref.child("bossam").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("bossam").addListenerForSingleValueEvent(new ValueEventListener() {
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
                    db1.child("bossam").addListenerForSingleValueEvent(new ValueEventListener() {
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

                if (cb28.isChecked() == true) {
                    ref.child("fish").setValue(formatDate);
                    for(Iterator iterator = key.iterator(); iterator.hasNext();){
                        final String keyname = (String) iterator.next();
                        db.child(keyname).child("data").child("fish").addListenerForSingleValueEvent(new ValueEventListener() {
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
                    db1.child("fish").addListenerForSingleValueEvent(new ValueEventListener() {
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
>>>>>>> Stashed changes

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
