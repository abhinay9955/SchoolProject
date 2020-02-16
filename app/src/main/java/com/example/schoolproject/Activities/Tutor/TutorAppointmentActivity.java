package com.example.schoolproject.Activities.Tutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.schoolproject.Model.ParentModel;
import com.example.schoolproject.Model.TutorAppointmentModel;
import com.example.schoolproject.R;
import com.example.schoolproject.Utils.AppointmentRviewAddapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class TutorAppointmentActivity extends AppCompatActivity {

    HashMap<String, ParentModel> parents;
    ArrayList<TutorAppointmentModel>  appointments;
    RecyclerView recyclerView;
    AppointmentRviewAddapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_appointment);
        parents=new HashMap<>();
        appointments=new ArrayList<>();
        recyclerView=findViewById(R.id.recylerview_tutorapppointment);
        adapter=new AppointmentRviewAddapter(appointments);
        recyclerView.setLayoutManager(new LinearLayoutManager(TutorAppointmentActivity.this));
        recyclerView.setAdapter(adapter);
        FirebaseDatabase.getInstance().getReference("Parent").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 parents.clear();
                 for(DataSnapshot ds:dataSnapshot.getChildren())
                 {
                         ParentModel pm=new ParentModel(ds.child("name").getValue(String.class),ds.child("email").getValue(String.class),ds.child("contact").getValue(String.class),ds.getKey());
                         parents.put(ds.getKey(),pm);
                 }

                 fillappointment();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void fillappointment()
    {
        FirebaseDatabase.getInstance().getReference("Tutor").child(FirebaseAuth.getInstance().getUid()).child("Appointments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                appointments.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    String pid=ds.child("pid").getValue(String.class);
                    TutorAppointmentModel tam=new TutorAppointmentModel(parents.get(pid).getName(),parents.get(pid).getContact(),ds.child("date").getValue(String.class));
                    appointments.add(tam);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
