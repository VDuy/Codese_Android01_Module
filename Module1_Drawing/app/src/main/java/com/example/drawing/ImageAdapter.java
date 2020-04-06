package com.example.drawing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {

    //tra ve so luong phan tu trong list
    @Override
    public int getCount() {
        return ImageUtils.getListImage().size();
    }

    //xx
    @Override
    public Object getItem(int position) {
        return null;
    }

    //xx
    @Override
    public long getItemId(int position) {
        return 0;
    }

    //
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(parent.getContext(), R.layout.item_grid_image, null);

        ImageView ivImage = convertView.findViewById(R.id.iv_image);
        TextView tvImage = convertView.findViewById(R.id.tv_image);

        Bitmap bitmap = BitmapFactory.decodeFile(ImageUtils.getListImage().get(position).getAbsolutePath());
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float ratio = (float) width / height;
        //   ivImage.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 100, (int) (100 * height / ratio), false));
        // ivImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ivImage.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 100, (int) (100 / ratio), false));

        tvImage.setText(ImageUtils.getListImage().get(position).getName());
        return convertView;

    }
}
