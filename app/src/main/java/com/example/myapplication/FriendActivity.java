package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.example.myapplication.ui.PeopleFragment;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FriendActivity extends AppCompatActivity {

    private ArrayList<MainData> arrayList;
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private String target_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        recyclerView = (RecyclerView)findViewById(R.id.Recyclerview_dashboard);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
        mainAdapter = new MainAdapter(arrayList);
        recyclerView.setAdapter(mainAdapter);

        final EditText et_search_nickname = (EditText)findViewById(R.id.et_search_nickname);
        Button btn_add = (Button)findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                target_email = et_search_nickname.getText().toString();

                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                final DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid()).child("friends");

                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    // 데이터 돌아다니면서 하나하나 확인
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds : dataSnapshot.getChildren()){
                            String email = ds.child("friend_email").getValue().toString();
                            if(email.equals(target_email)){
                                Toast.makeText(FriendActivity.this, "이미 등록한 사용자 입니다", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }

                        add_friend(target_email);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };
                db.addListenerForSingleValueEvent(eventListener);

            }
        });
    }

    // 처음 시작할때 띄우기
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid()).child("friends");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String email = ds.child("friend_email").getValue().toString();
                    String nickname = ds.child("friend_nickname").getValue().toString();
                    MainData mainData = new MainData(R.mipmap.ic_launcher, email, nickname);
                    arrayList.add(mainData);
                    mainAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        db.addListenerForSingleValueEvent(eventListener);
    }

    //사용자 존재 여부 체크 후 추가
    private void add_friend(final String email){

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("User");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                final DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("User");

                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String each_email = ds.child("UserEmail").getValue(String.class);
                    String each_nickname = ds.child("UserNickName").getValue(String.class);
                    if(each_email.equals(email)){
                        String uid = user.getUid();
                        db.child(uid).child("friends").child(ds.getKey()).child("friend_email").setValue(each_email);
                        db.child(uid).child("friends").child(ds.getKey()).child("friend_nickname").setValue(each_nickname);
                        add_frined_to_view(email, each_nickname);
                        return;
                    }
                }
                Toast.makeText(FriendActivity.this, "없는 사용자 입니다.", Toast.LENGTH_SHORT).show();
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