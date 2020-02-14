package com.example.schoolproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schoolproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private EditText useremail,userpassword;
    private TextView forgotpass;
    private Button loginbutton;
    private FirebaseAuth mAuth;
    private DatabaseReference rootref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Initialise();
        mAuth=FirebaseAuth.getInstance();
        rootref= FirebaseDatabase.getInstance().getReference();
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginuser();
            }
        });
    }

    private void loginuser()
    {
        final String  name,email,password;
        password=useremail.getText().toString();
        email=userpassword.getText().toString();
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "fill the fields first", Toast.LENGTH_SHORT).show();
        }
        else
        {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if(task.isSuccessful())
                    {
                        String userid=mAuth.getCurrentUser().getUid();
                        rootref.child(userid).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                            {
                                String type=dataSnapshot.getValue(String.class);
                                if(type.equals("Parent"))
                                {
                                    SendusertoParentActivity();

                                    
                                }
                                else if(type.equals("Tutor"))
                                {
                                    SendusertoTutorActivity();

                                }
                                else
                                {
                                    SendusertoAdministratorActivity();

                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(LoginActivity.this, "Erroe occured", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }

                }
            });
        }
    }

    private void SendusertoAdministratorActivity() {
    }

    private void SendusertoTutorActivity() {
    }

    private void SendusertoParentActivity() {
    }

    private void Initialise()
    {
        useremail=(EditText) findViewById(R.id.loginemail);
        userpassword=(EditText) findViewById(R.id.loginpassworduser);
        forgotpass=(TextView) findViewById(R.id.loginforgotpassword);
        loginbutton=(Button) findViewById(R.id.loginbutton);
    }
}
