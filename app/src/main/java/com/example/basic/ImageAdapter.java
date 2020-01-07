package com.example.basic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//import android.view.ViewHolder;

public class ImageAdapter extends  RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    public ArrayList<Cell> galleryList;
    private Context context;
    public ArrayList<String> uriList;

    public ImageAdapter(Context context, ArrayList<Cell> passedGalleryList) {
        this.galleryList = passedGalleryList;
        this.uriList = uriList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ImageAdapter.ViewHolder viewHolder, final int i) {

        Glide
                .with(context)
                .load(new File(galleryList.get(i).getUri().getPath()))
                .centerCrop()
                .into(viewHolder.img);


        viewHolder.img.setOnClickListener(new OnImageClickListener(i));

    }

    class OnImageClickListener implements View.OnClickListener {

        int _position;

        // constructor
        public OnImageClickListener(int position) {
            this._position = position;
        }

        @Override
        public void onClick(View view) {
            Intent fullScreenIntent = new Intent(view.getContext(), FullScreenImageActivity.class);
            fullScreenIntent.putExtra("picture", _position);
            view.getContext().startActivity(fullScreenIntent);
        }

    }


    public int getItemCount() {
        return galleryList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public ViewHolder(View view) {
            super(view);
            img = (ImageView) view.findViewById(R.id.img);
        }
    }


}
