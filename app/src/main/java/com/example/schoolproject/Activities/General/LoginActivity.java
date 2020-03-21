package com.example.schoolproject.Activities.General;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schoolproject.Activities.Administrator.AdministratorActivity;
import com.example.schoolproject.Activities.Parent.ParentActivity;
import com.example.schoolproject.Activities.Tutor.TutorActivity;
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
    private TextView forgotpass,createuser;
    private Button loginbutton;
    private FirebaseAuth mAuth;
    private DatabaseReference rootref;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Initialise();
        getSupportActionBar().setTitle("LOG IN");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.gradientback2));
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Logging you in");
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        mAuth=FirebaseAuth.getInstance();
        rootref= FirebaseDatabase.getInstance().getReference("users");
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginuser();
            }
        });

        createuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendialogue(view);

            }
        });
    }

    private void loginuser()
    {
        progressDialog.show();
        final String  name,email,password;
        password=userpassword.getText().toString();
        email=useremail.getText().toString();
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "fill the fields first", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
        else
        {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {     Log.i( "onComplete: ","logg");
                    if(task.isSuccessful())
                    {
                        Log.i( "onComplete: ","login");

                        String userid=mAuth.getCurrentUser().getUid();
                        rootref.child(userid).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                            {
                                String type=dataSnapshot.getValue(String.class);
                                if(type.equals("Parent"))
                                {
                                    startActivity(new Intent(LoginActivity.this, ParentActivity.class));
                                    finish();
                                    
                                }
                                else if(type.equals("Tutor"))
                                {
                                     startActivity(new Intent(LoginActivity.this, TutorActivity.class));
                                     finish();
                                }
                                else
                                {
                                    startActivity(new Intent(LoginActivity.this, AdministratorActivity.class));
                                    finish();

                                }

                                progressDialog.dismiss();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(LoginActivity.this, "Erroe occured", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }

                }
            });
        }
    }

    private void Initialise()
    {
        useremail=(EditText) findViewById(R.id.loginemail);
        userpassword=(EditText) findViewById(R.id.loginpassworduser);
        forgotpass=(TextView) findViewById(R.id.loginforgotpassword);
        loginbutton=(Button) findViewById(R.id.loginbutton);
        createuser=findViewById(R.id.register);
    }



    public void opendialogue(View view)

    {

        Toast.makeText(LoginActivity.this, "Clicked ", Toast.LENGTH_SHORT).show();
        Dialog d=new Dialog(LoginActivity.this);
        d.setContentView(R.layout.forgot_password_dialogue);

        final EditText email;
        Button send;
        email=d.findViewById(R.id.email);
        send=d.findViewById(R.id.sendlink);
        send.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                if(!TextUtils.isEmpty(email.getText().toString()))
                {FirebaseAuth.getInstance().sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(LoginActivity.this, "Link send", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                        }
                    }
                });}
                else
                    Toast.makeText(LoginActivity.this, "Enter email", Toast.LENGTH_SHORT).show();
            }
        });
        d.show();


    }

    public void coursesbuttonclicked(View view) {
        Intent intent=new Intent(this, CoursesActivity.class);
        startActivity(intent);
    }

    public void websiteclicked(View view) {
        Toast.makeText(this, "working", Toast.LENGTH_SHORT).show();
    }

    public void contactclicked(View view) {
        final Dialog dialog=new Dialog(LoginActivity.this);
        dialog.setContentView(R.layout.contact_dialog);
        ImageView mail,call;
        TextView ok;
        ok=dialog.findViewById(R.id.ok);
        mail=dialog.findViewById(R.id.mail);
        call=dialog.findViewById(R.id.call);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager mng=(ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData data=ClipData.newPlainText("mail","info@newcollege.com.au");
                mng.setPrimaryClip(data);
                Toast.makeText(LoginActivity.this, "Email copied to Clipboard", Toast.LENGTH_SHORT).show();

            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String posted_by = "(02) 97466999";

                String uri = "tel:" + posted_by.trim() ;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
        Window window=dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    }

    public void locateusclicked(View view) {
        startActivity(new Intent(LoginActivity.this, MapActivity.class));
    }
}
