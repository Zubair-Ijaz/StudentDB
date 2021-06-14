package com.example.studentdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ShowStudent extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student);

        textView = findViewById(R.id.textView);

        StudentDB db = new StudentDB(this);
        db.open();
        textView.setText(db.getData());
        db.close();
    }
}