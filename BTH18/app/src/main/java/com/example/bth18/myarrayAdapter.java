package com.example.bth18;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class myarrayAdapter extends ArrayAdapter<Item> {
    Activity context = null;
    ArrayList<Item> myArray = null;
    int LayoutId;

    public myarrayAdapter(Activity context, int LayoutId, ArrayList<Item> arr) {
        super(context, LayoutId, arr);
        this.context = context;
        this.LayoutId = LayoutId;
        this.myArray = arr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(LayoutId, null);
        final Item myItem = myArray.get(position);

        final TextView tieude = (TextView) convertView.findViewById(R.id.bttitlede);
        tieude.setText(myItem.getTieude());

        final TextView maso = (TextView) convertView.findViewById(R.id.txtmaso);
        maso.setText(myItem.getMaso());

        final ImageView btnlike = (ImageView) convertView.findViewById(R.id.btnlike);
        final ImageView btnunlike = (ImageView) convertView.findViewById(R.id.btnunlike);

        int thich = myItem.getThich();

        // Xử lý hiển thị cho ImageButton
        if (thich == 0) {
            btnlike.setVisibility(View.INVISIBLE);  // ẩn btnlike
            btnunlike.setVisibility(View.VISIBLE);  // hiện btnunlike
        } else {
            btnunlike.setVisibility(View.INVISIBLE);  // ẩn btnunlike
            btnlike.setVisibility(View.VISIBLE);     // hiện btnlike
        }

        // Xử lý sự kiện click vào ImageButton btnlike
        btnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH", 0);
                MainActivity.database.update("ArirangSongList", values, "MABH=?", new String[]{maso.getText().toString()});
                btnlike.setVisibility(View.INVISIBLE);
                btnunlike.setVisibility(View.VISIBLE);
            }
        });

        // Xử lý sự kiện click vào ImageButton btnunlike
        btnunlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH", 1);
                MainActivity.database.update("ArirangSongList", values, "MABH=?", new String[]{maso.getText().toString()});
                btnunlike.setVisibility(View.INVISIBLE);
                btnlike.setVisibility(View.VISIBLE);
            }
        });

        // Xử lý sự kiện khi Click vào mỗi dòng để mở bài hát từ ListView
        // Chuyển TextView về màu đỏ
        // Khởi tạo Intent, Bundle và truyền qua subactivity

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maso.setTextColor(Color.RED);
                tieude.setTextColor(Color.RED);

                Intent intent = new Intent(context, activitysub.class);
                Bundle bundle = new Bundle();
                bundle.putString("maso", maso.getText().toString());
                intent.putExtra("package", bundle);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}