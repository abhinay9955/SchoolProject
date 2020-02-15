package com.example.schoolproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schoolproject.R;

public class AddChildActivity extends AppCompatActivity {
    private EditText childname,childparent,childcontact,childclass,childroll;
    private Button registerchildbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);
        Initialise();
        registerchildbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerchild();
            }
        });
    }

    private void registerchild()
    {
        if(TextUtils.isEmpty(childname.getText().toString()) || TextUtils.isEmpty(childclass.getText().toString()) || TextUtils.isEmpty(childcontact.getText().toString()) ||
                TextUtils.isEmpty(childparent.getText().toString()) || TextUtils.isEmpty(childroll.getText().toString()))
        {
            Toast.makeText(this, "Fill the recquired information", Toast.LENGTH_SHORT).show();
        }
        else
        {

        }
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
