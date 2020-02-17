package com.example.schoolproject.Activities.Administrator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schoolproject.Activities.General.CoursesActivity;
import com.example.schoolproject.Activities.General.LoginActivity;
import com.example.schoolproject.Activities.General.MapActivity;
import com.example.schoolproject.Activities.Parent.ParentActivity;
import com.example.schoolproject.Activities.Tutor.TutorActivity;
import com.example.schoolproject.R;
import com.google.firebase.auth.FirebaseAuth;

public class AdministratorActivity extends AppCompatActivity {


    Button addchild,pta,tutor,parent;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);

        addchild=findViewById(R.id.addchild);
        tutor=findViewById(R.id.tutor);
        pta=findViewById(R.id.pta);
        mAuth=FirebaseAuth.getInstance();

        addchild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdministratorActivity.this, AddChildActivity.class));
            }
        });

        pta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdministratorActivity.this, AdminstratorPTAActivity.class));
            }
        });

        tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 startActivity(new Intent(AdministratorActivity.this,SheduleUpdateActivity.class));
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
            startActivity(new Intent(AdministratorActivity.this, MapActivity.class));
        }
        if(item.getItemId()==R.id.newcontact)
        {
            final Dialog dialog=new Dialog(AdministratorActivity.this);
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
        if(item.getItemId()==R.id.newwebsite)
        {
            Toast.makeText(this, "working", Toast.LENGTH_SHORT).show();

        }
        if(item.getItemId()==R.id.newsign_out)
        {
            mAuth.signOut();
            Intent intent=new Intent(AdministratorActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        }


        return true;

    }


}
