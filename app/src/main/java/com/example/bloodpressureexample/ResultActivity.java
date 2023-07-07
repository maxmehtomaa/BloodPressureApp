package com.example.bloodpressureexample;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    private TextView sysTextView;
    private TextView dysTextView;
    private TextView pulseTextView;
    private TextView dateTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        sysTextView = findViewById(R.id.activity_result_sys_text_view);
        dysTextView = findViewById(R.id.activity_result_dys_text_view);
        pulseTextView =findViewById(R.id.activity_result_pulse_text_view);
        dateTextView = findViewById(R.id.activity_result_date_text_view);

        Intent intent = getIntent();

        String sys = intent.getStringExtra("sys");
        String dys = intent.getStringExtra("dys");
        String pulse = intent.getStringExtra("pulse");
        String date = intent.getStringExtra("date");

        setSys(sys);
        setDys(dys);
        setPulse(pulse);
        setDate(date);
    }


    private void setSys(String sys) {
        sysTextView.setText(sys);
    }

    private void setDys(String dys) {
        dysTextView.setText(dys);
    }

    private void setPulse(String pulse) {
        pulseTextView.setText(pulse);
    }

    private void setDate(String date) {
        dateTextView.setText(date);
    }
}
