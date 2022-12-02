package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MasterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        Button addPass = (Button)findViewById(R.id.add_pass);

        addPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MasterActivity.this, MainActivity.class));
            }
        });

        Button showPass = (Button)findViewById(R.id.show_pass);

        showPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MasterActivity.this, ViewCourses.class);
                startActivity(i);
            }
        });

//        Button updatePass = (Button)findViewById(R.id.update_pass);
//
//        showPass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MasterActivity.this, UpdateCourseActivity.class);
//                startActivity(i);
//            }
//        });

    }
}