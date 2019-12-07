package com.tujuh.tujuh_capstone_project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class today extends Fragment {

    public View view;

    public TextView textView_id;
    public TextView textView_email;

    public Button button1;
    public Button button2;
    public Button button3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_today, container, false);
        textView_id = view.findViewById(R.id.profile_id);
        textView_email = view.findViewById(R.id.profile_email);
        button1 = view.findViewById(R.id.btn_move_to_like);
        button2 = view.findViewById(R.id.btn_move_to_hate);
        button3 = view.findViewById(R.id.btn_move_to_map);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid());
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String email = dataSnapshot.child("UserEmail").getValue().toString();
                String nickname = dataSnapshot.child("UserNickName").getValue().toString();
                textView_id.setText(nickname);
                textView_email.setText(email);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        db.addListenerForSingleValueEvent(eventListener);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Edit_like.class);
                startActivity(intent);
            }
        });
        button1.setBackgroundColor(Color.WHITE);
        button2.setBackgroundColor(Color.WHITE);
        button3.setBackgroundColor(Color.WHITE);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Edit_dislike.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MapActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
