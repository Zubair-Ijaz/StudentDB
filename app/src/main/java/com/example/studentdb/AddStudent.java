package com.example.studentdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddStudent extends AppCompatActivity {

    EditText sName,fName,sNumber, sEmail, sCgpa;
    Button btnAddStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        sName = findViewById(R.id.sName);
        fName = findViewById(R.id.fName);
        sNumber = findViewById(R.id.sNumber);
        sEmail = findViewById(R.id.sEmail);
        sCgpa = findViewById(R.id.sCgpa);

    }
    public void btnSubmit(View v){
        String name, fname, cell, email, cgpa;
        name = sName.getText().toString().trim();
        fname = fName.getText().toString().trim();
        cell = sNumber.getText().toString().trim();
        email = sEmail.getText().toString().trim();
        cgpa = sCgpa.getText().toString().trim();

            StudentDB db = new StudentDB(this);
            db.open();
            db.createEntry(name,fname, cell, email, cgpa);
            sName.setText("");
            fName.setText("");
            sNumber.setText("");
            sEmail.setText("");
            sCgpa.setText("");
            db.close();
    }
}