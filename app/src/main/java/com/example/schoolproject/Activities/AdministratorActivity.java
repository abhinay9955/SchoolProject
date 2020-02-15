package com.example.schoolproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.schoolproject.R;

public class AdministratorActivity extends AppCompatActivity {


    Button addchild,pta,tutor,parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);

        addchild=findViewById(R.id.addchild);
        tutor=findViewById(R.id.tutor);
        parent=findViewById(R.id.parent);
        pta=findViewById(R.id.pta);



        pta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdministratorActivity.this,AdminstratorPTAActivity.class));
            }
        });
    }
}
