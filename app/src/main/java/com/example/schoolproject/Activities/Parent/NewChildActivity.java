package com.example.schoolproject.Activities.Parent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.schoolproject.Model.StudentModel;
import com.example.schoolproject.R;
import com.example.schoolproject.Utils.ParentAddChildAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class NewChildActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    HashMap<String,ArrayList<StudentModel>>  spinnerdata;
    Spinner spinner;
    ArrayList<StudentModel> child;
    ParentAddChildAdapter parentAddChildAdapter;
    String[] classes={"Class 1","Class 2","Class 3","Class 4"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_child);
        child=new ArrayList<>();
        recyclerView=findViewById(R.id.recycler_new_child);
        getSupportActionBar().setTitle("Kids :)");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.gradientback2));
        spinner=findViewById(R.id.spinner);
        parentAddChildAdapter=new ParentAddChildAdapter(child);
        recyclerView.setLayoutManager(new LinearLayoutManager(NewChildActivity.this));
        recyclerView.setAdapter(parentAddChildAdapter);
        spinner.setAdapter(new ArrayAdapter<String>(NewChildActivity.this,android.R.layout.simple_spinner_dropdown_item,classes));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                child=spinnerdata.get(classes[i]);
                parentAddChildAdapter=new ParentAddChildAdapter(child);
                recyclerView.setAdapter(parentAddChildAdapter);
                Log.i("onItemSelected: ",child.size()+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                    child=spinnerdata.get(classes[0]);
            }
        });
        spinnerdata=new HashMap<>();
        FirebaseDatabase.getInstance().getReference("students").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                spinnerdata.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    ArrayList<StudentModel> list=new ArrayList<>();
                    for(DataSnapshot dss:ds.getChildren())
                    {
                        list.add(dss.getValue(StudentModel.class));
                    }
                    spinnerdata.put(ds.getKey(),list);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
