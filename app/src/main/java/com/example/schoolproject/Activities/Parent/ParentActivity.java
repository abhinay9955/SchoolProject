package com.example.schoolproject.Activities.Parent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schoolproject.Activities.General.CoursesActivity;
import com.example.schoolproject.Activities.General.LoginActivity;
import com.example.schoolproject.Activities.General.MapActivity;

import com.example.schoolproject.Activities.General.AcademicActivity;

import com.example.schoolproject.Model.StudentModel;
import com.example.schoolproject.R;
import com.google.firebase.auth.FirebaseAuth;
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

    private FirebaseAuth mAuth;

    ArrayList<StudentModel> children;
    RecyclerView mRecycler;
    ChildAdapter adapter;
    Button newchild,book,ptmlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        getSupportActionBar().setTitle("Parent");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.gradientback2));
        progressDialog=new ProgressDialog(ParentActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        spinnerdata=new HashMap<>();
        data=new HashMap<>();
        children=new ArrayList<>();
        classes=new ArrayList<>();
        mAuth=FirebaseAuth.getInstance();
        initiialise();
        mRecycler.setLayoutManager(new LinearLayoutManager(ParentActivity.this));
        Log.i( "onCreate: ",children.size()+"");
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
                startActivity(new Intent(ParentActivity.this,BookActivity.class));
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
                        map.put(dss.getKey(),dss.getValue(StudentModel.class));
                    }
                    spinnerdata.put(ds.getKey(),list);
                    data.put(ds.getKey(),map);
                }
                Log.i("onDataChangedata: ",data.toString());
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

        FirebaseDatabase.getInstance().getReference("Parent").child(FirebaseAuth.getInstance().getUid()).child("children").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                children.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {



                    StudentModel pm=data.get(ds.child("std").getValue(String.class)).get(ds.child("id").getValue(String.class));
                   children.add(pm);
                }

                adapter.notifyDataSetChanged();

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
        if(item.getItemId()==R.id.newcourse)
        {
            Intent intent=new Intent(this, CoursesActivity.class);
            startActivity(intent);

        }
        if(item.getItemId()==R.id.newlocation)
        {
            startActivity(new Intent(ParentActivity.this, MapActivity.class));
        }
        if(item.getItemId()==R.id.newcontact)
        {
            final Dialog dialog=new Dialog(ParentActivity.this);
            dialog.setContentView(R.layout.contact_dialog);
            TextView ok;
            ok=dialog.findViewById(R.id.ok);
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog.show();


        }

        if(item.getItemId()==R.id.academic) {
            Intent intent = new Intent(ParentActivity.this, AcademicActivity.class);
            startActivity(intent);
        }



        if(item.getItemId()==R.id.newwebsite)
        {
            Toast.makeText(this, "working", Toast.LENGTH_SHORT).show();

        }
        if(item.getItemId()==R.id.newsign_out)
        {
            mAuth.signOut();
            Intent intent1=new Intent(ParentActivity.this, LoginActivity.class);
            startActivity(intent1);
            finish();

        }


        return true;

    }
}

