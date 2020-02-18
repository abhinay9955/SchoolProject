package com.example.schoolproject.Activities.General;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ExpandableListView;

import com.example.schoolproject.R;
import com.example.schoolproject.Utils.Expandalelistadapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CoursesActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    HashMap<String, List<String>> data;
    List<String> titles;
    private ExpandableListView mylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        progressDialog=new ProgressDialog(this);
        getSupportActionBar().setTitle("COURSES UNDER US");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.gradientback2));
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        data=new HashMap<>();
        titles=new ArrayList<>();
        mylist= (ExpandableListView)findViewById(R.id.exp_listview);
        final Expandalelistadapter myAdapter=new Expandalelistadapter(this,titles,data);
        mylist.setAdapter(myAdapter);

        FirebaseDatabase.getInstance().getReference("classes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                titles.clear();
                data.clear();
                      for(DataSnapshot ds:dataSnapshot.getChildren())
                      {
                          String key=ds.getKey();
                          titles.add(key);
                          List<String> arr=new ArrayList<>();
                          for(DataSnapshot dss: ds.getChildren())
                          {
                              arr.add(dss.getValue(String.class));
                          }

                          data.put(key,arr);
                      }
                      myAdapter.notifyDataSetChanged();
                      progressDialog.dismiss();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }
}
