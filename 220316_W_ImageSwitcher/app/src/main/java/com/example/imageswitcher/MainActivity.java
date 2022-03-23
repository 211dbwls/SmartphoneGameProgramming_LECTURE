package com.example.imageswitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    protected static final int[] IMG_TEXT = new int[] {
            R.mipmap.pic1,
            R.mipmap.pic2,
            R.mipmap.pic3,
            R.mipmap.pic4,
            R.mipmap.pic5
    };

    protected int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnPrev(View view) {
        Log.d(TAG, "Prev Button Press");
        setPage(page - 1);
    }

    public void onBtnNext(View view) {
        Log.d(TAG, "Next Button Press");
        setPage(page + 1);
    }

    private void setPage(int newPage) {
        page = newPage;

        ImageView iv =  findViewById(R.id.whaleImage);
        iv.setImageResource(IMG_TEXT[page-1]);
    }
}