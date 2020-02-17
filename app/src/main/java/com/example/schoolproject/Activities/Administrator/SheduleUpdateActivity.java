package com.example.schoolproject.Activities.Administrator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.schoolproject.Model.TutorModel;
import com.example.schoolproject.R;
import com.example.schoolproject.Utils.SheduleAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SheduleUpdateActivity extends AppCompatActivity {

    ArrayList<TutorModel> tutors;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    private SheduleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shedule_update);
        getSupportActionBar().setTitle("Shedules");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.gradientback2));
        tutors=new ArrayList<>();
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        recyclerView=findViewById(R.id.suRecyclerview);
        adapter=new SheduleAdapter(tutors,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseDatabase.getInstance().getReference("Tutor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                tutors.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    TutorModel tm=new TutorModel(ds.child("email").getValue(String.class),ds.child("contact").getValue(String.class),ds.child("name").getValue(String.class),ds.child("tutorid").getValue(String.class),ds.getKey());
                    tutors.add(tm);
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
              progressDialog.dismiss();
            }
        });
    }
}
