package com.example.basic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

public class FullScreenImageActivity extends Activity {

    private ViewPager viewPager;
    private FullScreenImageAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_full_screen_image);


        //ImageView image = (ImageView) findViewById(R.id.imgDisplay);
        viewPager = findViewById(R.id.pager);
        adapter = new FullScreenImageAdapter(this);
        //adapter.set_galleryList(getIntent().getIntExtra("list", 0));
        viewPager.setAdapter(adapter);
    };

    @Override
    protected void onStart() {
        super.onStart();
        Integer image_id = getIntent().getIntExtra("picture", 0);

        viewPager.setCurrentItem(image_id);
    };


}
