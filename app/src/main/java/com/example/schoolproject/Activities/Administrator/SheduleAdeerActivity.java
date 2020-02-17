package com.example.schoolproject.Activities.Administrator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.schoolproject.Model.SheduleModel;
import com.example.schoolproject.R;
import com.example.schoolproject.Utils.SheduleAdapter;
import com.example.schoolproject.Utils.TutorSheduleAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SheduleAdeerActivity extends AppCompatActivity {

    String id,name,tutorid;
    ArrayList<SheduleModel> schedules;
    TutorSheduleAdapter scheduleAdapter;
    RecyclerView recycler;
    TextView name_tv,tutorid_tv;
    EditText std,period,subject;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shedule_adeer);
        initiaize();
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        schedules=new ArrayList<>();
        id=getIntent().getStringExtra("id");
        name=getIntent().getStringExtra("name");
        tutorid=getIntent().getStringExtra("tutorid");
        name_tv.setText(name);
        tutorid_tv.setText(tutorid);
        scheduleAdapter=new TutorSheduleAdapter(schedules,this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(scheduleAdapter);
        FirebaseDatabase.getInstance().getReference("Tutor").child(id).child("schedule").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                schedules.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    SheduleModel sm=ds.getValue(SheduleModel.class);
                    schedules.add(sm);
                }
                scheduleAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void initiaize()
    {
        std=findViewById(R.id.ism_class);
        period=findViewById(R.id.ism_period);
        subject=findViewById(R.id.ism_subject);
        recycler=findViewById(R.id.recyclerview);
        name_tv=findViewById(R.id.su_name);
        tutorid_tv=findViewById(R.id.su_tid);
    }
}
