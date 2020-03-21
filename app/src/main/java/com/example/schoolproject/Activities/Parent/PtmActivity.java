package com.example.schoolproject.Activities.Parent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.schoolproject.Model.PTModel;
import com.example.schoolproject.MyAdapterPT;
import com.example.schoolproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        getSupportActionBar().setTitle("P-T meets");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.gradientback2));
         ptadapter=new MyAdapterPT(data);
         mRecyclerView.setLayoutManager(new LinearLayoutManager(PtmActivity.this));
         mRecyclerView.setAdapter(ptadapter);
        FirebaseDatabase.getInstance().getReference("pta").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data.clear();
                Date cur=new Date();
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    PTModel pt=ds.getValue(PTModel.class);
                    Date date= null;
                    try {
                        date = new SimpleDateFormat("dd-MMM-yyyy hh:mm a").parse(pt.getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Log.i("onDataChange: ",cur.toString()+" / "+date.toString());
                    if(date.after(cur))
                       data.add(pt);
                }
                ptadapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mRecyclerView.setAdapter(ptadapter);


    }
}
