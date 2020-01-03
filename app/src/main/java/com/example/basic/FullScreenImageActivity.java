package com.example.basic;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.viewpager.widget.ViewPager;

import static android.os.SystemClock.sleep;

public class FullScreenImageActivity extends Activity {

    private ViewPager viewPager;
    private FullScreenImageAdapter adapter;

    public final Integer images_ids[] = {
            R.drawable.pic_1, R.drawable.pic_2,
            R.drawable.pic_3, R.drawable.pic_4,
            R.drawable.pic_5, R.drawable.pic_6,
            R.drawable.pic_7, R.drawable.pic_8,
            R.drawable.pic_9, R.drawable.pic_10,
            R.drawable.pic_11, R.drawable.pic_12,
            R.drawable.pic_13, R.drawable.pic_14,
            R.drawable.pic_15, R.drawable.pic_16,
            R.drawable.pic_17, R.drawable.pic_18,
            R.drawable.pic_19, R.drawable.pic_20
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_full_screen_image);


        //ImageView image = (ImageView) findViewById(R.id.imgDisplay);
        viewPager = findViewById(R.id.pager);
        adapter = new FullScreenImageAdapter(this);
        viewPager.setAdapter(adapter);
    };

    @Override
    protected void onStart() {
        super.onStart();
        Integer image_id = getIntent().getIntExtra("picture", 0);
        viewPager.setCurrentItem(image_id);
    };
}
