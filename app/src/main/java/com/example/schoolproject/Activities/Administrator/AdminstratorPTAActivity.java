package com.example.schoolproject.Activities.Administrator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.schoolproject.R;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AdminstratorPTAActivity extends AppCompatActivity {

    TextView time_tv;
    Button time_bt,submit;
    EditText mclass,mroom;
    Spinner duration;
    String[] durations={"1/2 Hour","1 Hour","1 and 1/2 Hour", "2 Hour"};
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator_pta);
        getSupportActionBar().setTitle("ADMINISTRATE PT-M");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.gradientback2));

        init();

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,durations);
        duration.setAdapter(adapter);
        duration.setSelection(0);

         time_bt.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 showDateTime();
             }
         });

         submit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(validate())
                 {
                     progressDialog.show();
                     HashMap<String,Object> data=new HashMap<>();
                     data.put("time",time_tv.getText().toString());
                     data.put("std",mclass.getText().toString());
                     data.put("room",mroom.getText().toString());
                     data.put("duration",duration.getSelectedItem().toString());
                     FirebaseDatabase.getInstance().getReference("pta").push().updateChildren(data);
                     progressDialog.dismiss();
                     finish();

                 }
                 else
                 {
                     Toast.makeText(AdminstratorPTAActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                 }
             }
         });

    }



    public void init()
    {
        time_tv=findViewById(R.id.time_tv);
        time_bt=findViewById(R.id.time_bt);
        submit=findViewById(R.id.submit);
        mclass=findViewById(R.id.mclass);
        mroom=findViewById(R.id.room);
        duration=findViewById(R.id.duration);
        progressDialog=new ProgressDialog(AdminstratorPTAActivity.this);
        progressDialog.setMessage("Uploading...");
        progressDialog.setCancelable(false);
    }

    public boolean validate()
    {
        String cls=mclass.getText().toString().trim();
        String rm=mroom.getText().toString().trim();
        String time= time_tv.getText().toString().trim();

        if(cls.equals("") || rm.equals("") || time.equals(""))
            return false;
        return true;
    }



    public void showDateTime()
    {
        final Calendar currentDate = Calendar.getInstance();
        final Calendar date = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(AdminstratorPTAActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                date.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(AdminstratorPTAActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        date.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        date.set(Calendar.MINUTE, minute);
                        // Log.v(TAG, "The choosen one " + date.getTime());
                        // Toast.makeText(getContext(),"The choosen one " + date.getTime(),Toast.LENGTH_SHORT).show();
                        time_tv.setText(new SimpleDateFormat("dd-MMM-yyyy hh:mm a").format(date.getTime()));
                    }
                },currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();

            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE));
        datePickerDialog.getDatePicker().setMinDate(currentDate.getTimeInMillis());
        datePickerDialog.show();
    }
}
