package com.example.schoolproject.Activities.Tutor;

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

import com.example.schoolproject.Activities.Parent.NewChildActivity;
import com.example.schoolproject.Model.StudentModel;
import com.example.schoolproject.R;
import com.example.schoolproject.Utils.ParentAddChildAdapter;
import com.example.schoolproject.Utils.UpdateChildInfoAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateChildInfoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private HashMap<String, ArrayList<StudentModel>> spinnerdata;
    private Spinner spinner;
    private ArrayList<StudentModel> child;
    private UpdateChildInfoAdapter updateChildAdapter;
    private String[] classes={"Class 1","Class 2","Class 3","Class 4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_child_info);
        child=new ArrayList<>();
        recyclerView=findViewById(R.id.ucirecycler_new_child);
        spinner=findViewById(R.id.ucispinner);
        updateChildAdapter=new UpdateChildInfoAdapter(child);
        recyclerView.setLayoutManager(new LinearLayoutManager(UpdateChildInfoActivity.this));
        recyclerView.setAdapter(updateChildAdapter);
        spinner.setAdapter(new ArrayAdapter<String>(UpdateChildInfoActivity.this,android.R.layout.simple_spinner_dropdown_item,classes));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                child=spinnerdata.get(classes[i]);
                updateChildAdapter=new UpdateChildInfoAdapter(child);
                recyclerView.setAdapter(updateChildAdapter);
               // Log.i("onItemSelected: ",child.size()+"");
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
