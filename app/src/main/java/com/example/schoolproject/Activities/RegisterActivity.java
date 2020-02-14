package com.example.schoolproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.schoolproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private EditText username,useremail,userpassword,confirmpassword;
    private Button RegisterButton;
    private FirebaseAuth mAuth;
    private DatabaseReference rootref;
    private RadioButton checkuser;
    private RadioGroup myradiogroup;
    private String user ="Tutor";
    private ProgressDialog loading;
    private String[] users={"Tutor","Parent"};


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Initialise();
        loading=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        rootref=FirebaseDatabase.getInstance().getReference();
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    private void registerUser()
    {
        final String  name,email,password1,password2;
        name=username.getText().toString();
        email=useremail.getText().toString();
        password1=userpassword.getText().toString();
        password2=confirmpassword.getText().toString();
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password1) || TextUtils.isEmpty(password2) )
        {
            Toast.makeText(this, "Fill the fields properly", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(!password1.equals(password2))
            {
                Toast.makeText(this, "password confirmation failed", Toast.LENGTH_SHORT).show();
            }
            else
                {
                    loading.setTitle("task process");
                    loading.setMessage("ho rha hai bhai");
                    loading.setCanceledOnTouchOutside(true);
                    loading.show();

                    mAuth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())

                            {
                                loading.dismiss();
                                String userid=mAuth.getCurrentUser().getUid();
                                rootref.child("users").child(userid).setValue(user);

                                if(user.equals("Parent"))
                                {
                                    HashMap<String,Object> data=new HashMap<>();
                                    data.put("name",username.getText().toString().trim());
                                    data.put("email",email);

                                    rootref.child("Parent").child(userid).updateChildren(data);
                                    startActivity(new Intent(RegisterActivity.this,ParentActivity.class));
                                    finish();
                                }
                                else if(user.equals("Tutor"))
                                {
                                    HashMap<String,Object> data=new HashMap<>();
                                    data.put("name",username.getText().toString().trim());
                                    data.put("email",email);

                                    rootref.child("Tutor").child(userid).updateChildren(data);

                                    startActivity(new Intent(RegisterActivity.this,TutorActivity.class));
                                    finish();
                                }

                            }
                            else
                                {
                                    loading.dismiss();
                                    Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                            }
                        }
                    });



                }
        }


    }

    private void Initialise()
    {
        username= (EditText)findViewById(R.id.registerusername);
        userpassword=(EditText) findViewById(R.id.registeruserpassword);
        useremail=(EditText) findViewById(R.id.registeruseremail);
        confirmpassword=(EditText) findViewById(R.id.registerconfirmpassword);
        myradiogroup=(RadioGroup)findViewById(R.id.regiseterradiogroup);
        RegisterButton=(Button) findViewById(R.id.registerbutton);


    }

    public void selectuser(View view)
    {
        int checkid=myradiogroup.getCheckedRadioButtonId();
        Log.i("selectuser: ",String.valueOf(checkid));
        checkuser=(RadioButton) findViewById(checkid);
        if(checkuser.getText().toString().equals("Parent"))
        {
            user ="Parent";

        }
        else
        {
            if(checkuser.getText().toString().equals("Tutor"))
            {
                user ="Tutor";

            }
        }

    }
}
