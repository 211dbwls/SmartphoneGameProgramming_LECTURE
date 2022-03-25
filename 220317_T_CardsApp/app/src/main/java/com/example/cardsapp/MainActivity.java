package com.example.cardsapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int[] BUTTON_IDS = new int[] {  // 버튼 id
            R.id.card_00, R.id.card_01, R.id.card_02,R.id.card_03,
            R.id.card_10, R.id.card_11, R.id.card_12,R.id.card_13,
            R.id.card_20, R.id.card_21, R.id.card_22,R.id.card_23,
            R.id.card_30, R.id.card_31, R.id.card_32,R.id.card_33
    };

    private int[] resIds = new int[]{  // 카드 이미지 리소스
            R.mipmap.card_as, R.mipmap.card_2c, R.mipmap.card_3d, R.mipmap.card_4h,
            R.mipmap.card_5s, R.mipmap.card_jc, R.mipmap.card_qh, R.mipmap.card_kd,
            R.mipmap.card_as, R.mipmap.card_2c, R.mipmap.card_3d, R.mipmap.card_4h,
            R.mipmap.card_5s, R.mipmap.card_jc, R.mipmap.card_qh, R.mipmap.card_kd
    };

    private ImageButton previousImageButton;  // 이전에 눌린 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startGame();  // 게임 시작시 기본 세팅하는 함수
    }

    private void startGame() {  // 기본 세팅하는 함수
        for(int i = 0; i < BUTTON_IDS.length; ++i) {  // 각 카드에 이미지 부여
            ImageButton btn = findViewById(BUTTON_IDS[i]);  // i번째 ImageButton 받아옴
            int resId = resIds[i];  // 이미지 리소스 받아옴
            btn.setTag(resId);  // ImageButton에 이미지 리소스를 태그로 설정
        }
    }

    public void onBtnRestart(View view) {
        Log.d(TAG, "Restart");
        askRetry();
    }

    private void askRetry() {  // 재시작 알림창
        new AlertDialog.Builder(this)
                .setTitle("Restart?")
                .setMessage("Do you really want to restart tha game?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {  // Yes 클릭할 경우
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startGame();  // 재시작
                    }
                })
                .setNegativeButton("No", null)  // No 클릭할 경우
                .create()
                .show();
    }

    public void onBtnCard(View view) {
        if(!(view instanceof ImageButton)) {  // 눌린 버튼이 ImageButton이 아닌 경우
            Log.d(TAG, "Not an ImageButton: " + view);
            return;
        }
        ImageButton imageButton = (ImageButton) view;  // 클릭한 ImageButton 가져옴

        int cardIndex = findButtonIndex(imageButton.getId());  // 카드 인덱스 구함
        Log.d(TAG, "Card: " + cardIndex);

        if(imageButton == previousImageButton) {  // 이전에 누른 버튼을 또 누른 경우
            Log.d(TAG, "Same Image Button");
            return;
        }

        int resId = (Integer) imageButton.getTag();  // getTag()로 태그로 설정한 이미지 리소스 받아옴

        if(previousImageButton != null) {  // 이전에 누른 버튼이 있을 경우
            int previousResourceId = (Integer) previousImageButton.getTag();  // getTag()로 태그로 설정한 이미지 리소스 받아옴

            if(resId == previousResourceId) {  // 같은 카드를 클릭한 경우
                imageButton.setVisibility(View.INVISIBLE);  // 현재 누른 카드 안보이도록 설정
                previousImageButton.setVisibility(View.INVISIBLE);  // 이전에 누른 카드 안보이도록 설정

                previousImageButton = null;  // 이전에 누른 카드 null로 설정
            }
            else {  // 다른 카드를 클릭한 경우
                imageButton.setImageResource(resId);  // 현재 누른 카드 이미지 변경
                previousImageButton.setImageResource(R.mipmap.card_blue_back);  // 이전에 누른 카드 이미지 변경

                previousImageButton = imageButton;  // 이전에 눌린 버튼 저장
            }
        }
        else {  // 이전에 누른 버튼이 null인 경우
            imageButton.setImageResource(resId);  // 현재 누른 카드 이미지 변경

            previousImageButton = imageButton;  // 이전에 눌린 버튼 저장
        }
    }

    private int findButtonIndex(int id) {  // 카드 인덱스 구하는 함수
        for(int i = 0; i < BUTTON_IDS.length; ++i) {
            if(id == BUTTON_IDS[i]) {
                return i;
            }
        }
        return -1;
    }
}