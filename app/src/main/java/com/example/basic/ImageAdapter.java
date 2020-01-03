package com.example.basic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ImageAdapter extends  RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    public ArrayList<Cell> galleryList;
    private Context context;

    public ImageAdapter(Context context, ArrayList<Cell> galleryList) {
        this.galleryList = galleryList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ImageAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        viewHolder.img.setImageResource(galleryList.get(i).getImg());


//        viewHolder.img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//
//            }
//        });
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
            ImageView imageview = (ImageView) view.findViewById(R.id.imgDisplay);
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
