package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageArrayAdapter extends BaseAdapter {
    private int mItemView;
    private ArrayList<Integer> mData;
    private LayoutInflater mInflater;

    public ImageArrayAdapter(Context context, int itemView, ArrayList<Integer> data) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mItemView = itemView;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Integer getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView image;
        if (convertView == null) {
            convertView = mInflater.inflate(mItemView, parent, false);
        }
        image = (ImageView) convertView;

        image.setImageResource(mData.get(position));

        return convertView;
    }
}
