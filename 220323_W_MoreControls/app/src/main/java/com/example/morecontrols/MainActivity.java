package com.example.morecontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCheckBox(View view) {
        CheckBox cb = (CheckBox) view; 
        Log.d(TAG, "Checked: " + cb.isChecked());
    }

    public void onBtnDoIt(View view) {
        CheckBox cb = findViewById(R.id.checkbox);
        Log.d(TAG, "onBtnDoIt(), Checked: " + cb.isChecked());

        EditText et = findViewById(R.id.nameEdit);
        String text = et.getText().toString();

        TextView tv = findViewById(R.id.outputTextView);
        tv.setText(text);
    }
}