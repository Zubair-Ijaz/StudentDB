package com.example.studentdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnUpdate, btnShow, btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnShow = findViewById(R.id.btnShow);
        btnDelete = findViewById(R.id.btnDelete);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddStudent.class);
                startActivity(intent);
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowStudent.class);
                startActivity(intent);
            }
        });


    }
        public void btnUpdate(View v){
            StudentDB db = new StudentDB(this);
            db.open();
            db.updateEntry("3", "Abdullah", "Ahmed", "03234689546", "abc@gmail.com", "2.3");
            db.close();
        }   
        public void btnDeleteData(View v)
        {
            StudentDB db = new StudentDB(this);
            db.open();
            long number = db.deleteEntry("1");
            Toast.makeText(this, ""+number, Toast.LENGTH_SHORT).show();
            db.close();
        }
}