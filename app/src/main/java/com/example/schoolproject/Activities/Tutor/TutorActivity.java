package com.example.schoolproject.Activities.Tutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.schoolproject.Activities.General.LoginActivity;
import com.example.schoolproject.Activities.Parent.PtmActivity;
import com.example.schoolproject.R;
import com.google.firebase.auth.FirebaseAuth;

public class TutorActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);
        mAuth=FirebaseAuth.getInstance();
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
        if(item.getItemId()==R.id.signouttutor)
        {
            mAuth.signOut();
            Intent intent=new Intent(TutorActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        }


        return true;
    }

    public void sendptactivity(View view)
    {
        Intent intent=new Intent(TutorActivity.this, PtmActivity.class);
        startActivity(intent);
    }

}
