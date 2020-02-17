package com.example.schoolproject.Activities.Administrator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schoolproject.Model.SheduleModel;
import com.example.schoolproject.R;
import com.example.schoolproject.Utils.SheduleAdapter;
import com.example.schoolproject.Utils.TutorSheduleAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class SheduleAdeerActivity extends AppCompatActivity {

    String id,name,tutorid;
    ArrayList<SheduleModel> schedules;
    TutorSheduleAdapter scheduleAdapter;
    RecyclerView recycler;
    TextView name_tv,tutorid_tv;
    EditText std,period,subject;
    ProgressDialog progressDialog;
    Button addschedule;

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
        addschedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(std.getText().toString()) || TextUtils.isEmpty(period.getText().toString()) || TextUtils.isEmpty(subject.getText().toString()))
                {
                    Toast.makeText(SheduleAdeerActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    HashMap<String ,Object> data=new HashMap<>();
                    data.put("class",std.getText().toString());
                    data.put("period",period.getText().toString());
                    data.put("subject",subject.getText().toString());
                    FirebaseDatabase.getInstance().getReference("Tutor").child(id).child("schedule").push().updateChildren(data);
                    Toast.makeText(SheduleAdeerActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
        FirebaseDatabase.getInstance().getReference("Tutor").child(id).child("schedule").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                schedules.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    SheduleModel sm=new SheduleModel(ds.child("subject").getValue(String.class),ds.child("period").getValue(String.class),ds.child("class").getValue(String.class));
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
        addschedule=findViewById(R.id.su_add_button);
    }
}
