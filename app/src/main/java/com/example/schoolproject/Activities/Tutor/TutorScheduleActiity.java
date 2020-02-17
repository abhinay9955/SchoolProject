package com.example.schoolproject.Activities.Tutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.schoolproject.Model.SheduleModel;
import com.example.schoolproject.R;
import com.example.schoolproject.Utils.TutorSheduleAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TutorScheduleActiity extends AppCompatActivity {

    RecyclerView recyclerView;
    TutorSheduleAdapter adapter;
    ArrayList<SheduleModel> schedules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_schedule_actiity);
        getSupportActionBar().setTitle("My Shedules");
        schedules=new ArrayList<>();
        recyclerView=findViewById(R.id.tutor_schedule_recycler);
        adapter=new TutorSheduleAdapter(schedules,TutorScheduleActiity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(TutorScheduleActiity.this));
        recyclerView.setAdapter(adapter);
        FirebaseDatabase.getInstance().getReference("Tutor").child(FirebaseAuth.getInstance().getUid()).child("schedule").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              schedules.clear();
              for(DataSnapshot ds:dataSnapshot.getChildren())
              {
                  SheduleModel sm=new SheduleModel(ds.child("subject").getValue(String.class),ds.child("period").getValue(String.class),ds.child("class").getValue(String.class));
                  schedules.add(sm);
              }
              adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
