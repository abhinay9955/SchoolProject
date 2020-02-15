package com.example.schoolproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schoolproject.R;

import java.util.HashMap;

public class AddChildActivity extends AppCompatActivity {
    private EditText childname,childparent,childcontact,childclass,childroll;
    private Button registerchildbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);
<<<<<<< HEAD


=======
>>>>>>> f7246f2ffb2c4548b9dac2aa8defc205e667cda4
        Initialise();

        getSupportActionBar().hide();

        registerchildbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate())
                {
                    String name,parent,roll,id;
                    int std,contact,grade,rank,tdp,twd,
                }
            }
        });
    }

    private boolean validate()
    {
        if(TextUtils.isEmpty(childname.getText().toString()) || TextUtils.isEmpty(childclass.getText().toString()) || TextUtils.isEmpty(childcontact.getText().toString()) ||
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
        childclass=(EditText)findViewById(R.id.addchild_class);
        childcontact=(EditText)findViewById(R.id.addchild_contact);
        childparent=(EditText)findViewById(R.id.addchild_guardian);
        childroll=(EditText)findViewById(R.id.addchild_roll);
        registerchildbutton=(Button)findViewById(R.id.addchildbutton);

    }
}
