package com.tujuh.tujuh_capstone_project;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//이메일검색해서 사용자 찾고 그룹 만들기
public class GroupActivity extends AppCompatActivity {

    private ArrayList<GroupData> arrayList;
    private GroupAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private EditText mEmailSearch, mGroupName;
    private Button mButton, mSearch, mButton1;
    private ListView mListView;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DatabaseReference databaseReference1 = firebaseDatabase.getReference();
    private DataSnapshot dataSnapshot;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    String key, uid;
    String groupname;
    Food_Ranking food_ranking = new Food_Ranking();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = (RecyclerView)findViewById(R.id.Recyclerview_group);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
        mainAdapter = new GroupAdapter(arrayList);
        recyclerView.setAdapter(mainAdapter);


        mEmailSearch = (EditText) findViewById(R.id.emailsearch);
        mGroupName = (EditText) findViewById(R.id.group_name);
        mButton = (Button) findViewById(R.id.button1);
        mSearch = (Button) findViewById(R.id.button2);
//        mListView = (ListView) findViewById(R.id.listView1);
//        mButton1 = (Button) findViewById(R.id.button3);
        final ArrayList<String> data = new ArrayList<>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.fragment1, data);

        food_ranking.Korean = 0;
        food_ranking.Snack = 0;
        food_ranking.dessert = 0;
        food_ranking.curtlet = 0;
        food_ranking.chicken = 0;
        food_ranking.pizza = 0;
        food_ranking.asian = 0;
        food_ranking.china = 0;
        food_ranking.pork = 0;
        food_ranking.soup = 0;
        food_ranking.lunch_box = 0;
        food_ranking.fast_food = 0;
        food_ranking.noodle = 0;
        food_ranking.ribs = 0;
        food_ranking.gukbap = 0;
        food_ranking.sandwich = 0;
        food_ranking.meat = 0;
        food_ranking.tie = 0;
        food_ranking.cold_noodle = 0;
        food_ranking.udon = 0;
        food_ranking.raw_fish = 0;
        food_ranking.curry = 0;
        food_ranking.skewers = 0;
        food_ranking.boiled_chicken = 0;
        food_ranking.ramen = 0;
        food_ranking.mara = 0;
        food_ranking.bossam = 0;
        food_ranking.fish = 0;

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupname = mGroupName.getText().toString();
                DatabaseReference newDatabaseReference = databaseReference.child("group").push();
                key = newDatabaseReference.getKey();
