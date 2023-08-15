package com.example.madapplication1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    EditText name,inputEmail,inputPassword,confirmPassword;

    Button btnRegister;
    Button btnsave;
    TextView loginNow;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister=findViewById(R.id.btnRegister);

        inputEmail=findViewById(R.id.inputEmail);
        inputPassword=findViewById(R.id.inputPassword);
        confirmPassword=findViewById(R.id.confirmPassword);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Home.class);
                startActivity(intent);



            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerforAuth();
            }

            private void PerforAuth() {
                String inputName=name.getText().toString();
                String Email=inputEmail.getText().toString();
                String Password=inputPassword.getText().toString();
                String ConfirmPassword=confirmPassword.getText().toString();

                if(!Email.matches(emailPattern))
                {
                    inputEmail.setError("Enter Correct Email");
                } else if (inputName.isEmpty())
                {
                    name.setError("Enter Name");
                }

                else if(Password.isEmpty() || inputPassword.length()<6)
                {
                    inputPassword.setError("Enter Proper Password");
                } else if (!Password.equals(ConfirmPassword))
                {
                    confirmPassword.setError("Password Not Match Both Field");
                }else
                {
                    progressDialog.setMessage("Please Wait While Registration....");
                    progressDialog.setTitle("Registration....");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                progressDialog.dismiss();
                                sendUserToNextActivity();
                                Toast.makeText(Register.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                            }else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(Register.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


    }
    private void sendUserToNextActivity() {
        Intent intent=new Intent(Register.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}