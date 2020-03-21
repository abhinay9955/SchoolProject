package com.example.schoolproject.Activities.Parent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.schoolproject.R;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        getSupportActionBar().setTitle("Grade Analysis");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.gradientback2));
    }
}
