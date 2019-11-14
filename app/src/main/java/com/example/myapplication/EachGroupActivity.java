package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EachGroupActivity extends AppCompatActivity {

    private ArrayList<MainData> arrayList;
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private String target_email, target_uid;
    private String groupkey, groupname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_group);

        Intent intent = getIntent();
        groupkey = (String) intent.getExtras().get("groupkey");
        groupname = (String) intent.getExtras().get("groupname");

        recyclerView = (RecyclerView)findViewById(R.id.Recyclerview_each_group);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
        mainAdapter = new MainAdapter(arrayList);
        recyclerView.setAdapter(mainAdapter);

        final EditText et_search_email = (EditText)findViewById(R.id.et_search_email);
        Button btn_add = (Button)findViewById(R.id.btn_add_eachgroup);
        Button btn_move = (Button)findViewById(R.id.btn_move_to_recommend);
        Button btn_vote = (Button)findViewById(R.id.button_vote);

        btn_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                target_email = et_search_email.getText().toString();

                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                final DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child(groupname);

                invite_member();

//                ValueEventListener eventListener = new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        for(DataSnapshot ds : dataSnapshot.getChildren()){
//                            String email = ds.getValue().toString();
//                            MainData mainData = new MainData(R.mipmap.ic_launcher, email, "");
//                            arrayList.add(mainData);
//                            mainAdapter.notifyDataSetChanged();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                };
//                db.addListenerForSingleValueEvent(eventListener);

            }
        });

        btn_vote.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intend = new Intent(getApplicationContext(), Vote.class);
                intend.putExtra("groupkey", groupkey);
                intend.putExtra("groupname", groupname);
                startActivity(intend);
            }
        });

        btn_move.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RecommendActivity.class);
                intent.putExtra("groupkey", groupkey);
                intent.putExtra("groupname", groupname);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child(groupname);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String email = ds.getValue().toString();
                    MainData mainData = new MainData(R.mipmap.ic_launcher, email, "");
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

    private void invite_member(){
        final DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("User");
        final DatabaseReference group_db = FirebaseDatabase.getInstance().getReference().child("group").child(groupkey).child(groupname);

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String email = ds.child("UserEmail").getValue().toString();
                    if(email.equals(target_email)) {
                        add_frined_to_view(target_email, "");
                        db.child(ds.getKey()).child("group").child(groupkey).setValue(groupname);
                        group_db.push().setValue(target_email);
                        return;
                    }
                }
                Toast.makeText(EachGroupActivity.this, "없는 사용자 입니다.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        db.addListenerForSingleValueEvent(eventListener);
    }


    //친구 리스트 뷰에 추가
    private void add_frined_to_view(final String email, String nickname){
        MainData mainData = new MainData(R.mipmap.ic_launcher, email, nickname);
        arrayList.add(mainData);
        mainAdapter.notifyDataSetChanged();
    }


    // 뒤로가기
    @Override
    public void onBackPressed() {
        finish();
    }
}
