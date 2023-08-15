package com.example.madapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Home extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button button1 = findViewById(R.id.btn1);
        Button button2 = findViewById(R.id.btn2);



        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.btn1:
               Toast.makeText(this, "Gallery Page", Toast.LENGTH_SHORT).show();
               Intent c = new Intent(Home.this, Gallery.class);
               startActivity(c);
                break;

           case R.id.btn2:
               Toast.makeText(this, "Registration Page", Toast.LENGTH_SHORT).show();
               Intent a = new Intent(Home.this, Register.class);
               startActivity(a);
               break;

       }

    }
}