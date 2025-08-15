package com.example.bth13_customlistview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class subActivity extends AppCompatActivity {
    TextView txt_subphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        txt_subphone = findViewById(R.id.txtPhoneName);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        txt_subphone.setText(name);
    }
}