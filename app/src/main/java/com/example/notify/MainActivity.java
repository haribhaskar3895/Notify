package com.example.notify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.notify.adapter.NotificationAdapter;
import com.example.notify.db.DBHelper;
import com.example.notify.model.NotificationModel;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        DBHelper dbHelper = new DBHelper(this);
        recyclerView.setAdapter(new NotificationAdapter(dbHelper.getNotification()));
    }



}


