package com.example.schoolproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.schoolproject.Model.PTModel;
import com.example.schoolproject.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ParentActivity extends AppCompatActivity {

    HashMap<String,HashMap<String, PTModel>> data;
    HashMap<String,ArrayList<PTModel>>  spinnerdata;
    ArrayList<String> classes;
    ProgressDialog progressDialog;
    ArrayList<PTModel> children;
    private FirebaseAuth mAuth;

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
        mAuth=FirebaseAuth.getInstance();
        FirebaseDatabase.getInstance().getReference("students").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                data.clear();
                spinnerdata.clear();
                classes.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    classes.add(ds.getKey());
                    HashMap<String,PTModel> map=new HashMap<>();
                    ArrayList<PTModel> list=new ArrayList<>();
                    for(DataSnapshot dss:ds.getChildren())
                    {
                        map.put(dss.getKey(),dss.getValue(PTModel.class));
                        list.add(dss.getValue(PTModel.class));
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


    public void fillParent()
    {
        FirebaseDatabase.getInstance().getReference("parent").child("children").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                children.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                   PTModel pm=data.get(ds.child("std").getValue(String.class)).get(ds.child("id").getValue(String.class));
                   children.add(pm);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.tutormenu,menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.signouttutor)
        {
            mAuth.signOut();
            Intent intent=new Intent(ParentActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();

        }


        return true;
    }
}

