package com.example.schoolproject.Activities.Tutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
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
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_child_info);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        child=new ArrayList<>();
        spinnerdata=new HashMap<>();
        init();
        recyclerView=findViewById(R.id.ucirecycler_new_child);
        spinner=findViewById(R.id.ucispinner);
        Log.i("onCreateuic: ",child.toString());
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

                //Log.i("onItemSelectedteupda: ",spinnerdata+"");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                child=spinnerdata.get(classes[0]);
                updateChildAdapter=new UpdateChildInfoAdapter(child);
                recyclerView.setAdapter(updateChildAdapter);

            }
        });

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
                    Log.i( "onDataChangeuci: ",list.size()+"");
                    spinnerdata.put(ds.getKey(),list);
                }
                updateChildAdapter.notifyDataSetChanged();
                progressDialog.dismiss();
                spinner.setSelection(1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
              progressDialog.dismiss();
            }
        });



    }

    public void init()
    {
        spinnerdata.put(classes[0],new ArrayList<StudentModel>());
        spinnerdata.put(classes[1],new ArrayList<StudentModel>());
        spinnerdata.put(classes[2],new ArrayList<StudentModel>());
        spinnerdata.put(classes[3],new ArrayList<StudentModel>());
    }
}
