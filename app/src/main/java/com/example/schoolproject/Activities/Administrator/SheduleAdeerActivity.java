package com.example.schoolproject.Activities.Administrator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.schoolproject.Model.SheduleModel;
import com.example.schoolproject.R;
import com.example.schoolproject.Utils.SheduleAdapter;
import com.example.schoolproject.Utils.TutorSheduleAdapter;

import java.util.ArrayList;

public class SheduleAdeerActivity extends AppCompatActivity {

    String id,name,tutorid;
    ArrayList<SheduleModel> schedules;
    TutorSheduleAdapter scheduleAdapter;
    RecyclerView recycler;
    TextView name_tv,tutorid_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shedule_adeer);
        initiaize();
        schedules=new ArrayList<>();
        id=getIntent().getStringExtra("id");
        name=getIntent().getStringExtra("name");
        tutorid=getIntent().getStringExtra("tutorid");
        scheduleAdapter=new TutorSheduleAdapter(schedules,this);

    }

    public void initiaize()
    {

    }
}
