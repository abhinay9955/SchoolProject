package com.example.schoolproject.Activities.Tutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schoolproject.Activities.General.AcademicActivity;
import com.example.schoolproject.Activities.General.CoursesActivity;
import com.example.schoolproject.Activities.General.LoginActivity;
import com.example.schoolproject.Activities.General.MapActivity;
import com.example.schoolproject.Activities.Parent.ParentActivity;

import com.example.schoolproject.Activities.General.AcademicActivity;




import com.example.schoolproject.Activities.Parent.PtmActivity;
import com.example.schoolproject.R;
import com.google.firebase.auth.FirebaseAuth;

public class TutorActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor);
        getSupportActionBar().setTitle("TUTOR");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.gradientback2));
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
        if(item.getItemId()==R.id.newcourse)
        {
            Intent intent=new Intent(this, CoursesActivity.class);
            startActivity(intent);

        }

        if(item.getItemId()==R.id.academic)
        {
            startActivity(new Intent(TutorActivity.this, AcademicActivity.class));


        }

        if(item.getItemId()==R.id.newlocation)
        {
            startActivity(new Intent(TutorActivity.this, MapActivity.class));
        }
        if(item.getItemId()==R.id.newcontact)
        {
            final Dialog dialog=new Dialog(TutorActivity.this);
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
            Intent intent=new Intent(TutorActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        }


        return true;
    }

    public void ChildDataclicked(View view) {
        Intent inten1 = new Intent(TutorActivity.this,UpdateChildInfoActivity.class);
        startActivity(inten1);
    }

    public void PTmeetclicked(View view) {
        Intent inten = new Intent(TutorActivity.this,PtmActivity.class);
        startActivity(inten);
    }

    public void SeeAppointmentclicked(View view) {
        Intent intent= new Intent(TutorActivity.this,TutorAppointmentActivity.class);
        startActivity(intent);
    }

    public void SendtosheduleActivity(View view) {
        Intent intent= new Intent(TutorActivity.this,TutorScheduleActiity.class);
        startActivity(intent);
    }
}
