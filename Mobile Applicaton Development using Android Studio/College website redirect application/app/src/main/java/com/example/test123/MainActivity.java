package com.example.test123;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button gabout, glocation, gcontact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        gabout = findViewById(R.id.button);
        glocation = findViewById(R.id.button2);
        gcontact = findViewById(R.id.button3);

        gabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  url = "www.ajiet.edu.in/pages/aboutus/";
                Uri u = Uri.parse("https://"+url);
                Intent i = new Intent(Intent.ACTION_VIEW,u);
                startActivity(i);            }
        });

        gcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String call = "0824 286 2200";
                Uri u = Uri.parse("tel:"+call);
                Intent i = new Intent(Intent.ACTION_VIEW,u);
                startActivity(i);
            }
        });

        glocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loc = "A J Institute of Engineering and Technology";
                Uri u = Uri.parse("geo:?q="+loc);
                Intent i = new Intent(Intent.ACTION_VIEW,u);
                startActivity(i);
            }
        });
    }
}