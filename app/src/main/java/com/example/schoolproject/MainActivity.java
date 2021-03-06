package com.example.schoolproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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
        FirebaseDatabase.getInstance().getReference("check").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.getValue(Integer.class)==1)
                {
                    if(user==null)
                    {
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));
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
                                else  if(dataSnapshot.getValue(String.class).equals("Administrator"))
                                {
                                    startActivity(new Intent(MainActivity.this, AdministratorActivity.class));
                                    finish();
                                }
                                else
                                {
                                    Toast.makeText(MainActivity.this, "Invalid Credential", Toast.LENGTH_SHORT).show();
                                    FirebaseAuth.getInstance().signOut();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }

                }
                else
                {
                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Your Payment is Due!!!");
                    builder.setCancelable(false);
                    builder.create().show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
