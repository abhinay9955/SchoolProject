package com.example.schoolproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.schoolproject.Activities.Administrator.AdministratorActivity;
import com.example.schoolproject.Activities.General.LoginActivity;
import com.example.schoolproject.Activities.Parent.ParentActivity;
import com.example.schoolproject.Activities.Tutor.TutorActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        user= FirebaseAuth.getInstance().getCurrentUser();
        if(user==null)
        {
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }
        else
        {
            FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.getValue(String.class).equals("Parent"))
                    {
                        startActivity(new Intent(MainActivity.this, ParentActivity.class));
                        finish();
                    }
                    else if(dataSnapshot.getValue(String.class).equals("Tutor"))
                    {
                        startActivity(new Intent(MainActivity.this, TutorActivity.class));
                        finish();
                    }
                    else
                    {
                        startActivity(new Intent(MainActivity.this, AdministratorActivity.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }


}
