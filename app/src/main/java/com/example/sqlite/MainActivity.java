package com.example.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText websiteNameEdt, userNameEdt, passWordEdt;
    private Button addCourseBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing all our variables.
        websiteNameEdt = findViewById(R.id.websiteName);
        userNameEdt = findViewById(R.id.userName);
        passWordEdt = findViewById(R.id.password);

        addCourseBtn = findViewById(R.id.idBtnAddCourse);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(MainActivity.this);

        // below line is to add on click listener for our add course button.
        addCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String website_Name = websiteNameEdt.getText().toString();
                String user_Name = userNameEdt.getText().toString();
                String pass_Word = passWordEdt.getText().toString();

                // validating if the text fields are empty or not.
                if (website_Name.isEmpty() && user_Name.isEmpty() && pass_Word.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                dbHandler.addNewPass(website_Name, user_Name, pass_Word);

                // after adding the data we are displaying a toast message.
                Toast.makeText(MainActivity.this, "Course has been added.", Toast.LENGTH_SHORT).show();
                websiteNameEdt.setText("");
                userNameEdt.setText("");
                passWordEdt.setText("");
            }
        });


    }
}
