package com.example.phone_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Recieve_network extends AppCompatActivity {
Button btnconect,btncancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieve_network);
        btnconect=findViewById(R.id.btnConnect);
        btncancel=findViewById(R.id.btnCancel);
        btnconect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Recieve_network.this,MainActivity.class));
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Recieve_network.this,Home_activity.class));
            }
        });
    }
}