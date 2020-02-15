package com.example.schoolproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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

<<<<<<< HEAD

=======
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
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);

        }


        return true;
    }
>>>>>>> 2a989ce87a99f7ddd9495f3419b22c886f81a038
}
