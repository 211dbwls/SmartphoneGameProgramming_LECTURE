package com.example.morecontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

// public class MainActivity extends AppCompatActivity implements TextWatcher {  // TextWatcher 두번째 방법
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

        // TextWatcher 첫번째 방법
        /*editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {  // 입력하기 전에 호출
                Log.v(TAG, "before");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {  // 변화가 있을 때 호출
                Log.d(TAG, "text change: " + charSequence);
                outputTextView.setText("Text Length: " + charSequence.length());
            }

            @Override
            public void afterTextChanged(Editable editable) {  // 입력 끝난 후 호출
                Log.v(TAG, "after");
            }
        });*/

        // editText.addTextChangedListener(this);  // TextWatcher 두번째 방법
        editText.addTextChangedListener(editWatcher);  // TextWatcher 세번째 방법
    }

    // TextWatcher 두번째 방법
    /*@Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {  // 입력하기 전에 호출
        Log.v(TAG, "before");
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {  // 변화가 있을 때 호출
        Log.d(TAG, "text change: " + charSequence);
        outputTextView.setText("Text Length: " + charSequence.length());
    }

    @Override
    public void afterTextChanged(Editable editable) {  // 입력 끝난 후 호출
        Log.v(TAG, "after");
    }*/

    TextWatcher editWatcher = new TextWatcher() {    // TextWatcher 세번째 방법
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {  // 입력하기 전에 호출
            Log.v(TAG, "before");
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {  // 변화가 있을 때 호출
            Log.d(TAG, "text change: " + charSequence);
            outputTextView.setText("Text Length: " + charSequence.length());
        }

        @Override
        public void afterTextChanged(Editable editable) {  // 입력 끝난 후 호출
            Log.v(TAG, "after");
        }
    };

    public void onCheckBox(View view) {
        CheckBox cb = (CheckBox) view; 
        Log.d(TAG, "Checked: " + cb.isChecked());  // CheckBox 체크상태 ㄴㅁ확인
    }

    public void onBtnDoIt(View view) {
        Log.d(TAG, "onBtnDoIt(), Checked: " + checkBox.isChecked());  // CheckBox 체크상태 확인

        String text = editText.getText().toString();  // EditText 텍스트 가져옴
        outputTextView.setText(text);  // TextView 텍스트를 EditText 텍스트로 변경
    }
}