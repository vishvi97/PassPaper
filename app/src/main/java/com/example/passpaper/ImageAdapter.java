package com.example.passpaper;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

class ImageAdapter  extends BaseAdapter {

    Context context;
    ArrayList<Bitmap> images;

    public ImageAdapter(Context context, ArrayList<Bitmap> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = LayoutInflater.from(context).inflate( R.layout.image_row , null );
        ImageView imageView = view1.findViewById(R.id.photo);

        imageView.setImageBitmap( images.get(i));
        return view1;
    }
}
