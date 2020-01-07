package com.example.basic;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

public class FullScreenImageAdapter extends PagerAdapter {

    private Activity _activity;
    //ArrayList<String> uriList = getImage();

    public ArrayList<Cell> get_galleryList() {
        return _galleryList;
    }

    public void set_galleryList(ArrayList<Cell> _galleryList) {
        this._galleryList = _galleryList;
    }

    private ArrayList<Cell> _galleryList;
    private LayoutInflater inflater;

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

    // constructor
    public FullScreenImageAdapter(Activity activity) {
        this._activity = activity;
        //this._galleryList = galleryList;
    }

    @Override
    public int getCount() {
        return images_ids.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        TouchImageView imgDisplay;
        Button btnClose;

        inflater = (LayoutInflater) _activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.layout_fullscreen_image, container,
                false);

        imgDisplay = (TouchImageView) viewLayout.findViewById(R.id.imgDisplay);
        btnClose = (Button) viewLayout.findViewById(R.id.btnClose);

        Integer image_id = _activity.getIntent().getIntExtra("picture", 0);


        ArrayList<String> uriList = getImage();
        String fullUri = uriList.get(position);
        Uri myUri = Uri.parse(fullUri);

        //Bitmap before = galleryList.get(i).getBitmap();
        //Bitmap bitmap2 = Bitmap.createScaledBitmap(before,  600 ,600, true);
        //Bitmap after = BitmapFactory.decodeByteArray(before, 0, before.getHeight());
        //viewHolder.img.setImageBitmap(bitmap2);

        Glide
                .with(_activity)
                .load(new File(myUri.getPath()))
                .centerCrop()
                .into(imgDisplay);



        TouchImageView image = (TouchImageView) container.findViewById(R.id.imgDisplay);

        //Drawable drawable = _activity.getResources().getDrawable(images_ids[position]);
        //imgDisplay.setImageDrawable(drawable);

//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
//        Bitmap bitmap = BitmapFactory.decodeFile(_galleryList.get(position), options);
//        imgDisplay.setImageBitmap(bitmap);

        // close button click event
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _activity.finish();
            }
        });

        ((ViewPager) container).addView(viewLayout);

        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);

    }

    private ArrayList<String> getImage()
    {
        ArrayList<String> result = new ArrayList<>();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = { MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.DISPLAY_NAME };

        Cursor cursor = _activity.getApplicationContext().getContentResolver(). query(uri, projection, null, null, MediaStore.MediaColumns.DATE_ADDED + " desc");
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        int columnDisplayname = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME);

        int lastIndex;
        while (cursor.moveToNext())
        {
            String absolutePathOfImage = cursor.getString(columnIndex);
            String nameOfFile = cursor.getString(columnDisplayname);
            //saveImageURI(absolutePathOfImage);
            lastIndex = absolutePathOfImage.lastIndexOf(nameOfFile);
            lastIndex = lastIndex >= 0 ? lastIndex : nameOfFile.length() - 1;

            if (!TextUtils.isEmpty(absolutePathOfImage))
            {
                result.add(absolutePathOfImage);
            }
        }

        for (String string : result)
        {
            Log.i("Gallery.java | getImage", "|" + string + "|");
        }
        return result;
    }
}