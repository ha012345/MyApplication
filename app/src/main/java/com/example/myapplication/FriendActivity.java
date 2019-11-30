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

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DataSnapshot dataSnapshot;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    ArrayList<String> data = new ArrayList<>();

    private String target_email;
    String uid;

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

        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child("User").child(user.getUid()).child("friends").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String email = ds.child("friend_email").getValue(String.class);
                    String nickname = ds.child("friend_nickname").getValue(String.class);
                    if(!data.contains(nickname)){
                        MainData mainData = new MainData(R.mipmap.ic_launcher, email, nickname);
                        arrayList.add(mainData);
                        data.add(nickname);
                        mainAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nickname;
                nickname = et_search_nickname.getText().toString();
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                uid = currentUser.getUid();
                databaseReference.child("User").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot val : dataSnapshot.getChildren()){
                            if(val.child("UserNickName").getValue(String.class).contains(nickname)){

                                String mem_uid = val.getKey();
                                String email = val.child("UserEmail").getValue(String.class);
                                //String nickname = val.child("UserNickName").getValue(String.class);
                                if(!data.contains(nickname)){
                                    databaseReference.child("User").child(uid).child("friends").child(mem_uid).child("friend_email").setValue(email);
                                    databaseReference.child("User").child(uid).child("friends").child(mem_uid).child("friend_nickname").setValue(nickname);
                                    data.add(nickname);
                                    add_frined_to_view(email, nickname);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
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