package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.internal.IdTokenListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.internal.InternalTokenResult;

import java.util.ArrayList;

//이메일검색해서 사용자 찾고 그룹 만들기
public class GroupActivity extends AppCompatActivity {

    private ArrayList<MainData> arrayList;
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private EditText mEmailSearch, mGroupName;
    private Button mButton, mSearch, mButton1;
    private ListView mListView;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    private DataSnapshot dataSnapshot;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    String key, uid;
    String groupname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = (RecyclerView)findViewById(R.id.Recyclerview_group);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
        mainAdapter = new MainAdapter(arrayList);
        recyclerView.setAdapter(mainAdapter);


        mEmailSearch = (EditText) findViewById(R.id.emailsearch);
        mGroupName = (EditText) findViewById(R.id.group_name);
        mButton = (Button) findViewById(R.id.button1);
        mSearch = (Button) findViewById(R.id.button2);
//        mListView = (ListView) findViewById(R.id.listView1);
//        mButton1 = (Button) findViewById(R.id.button3);
        final ArrayList<String> data = new ArrayList<>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.fragment1, data);

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
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                };
                db.addListenerForSingleValueEvent(eventListener);
                databaseReference.child("User").child(uid).child("group").child(key).setValue(groupname);
                MainData mainData = new MainData(R.mipmap.ic_launcher, groupname, key);
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
                    MainData mainData = new MainData(R.mipmap.ic_launcher, email, ds.getKey().toString());
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
