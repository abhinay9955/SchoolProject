package com.example.schoolproject.Activities.Administrator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.schoolproject.Model.StudentModel;
import com.example.schoolproject.R;
import com.google.firebase.database.FirebaseDatabase;

public class AddChildActivity extends AppCompatActivity {
    private EditText childname,childparent,childcontact,childroll;
    private Button registerchildbutton;
    Spinner childclass;
    private String[] classes={"Class 1","Class 2","Class 3","Class 4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);
        getSupportActionBar().setTitle("ADD CHILD");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.gradientback2));



        Initialise();
         childclass.setAdapter(new ArrayAdapter<String>(AddChildActivity.this,android.R.layout.simple_spinner_dropdown_item,classes));
        getSupportActionBar().hide();

        registerchildbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate())
                {


                    String name,parent,roll,id,contact,std;
                    int grade,rank,tdp,twd;

                    name=childname.getText().toString();
                    parent=childparent.getText().toString();
                    roll=childroll.getText().toString();
                    contact=childcontact.getText().toString();
                    rank=-1;
                    grade=-1;
                    tdp=-1;
                    twd=-1;
                    std=classes[childclass.getSelectedItemPosition()];
                    id= FirebaseDatabase.getInstance().getReference("students").child(std).push().getKey();

                    StudentModel sm=new StudentModel(name,id,roll,contact,grade,rank,tdp,twd,std,parent);
                    FirebaseDatabase.getInstance().getReference("students").child(std).child(id).setValue(sm);
                    finish();




                }
            }
        });
    }

    private boolean validate()
    {
        if(TextUtils.isEmpty(childname.getText().toString())  || TextUtils.isEmpty(childcontact.getText().toString()) ||
                TextUtils.isEmpty(childparent.getText().toString()) || TextUtils.isEmpty(childroll.getText().toString()))
        {
            Toast.makeText(AddChildActivity.this, "Fill the recquired information", Toast.LENGTH_SHORT).show();
            return false;
        }
       return true;
    }

    private void Initialise()
    {
        childname=(EditText)findViewById(R.id.addchild_name);
        childclass=(Spinner)findViewById(R.id.addchild_class);
        childcontact=(EditText)findViewById(R.id.addchild_contact);
        childparent=(EditText)findViewById(R.id.addchild_guardian);
        childroll=(EditText)findViewById(R.id.addchild_roll);
        registerchildbutton=(Button)findViewById(R.id.addchildbutton);

    }
}
