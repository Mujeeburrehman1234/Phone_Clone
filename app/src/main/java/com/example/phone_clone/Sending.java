package com.example.phone_clone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.phone_clone.adapter.FileReciveAdapter;

import java.util.ArrayList;

import com.example.phone_clone.Model.FileToSendPath;

public class Sending extends AppCompatActivity {
RecyclerView recyclerView;
ArrayList<FileToSendPath> list;
FileToSendPath fileToSendPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending);
        recyclerView=findViewById(R.id.recyclerview1);
        list=new ArrayList<>();
        fileToSendPath=new FileToSendPath();
        fileToSendPath.setName("Youtube");
        list.add(fileToSendPath);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new FileReciveAdapter(list));


    }
}