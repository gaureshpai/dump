package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
EditText e1,e2;
TextView tv;


Button btnadd,btnsub,btndiv,btnmul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
       e1=findViewById(R.id.editTextNumber);
       e2=findViewById(R.id.editTextNumber2);
               tv=findViewById(R.id.editTextText2);
              btnadd=findViewById(R.id.btnadd);
        btnmul=findViewById(R.id.btnmul);
        btndiv=findViewById(R.id.btndiv);
        btnsub=findViewById(R.id.btnsub);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a=Integer.parseInt(e1.getText().toString());
                int b=Integer.parseInt(e2.getText().toString());
                int res=a+b;
                tv.setText(""+res);
            }
        });
       btnmul.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int a=Integer.parseInt(e1.getText().toString());
               int b=Integer.parseInt(e2.getText().toString());
               int res=a*b;
               tv.setText(""+res);

           }
       });
        btndiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a=Integer.parseInt(e1.getText().toString());
                int b=Integer.parseInt(e2.getText().toString());
                int res=a/b;
                tv.setText(""+res);

            }
        });
        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a=Integer.parseInt(e1.getText().toString());
                int b=Integer.parseInt(e2.getText().toString());
                int res=a-b;
                tv.setText(""+res);

            }
        });
        }
    }