package com.tujuh.tujuh_capstone_project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Friend extends Fragment {

    public ArrayList<MainData> arrayList = new ArrayList<>();
    public MainAdapter mainAdapter;
    public RecyclerView recyclerView;
    public LinearLayoutManager linearLayoutManager;

    public FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public DatabaseReference databaseReference = firebaseDatabase.getReference();
    public DataSnapshot dataSnapshot;
    public FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    ArrayList<String> data = new ArrayList<>();

    public View view;
    public EditText et_search_nickname;
    public Button btn_add;

    public String target_email;
    String uid;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid()).child("friends");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    String email = ds.child("friend_email").getValue().toString();
                    String nickname = ds.child("friend_nickname").getValue().toString();
                    if(!data.contains(nickname)) {
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
        };
        if(arrayList.isEmpty())
            db.addListenerForSingleValueEvent(eventListener);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_blank, container, false);
        //ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_blank, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.Recyclerview_f1);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        mainAdapter = new MainAdapter(arrayList);
        recyclerView.setAdapter(mainAdapter);
        et_search_nickname = (EditText)view.findViewById(R.id.et_search_nickname_f1);

        return view;
    }

//    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btn_add = (Button)view.findViewById(R.id.btn_add_f1);
        //final ArrayList<String> data = new ArrayList<>();
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

    //친구 리스트 뷰에 추가
    private void add_frined_to_view(final String email, String nickname){
        MainData mainData = new MainData(R.mipmap.ic_launcher, email, nickname);
        arrayList.add(mainData);
        mainAdapter.notifyDataSetChanged();
    }

}
