package com.example.schoolproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.schoolproject.Activities.RegisterActivity;
import com.example.schoolproject.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //hlekjlkfj.snvlkjlejgjfgekgerkjge
    }

    public void sendregister(View view)
    {
        Intent intent=new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
