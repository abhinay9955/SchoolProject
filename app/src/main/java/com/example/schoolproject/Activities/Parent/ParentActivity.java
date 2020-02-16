package com.example.schoolproject.Activities.Parent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.schoolproject.Model.StudentModel;
import com.example.schoolproject.R;
import com.example.schoolproject.Utils.ChildAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ParentActivity extends AppCompatActivity {

    HashMap<String,HashMap<String, StudentModel>> data;
    HashMap<String,ArrayList<StudentModel>>  spinnerdata;
    ArrayList<String> classes;
    ProgressDialog progressDialog;
    ArrayList<StudentModel> children;
    RecyclerView mRecycler;
    ChildAdapter adapter;
    Button newchild,book,ptmlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        progressDialog=new ProgressDialog(ParentActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        spinnerdata=new HashMap<>();
        data=new HashMap<>();
        children=new ArrayList<>();
        classes=new ArrayList<>();
        initiialise();
        mRecycler.setLayoutManager(new LinearLayoutManager(ParentActivity.this));;
        adapter=new ChildAdapter(children);
        mRecycler.setAdapter(adapter);
        newchild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ParentActivity.this,NewChildActivity.class));
            }
        });

        ptmlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ParentActivity.this,PtmActivity.class));
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        FirebaseDatabase.getInstance().getReference("students").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                data.clear();
                spinnerdata.clear();
                classes.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    classes.add(ds.getKey());
                    HashMap<String,StudentModel> map=new HashMap<>();
                    ArrayList<StudentModel> list=new ArrayList<>();
                    for(DataSnapshot dss:ds.getChildren())
                    {
                        list.add(dss.getValue(StudentModel.class));
                    }
                    spinnerdata.put(ds.getKey(),list);
                    data.put(ds.getKey(),map);
                }
                progressDialog.dismiss();

                fillParent();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    public void initiialise()
    {
        mRecycler=findViewById(R.id.recycler_pa);
        newchild=findViewById(R.id.addchild);
        ptmlist=findViewById(R.id.ptm);
        book=findViewById(R.id.book);
    }


    public void fillParent()
    {

        FirebaseDatabase.getInstance().getReference("parent").child("children").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                children.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                   StudentModel pm=data.get(ds.child("std").getValue(String.class)).get(ds.child("id").getValue(String.class));
                   children.add(pm);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
