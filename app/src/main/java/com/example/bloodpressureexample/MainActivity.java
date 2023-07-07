package com.example.bloodpressureexample;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button addButton;
    private Button listButton;
    private EditText sysEditText;
    private EditText dysEditText;
    private EditText pulseEditText;

    private ResultTextWatcher resultTextWatcher = new ResultTextWatcher() {
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            int sysTextLength = sysEditText.getText().toString().trim().length();
            int dysTextLength = dysEditText.getText().toString().trim().length();
            int pulseTextLength = pulseEditText.getText().toString().trim().length();

            if (sysTextLength == 0 || dysTextLength == 0 || pulseTextLength == 0) {
                addButton.setEnabled(false);
            } else {
                addButton.setEnabled(true);
            }
        }
    };

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            initUI();
        }

        private void initUI() {
            addButton = findViewById(R.id.activity_main_add_button);
            listButton = findViewById(R.id.activity_main_list_button);
            sysEditText = findViewById(R.id.activity_main_sys_edit_text);
            dysEditText = findViewById(R.id.activity_main_dys_edit_text);
            pulseEditText = findViewById(R.id.activity_main_pulse_edit_text);
            sysEditText.addTextChangedListener(resultTextWatcher);
            dysEditText.addTextChangedListener(resultTextWatcher);
            pulseEditText.addTextChangedListener(resultTextWatcher);
            addButton.setEnabled(false);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addResult();
                }
            });

            listButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ListActivity.class);
                    context.startActivity(intent);
                }
            });
        }

        private void addResult() {
            String sys = sysEditText.getText().toString();
            String dys = dysEditText.getText().toString();
            String pulse = pulseEditText.getText().toString();

            if (!TextUtils.isEmpty(sys) && !TextUtils.isEmpty(dys) && !TextUtils.isEmpty(pulse)) {
                int sysValue = Integer.parseInt(sys);
                int dysValue = Integer.parseInt(dys);
                int pulseValue = Integer.parseInt(pulse);
                DatabaseController.getInstance().createResult(sysValue, dysValue, pulseValue);
                clearText();
                Toast.makeText(getApplicationContext(), "Added new result", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Incorrect values", Toast.LENGTH_SHORT).show();
            }
        }

    private void clearText() {
        sysEditText.getText().clear();
        dysEditText.getText().clear();
        pulseEditText.getText().clear();
    }

}
