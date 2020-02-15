package com.example.schoolproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.schoolproject.Model.PTModel;
import com.example.schoolproject.MyAdapterPT;
import com.example.schoolproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PtmActivity extends AppCompatActivity {

    ArrayList<PTModel> data;
    RecyclerView mRecyclerView;
    MyAdapterPT ptadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptm);
        data=new ArrayList<>();
        mRecyclerView=findViewById(R.id.recycler_ptm);
         ptadapter=new MyAdapterPT(data);
         mRecyclerView.setLayoutManager(new LinearLayoutManager(PtmActivity.this));
         mRecyclerView.setAdapter(ptadapter);
        FirebaseDatabase.getInstance().getReference("pta").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    PTModel pt=ds.getValue(PTModel.class);
                    data.add(pt);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
