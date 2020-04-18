package com.example.gossettsamantha.test.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gossettsamantha.test.R;

public class Details extends AppCompatActivity {
    TextView textTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        String title = i.getStringExtra("title");

        textTitle = findViewById(R.id.detailTitle);
        textTitle.setText(title);

    }
}
