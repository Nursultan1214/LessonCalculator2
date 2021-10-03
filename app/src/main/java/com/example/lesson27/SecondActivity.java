package com.example.lesson27;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = findViewById(R.id.tv_polic);
        String text = getIntent().getStringExtra("key");
        textView.setText(text);

    }

    public void finishClick(View view) {
        finish();
    }
}