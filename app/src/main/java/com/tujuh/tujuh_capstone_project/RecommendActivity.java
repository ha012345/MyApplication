package com.tujuh.tujuh_capstone_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RecommendActivity extends AppCompatActivity {

    private String groupname, groupkey;
    private TextView mGroupName;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference1 = firebaseDatabase.getReference();
    private DatabaseReference databaseReference2 = firebaseDatabase.getReference();
    private DatabaseReference databaseReference3 = firebaseDatabase.getReference();
    private DatabaseReference databaseReference4 = firebaseDatabase.getReference();
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    ArrayList<String> member = new ArrayList<>();
    ArrayList<String> mem_uid = new ArrayList<>();
    Map<String, Integer> score = new HashMap<>();
    ArrayList<String> menu1 = new ArrayList<>();
    ArrayList<String> today_hate = new ArrayList<>();
    Map<String, Double> like_hate = new HashMap<>();
    Map<String, Double> like_hate_all = new HashMap<>();
    Map<String, Double> like_hate_final = new HashMap<>();
    Map<String, Map<String, Double>> other_group = new HashMap<>();
//    ArrayList<Integer> score = new ArrayList<>();
    String[] menu = {"Korean", "Snack", "asian", "chicken", "china", "curtlet", "dessert", "fast_food", "lunch_box", "pizza", "pork", "soup",
"noodle", "ribs", "gukbap", "sandwich", "meat", "tie", "cold_noodle", "udon", "raw_fish", "curry", "skewers", "boiled_chicken", "ramen", "mara", "bossam", "fish"};
    //ArrayList<Food_Ranking> score = new ArrayList<>();
    String final_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        Intent intent = getIntent();
        groupname = (String) intent.getExtras().get("groupname");
        groupkey = (String) intent.getExtras().get("groupkey");

        Button rec_food = (Button)findViewById(R.id.btn_recommend);
        Button map_food = (Button)findViewById(R.id.btn_map);
        mGroupName = (TextView)findViewById(R.id.text1);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid()).child("Food_Rank");
        final int[] max_value = {0};
        //final int[] score = {0};

        databaseReference1.child("group").child(groupkey).child(groupname).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String email = dataSnapshot.getValue().toString();
                member.add(email);
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

        databaseReference2.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot val : dataSnapshot.getChildren()){
                    for(int i=0; i<member.size(); i++){
                        if(val.child("UserEmail").getValue(String.class).contains(member.get(i))){
                            if(!mem_uid.contains(val.getKey())){
                                mem_uid.add(val.getKey());
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference3.child("group").child(groupkey).child("data").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DataSnapshot ds = dataSnapshot;
                String name = ds.getKey();
                int val = ds.getValue(Integer.class).hashCode();
                score.put(name, val);
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

        final ImageView imageview = (ImageView)findViewById(R.id.imageView);
        final TextView textView = (TextView)findViewById(R.id.tv_recommend);

        for(int i = 0; i<score.size();i++){
            Toast.makeText(RecommendActivity.this, score.get(i), Toast.LENGTH_SHORT).show();
        }

        databaseReference3.child("group").child(groupkey).child("Like_Hate").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                DataSnapshot ds = dataSnapshot;
                String name = ds.getKey();
                int like = 0; int hate = 0;
                try {
                    like = ds.child("Like").getValue(Integer.class).hashCode();
                } catch (Exception e){}
                try {
                    hate = ds.child("Hate").getValue(Integer.class).hashCode();
                } catch (Exception e){}
                double value = 10.0 * ((double)like/(double)(like+hate));
                like_hate.put(name, value);
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

        databaseReference3.child("group").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String key = dataSnapshot.getKey();
                Map<String, Double> data = new HashMap<>();
                if(!key.equals(groupkey)){
                    for(DataSnapshot ds : dataSnapshot.child("Like_Hate").getChildren()) {
                        try {
                            String menu = ds.getKey();
                            int like = 0; int hate = 0;
                            try{
                                like = ds.child("Like").getValue(Integer.class).hashCode();
                            }catch (Exception e){}
                            try {
                                hate = ds.child("Hate").getValue(Integer.class).hashCode();
                            } catch (Exception e){}
                            double value = 10.0 * ((double) like / (double) (like + hate));
                            data.put(menu, value);
                        } catch (Exception e) {

                        }
                    }
                }
                if(!data.isEmpty()){
                    other_group.put(key, data);
                }
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

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd");
        final String formatDate = sdfNow.format(date);

        databaseReference4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (int i=0; i<mem_uid.size(); i++){
                    databaseReference4.child("User").child(mem_uid.get(i)).child("today_hate_food").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot ds : dataSnapshot.getChildren()) {
                                String time = ds.getValue().toString();
                                if(time.contains(formatDate)){
                                    String menu = ds.getKey();
                                    if(!today_hate.contains(menu)){
                                        today_hate.add(menu);
                                        int v = score.get(menu);
                                        score.remove(menu);
                                        score.put(menu, v-100);
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        rec_food.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ArrayList<String> sg = new ArrayList<>();
                sg.addAll(get_recommendations());
                mGroupName.setText(Integer.toString(sg.size()));
                //int max = 0;
                double max = like_hate_final.get("Korean");
                int index = 0;
                if(like_hate_final.isEmpty())
                    Toast.makeText(RecommendActivity.this, "score is empty", Toast.LENGTH_SHORT).show();

                for(String key : like_hate_final.keySet()){
                    if(max < like_hate_final.get(key))
                        max = like_hate_final.get(key);
                }
                for(String key : like_hate_final.keySet()){
                    if(max ==  like_hate_final.get(key))
                        menu1.add(key);
                }
                
                Random rand = new Random();
                if(!menu1.isEmpty()) {
                    final_menu = menu1.get(rand.nextInt(menu1.size()));
                    if(final_menu.equals("Korean")) {
                        imageview.setImageResource(R.drawable.rice);
                    }else if(final_menu.equals("Snack")) {
                        imageview.setImageResource(R.drawable.snack);
                    }else if(final_menu.equals("asian")){
                        imageview.setImageResource(R.drawable.sushi);
                    }else if(final_menu.equals("chicken")) {
                        imageview.setImageResource(R.drawable.chicken);
                    }else if(final_menu.equals("china")) {
                        imageview.setImageResource(R.drawable.china);
                    }else if(final_menu.equals("dessert")) {
                        imageview.setImageResource(R.drawable.dessert);
                    }else if(final_menu.equals("fast_food")) {
                        imageview.setImageResource(R.drawable.steak);
                    }else if(final_menu.equals("lunch_box")) {
                        imageview.setImageResource(R.drawable.lunch_box);
                    }else if(final_menu.equals("pizza")) {
                        imageview.setImageResource(R.drawable.pizza);
                    }else if(final_menu.equals("soup")) {
                        imageview.setImageResource(R.drawable.soup);
                    }else if(final_menu.equals("noodle")){
                        imageview.setImageResource(R.drawable.noodle);
                    }else if(final_menu.equals("ribs")){
                        imageview.setImageResource(R.drawable.ribs);
                    }else if(final_menu.equals("gukbap")){
                        imageview.setImageResource(R.drawable.gukbap);
                    }else if(final_menu.equals("sandwich")){
                        imageview.setImageResource(R.drawable.sandwich);
                    }else if(final_menu.equals("meat")){
                        imageview.setImageResource(R.drawable.meat);
                    }else if(final_menu.equals("tie")){
                        imageview.setImageResource(R.drawable.tie);
                    }else if(final_menu.equals("cold_noodle")){
                        imageview.setImageResource(R.drawable.cold_noodle);
                    }else if(final_menu.equals("udon")){
                        imageview.setImageResource(R.drawable.udon);
                    }else if(final_menu.equals("raw_fish")){
                        imageview.setImageResource(R.drawable.raw_fish);
                    }else if(final_menu.equals("curry")){
                        imageview.setImageResource(R.drawable.curry);
                    }else if(final_menu.equals("skewers")){
                        imageview.setImageResource(R.drawable.skewers);
                    }else if(final_menu.equals("boiled_chicken")){
                        imageview.setImageResource(R.drawable.boiled_chicken);
                    }else if(final_menu.equals("ramen")){
                        imageview.setImageResource(R.drawable.ramen);
                    }else if(final_menu.equals("mara")){
                        imageview.setImageResource(R.drawable.mara);
                    }else if(final_menu.equals("bossam")){
                        imageview.setImageResource(R.drawable.bossam);
                    }else if(final_menu.equals("fish")){
                        imageview.setImageResource(R.drawable.fish);
                    }
                    textView.setText(final_menu + " " + like_hate_final.get(final_menu));
                }
                else
                    Toast.makeText(RecommendActivity.this, "Menu Error", Toast.LENGTH_SHORT).show();

            }
        });

            Button button_like = (Button)findViewById(R.id.btn_like);
            Button button_hate = (Button)findViewById(R.id.btn_hate);
            button_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!menu1.isEmpty()){
                        final String food_name = final_menu;
                        final DatabaseReference ref = databaseReference1.child("group").child(groupkey).child("Like_Hate").child(food_name).child("Like");
                        ValueEventListener valueEventListener = new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                try {
                                    int num = dataSnapshot.getValue(Integer.class);
                                    ref.setValue(num + 1);
                                }catch (Exception e){
                                    ref.setValue(1);
                                }

                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                            }
                        };
                        ref.addListenerForSingleValueEvent(valueEventListener);
                    }
                    else{
                        Toast.makeText(RecommendActivity.this, "Menu is empty", Toast.LENGTH_SHORT).show();
                    }

                }
                private void onCancelled(@NonNull DatabaseError databaseError) {
                }

            });

        button_hate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!menu1.isEmpty()){
                    final String food_name = final_menu;
                    final DatabaseReference ref = databaseReference1.child("group").child(groupkey).child("Like_Hate").child(food_name).child("Hate");
                    ValueEventListener valueEventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            try {
                                int num = dataSnapshot.getValue(Integer.class);
                                ref.setValue(num + 1);
                            }catch (Exception e){
                                ref.setValue(1);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    };
                    ref.addListenerForSingleValueEvent(valueEventListener);
                }
                else{
                    Toast.makeText(RecommendActivity.this, "Menu is empty", Toast.LENGTH_SHORT).show();
                }

            }
            private void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });
        map_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                intent.putExtra("final_menu", final_menu);
                startActivity(intent);
            }
        });
    }

    //피어슨 점수 계산 함수
    private double sim_pearson(String other) {
        ArrayList<String> item = new ArrayList<>();
        //그룹에서 공통으로 평가된 항목 목록 구함
        for(int i=0; i<menu.length; i++){
            if(like_hate.containsKey(menu[i]) && other_group.get(other).containsKey(menu[i])){
                item.add(menu[i]);
            }
        }
        //공통 항목 개수
        int n = item.size();
        //공통항목이 없으면 0 리턴
        if(n==0){
            return 0;
        }
        //모든 평점을 합산
        double groupsum = 0;
        double othersum = 0;
        for(int i=0; i<item.size(); i++){
            groupsum += like_hate.get(item.get(i));
            othersum += other_group.get(other).get(item.get(i));
        }
        //제곱의 합을 계산
        double groupsumSq = 0;
        double othersumSq = 0;
        for(int i=0; i<item.size(); i++){
            groupsumSq += Math.pow(like_hate.get(item.get(i)), 2);
            othersumSq += Math.pow(other_group.get(other).get(item.get(i)), 2);
        }
        //곱의 합을 계산
        double pSum = 0;
        for (int i=0; i<item.size(); i++){
            pSum += like_hate.get(item.get(i))*other_group.get(other).get(item.get(i));
        }
        //피어슨 점수 계산
        double num = pSum - (groupsum*othersum/n);
        double den = Math.sqrt((groupsumSq-Math.pow(groupsum,2)/n) * (othersumSq-Math.pow(othersum,2)/n));
        if(den==0){
            return 0;
        }
        double r = num/den;
        return r;
    }

    private ArrayList<String> get_recommendations(){
        Map<String, Double> totals = new HashMap<>();
        Map<String, Double> simSums = new HashMap<>();
        ArrayList<String> similar_groups = new ArrayList<>();
        //모든 그룹에 대해 피어슨 점수 구함
        for(String key : other_group.keySet()){
            Double sim = sim_pearson(key);
            //0 이하 점수는 무시
            if(sim <= 0){
                continue;
            }
            //유사한 그룹 리스트에 저장
            similar_groups.add(key);
        }
        //like_hate_all.putAll(like_hate);
        for(int i=0; i<similar_groups.size(); i++){
            String g = similar_groups.get(i);
            for(String key : other_group.get(g).keySet()){
                if(like_hate_all.containsKey(key)){
                    double origin = like_hate_all.get(key);
                    double other = other_group.get(g).get(key);
                    like_hate_all.put(key, origin+other);
                }
                else {
                    double val = other_group.get(g).get(key);
                    like_hate_all.put(key, val);
                }
            }
        }
        for(String key : score.keySet()){
            if(like_hate_all.containsKey(key)){
                double sc = (double)score.get(key);
                double lh = like_hate_all.get(key);
                like_hate_final.put(key, sc + lh);
            }
            else {
                double sc = score.get(key);
                like_hate_final.put(key, sc);
            }
        }
        return similar_groups;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}