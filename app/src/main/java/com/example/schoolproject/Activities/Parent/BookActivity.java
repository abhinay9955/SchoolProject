package com.example.schoolproject.Activities.Parent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.schoolproject.Model.ParentAppointmentModel;
import com.example.schoolproject.R;
import com.example.schoolproject.Utils.ParentAppointmentAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    ArrayList<ParentAppointmentModel> tutors;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    ParentAppointmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        progressDialog=new ProgressDialog(this);
        tutors=new ArrayList<>();
        progressDialog.setMessage("Loading...");
        getSupportActionBar().setTitle("Parent");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.gradientback2));
        progressDialog.setCancelable(false);
        progressDialog.show();
        recyclerView=findViewById(R.id.recycler_book);
        adapter=new ParentAppointmentAdapter(tutors);
        recyclerView.setLayoutManager(new LinearLayoutManager(BookActivity.this));
        recyclerView.setAdapter(adapter);
        FirebaseDatabase.getInstance().getReference("Tutor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               tutors.clear();
               for(DataSnapshot ds:dataSnapshot.getChildren())
               {
                   ParentAppointmentModel pam=new ParentAppointmentModel(ds.child("name").getValue(String.class),ds.child("tutorid").getValue(String.class),ds.getKey());
                   tutors.add(pam);
               }
               adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