//                databaseReference.child("group").child(key).child(groupname).setValue(groupname);
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                uid = currentUser.getUid();
                //그룹에 자기 자신 추가
                DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("User").child(uid).child("UserEmail");
                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String email = dataSnapshot.getValue().toString();
                        databaseReference.child("group").child(key).child(groupname).push().setValue(email);
                        databaseReference.child("User").child(uid).child("Food_Rank").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Food_Ranking food = dataSnapshot.getValue(Food_Ranking.class);
                                food_ranking.Korean += food.Korean;
                                food_ranking.Snack += food.Snack;
                                food_ranking.dessert += food.dessert;
                                food_ranking.curtlet += food.curtlet;
                                food_ranking.chicken += food.chicken;
                                food_ranking.pizza += food.pizza;
                                food_ranking.asian += food.asian;
                                food_ranking.china += food.china;
                                food_ranking.pork += food.pork;
                                food_ranking.soup += food.soup;
                                food_ranking.lunch_box += food.lunch_box;
                                food_ranking.fast_food += food.fast_food;
                                food_ranking.noodle += food.noodle;
                                food_ranking.ribs += food.ribs;
                                food_ranking.gukbap += food.gukbap;
                                food_ranking.sandwich += food.sandwich;
                                food_ranking.meat += food.meat;
                                food_ranking.tie += food.tie;
                                food_ranking.cold_noodle += food.cold_noodle;
                                food_ranking.udon += food.udon;
                                food_ranking.raw_fish += food.raw_fish;
                                food_ranking.curry += food.curry;
                                food_ranking.skewers += food.skewers;
                                food_ranking.boiled_chicken += food.boiled_chicken;
                                food_ranking.ramen += food.ramen;
                                food_ranking.mara += food.mara;
                                food_ranking.bossam += food.bossam;
                                food_ranking.fish += food.fish;
                                databaseReference.child("group").child(key).child("data").setValue(food_ranking);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                 };
                db.addListenerForSingleValueEvent(eventListener);
                databaseReference.child("User").child(uid).child("group").child(key).setValue(groupname);
                GroupData mainData = new GroupData(R.mipmap.ic_launcher, groupname, key, 1);
                arrayList.add(mainData);
                mainAdapter.notifyDataSetChanged();

                Toast.makeText(GroupActivity.this, R.string.group_register_success, Toast.LENGTH_SHORT).show();
            }
        });

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email;
                email = mEmailSearch.getText().toString();
                String groupname;
                groupname = mGroupName.getText().toString();
                databaseReference.child("User").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot val : dataSnapshot.getChildren()){
                            if(val.child("UserEmail").getValue(String.class).contains(mEmailSearch.getText().toString())){

                                String mem_uid = val.getKey();
                                if(!data.contains(mEmailSearch.getText().toString())){
                                    databaseReference.child("group").child(key).child(mGroupName.getText().toString()).push().setValue(mEmailSearch.getText().toString());
                                    adapter.add(mEmailSearch.getText().toString());
                                    databaseReference.child("User").child(mem_uid).child("Food_Rank").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            Food_Ranking food = dataSnapshot.getValue(Food_Ranking.class);
                                            food_ranking.Korean += food.Korean;
                                            food_ranking.Snack += food.Snack;
                                            food_ranking.dessert += food.dessert;
                                            food_ranking.curtlet += food.curtlet;
                                            food_ranking.chicken += food.chicken;
                                            food_ranking.pizza += food.pizza;
                                            food_ranking.asian += food.asian;
                                            food_ranking.china += food.china;
                                            food_ranking.pork += food.pork;
                                            food_ranking.soup += food.soup;
                                            food_ranking.lunch_box += food.lunch_box;
                                            food_ranking.fast_food += food.fast_food;
                                            food_ranking.noodle += food.noodle;
                                            food_ranking.ribs += food.ribs;
                                            food_ranking.gukbap += food.gukbap;
                                            food_ranking.sandwich += food.sandwich;
                                            food_ranking.meat += food.meat;
                                            food_ranking.tie += food.tie;
                                            food_ranking.cold_noodle += food.cold_noodle;
                                            food_ranking.udon += food.udon;
                                            food_ranking.raw_fish += food.raw_fish;
                                            food_ranking.curry += food.curry;
                                            food_ranking.skewers += food.skewers;
                                            food_ranking.boiled_chicken += food.boiled_chicken;
                                            food_ranking.ramen += food.ramen;
                                            food_ranking.mara += food.mara;
                                            food_ranking.bossam += food.bossam;
                                            food_ranking.fish += food.fish;
                                            databaseReference.child("group").child(key).child("data").setValue(food_ranking);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    });
                                }
                                databaseReference.child("User").child(mem_uid).child("group").child(key).setValue(mGroupName.getText().toString());
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                //databaseReference.child("group").child(key).child(groupname).push().setValue(email);
                //adapter.add(email);
                //mListView.setAdapter(adapter);
                //if (databaseReference.child("User").equals(email)) {
                    //databaseReference.child("group").child(groupname).push().setValue(email);
                    //Toast.makeText(GroupActivity.this, R.string.group_register_success, Toast.LENGTH_SHORT).show();
                //}
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid()).child("group");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String email = ds.getValue().toString();
                    GroupData mainData = new GroupData(R.mipmap.ic_launcher, email, ds.getKey(), ds.getChildrenCount());
                    arrayList.add(mainData);
                    mainAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        if(arrayList.isEmpty())
            db.addListenerForSingleValueEvent(eventListener);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
