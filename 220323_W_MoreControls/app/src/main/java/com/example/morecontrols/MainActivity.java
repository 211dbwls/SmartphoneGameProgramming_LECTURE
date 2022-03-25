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

    CheckBox checkBox;
    EditText editText;
    TextView outputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox = findViewById(R.id.checkbox);
        editText = findViewById(R.id.nameEdit);
        outputTextView = findViewById(R.id.outputTextView);
    }

    public void onCheckBox(View view) {
        CheckBox cb = (CheckBox) view; 
        Log.d(TAG, "Checked: " + cb.isChecked());  // CheckBox 체크상태 확인
    }

    public void onBtnDoIt(View view) {
        Log.d(TAG, "onBtnDoIt(), Checked: " + checkBox.isChecked());  // CheckBox 체크상태 확인

        String text = editText.getText().toString();  // EditText 텍스트 가져옴
        outputTextView.setText(text);  // TextView 텍스트를 EditText 텍스트로 변경
    }
}