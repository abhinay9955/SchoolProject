package com.example.schoolproject.Activities.Tutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schoolproject.Model.StudentModel;
import com.example.schoolproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class FilldataChildActivity extends AppCompatActivity {

    EditText grade,rank,tdp,twd;
    TextView name,parent,roll,std,contact;
    String id,sstd;
    Button filldata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filldata_child);
        id=getIntent().getStringExtra("id");
        sstd=getIntent().getStringExtra("class");
        initialize();
        filldata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(grade.getText().toString()) || TextUtils.isEmpty(rank.getText().toString()) || TextUtils.isEmpty(tdp.getText().toString()) || TextUtils.isEmpty(twd.getText().toString()))
                {
                    Toast.makeText(FilldataChildActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (!isNumber(grade.getText().toString()) || !isNumber(rank.getText().toString()) || !isNumber(twd.getText().toString()) || !isNumber(tdp.getText().toString())
                    ) {

                        Toast.makeText(FilldataChildActivity.this, "Grade,Rank,Total working days and Total days present must be numbers only", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        HashMap<String,Object> data=new HashMap<>();
                        data.put("grade",Integer.parseInt(grade.getText().toString()));
                        data.put("rank",Integer.parseInt(rank.getText().toString()));
                        data.put("tdp",Integer.parseInt(tdp.getText().toString()));
                        data.put("twd",Integer.parseInt(twd.getText().toString()));
                        FirebaseDatabase.getInstance().getReference("students").child(getIntent().getStringExtra("class")).child(id).updateChildren(data);
                        Toast.makeText(FilldataChildActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });


        FirebaseDatabase.getInstance().getReference("students").child(sstd).child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                StudentModel sm=dataSnapshot.getValue(StudentModel.class);
                fillvalues(sm);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void fillvalues(StudentModel sm)
    {
        name.setText(sm.getName());
        roll.setText(sm.getRoll());
        std.setText(sm.getStd());
        contact.setText(sm.getContact());
        parent.setText(sm.getParent());
        grade.setText((sm.getGrade()==-1?"N/A":sm.getGrade()+""));
        rank.setText((sm.getRank()==-1?"N/A":sm.getRank()+""));
        tdp.setText((sm.getTdp()==-1?"N/A":sm.getTdp()+""));
        twd.setText((sm.getTwd()==-1?"N/A":sm.getTwd()+""));
    }

    public boolean isNumber(String num)
    {
        boolean ans=true;

        try{
            int x=Integer.parseInt(num);
        }
        catch (Exception e)
        {
            ans=false;
        }
        return ans;
    }

    public void initialize()
    {
        name=findViewById(R.id.child_item_name);
        roll=findViewById(R.id.child_item_roll);
        parent=findViewById(R.id.child_item_parent);
        std=findViewById(R.id.child_item_class);
        contact=findViewById(R.id.child_item_contact);
        grade=findViewById(R.id.child_item_grade);
        rank=findViewById(R.id.child_item_rank);
        tdp=findViewById(R.id.child_item_tdp);
        twd=findViewById(R.id.child_item_twd);
        filldata=findViewById(R.id.filldata_button);
    }
}
