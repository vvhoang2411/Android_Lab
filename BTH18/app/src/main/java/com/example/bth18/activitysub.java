package com.example.bth18;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class activitysub extends AppCompatActivity {
    TextView txtmaso, txtbaihat, txtloibaihat, txttacgia;
    ImageButton btnthich, btnkhongthich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subactivity);

        txtmaso = (TextView) findViewById(R.id.txtmaso);
        txtbaihat = (TextView) findViewById(R.id.txttenbaihat);
        txtloibaihat = (TextView) findViewById(R.id.txtloibaihat);
        txttacgia = (TextView) findViewById(R.id.txttacgia);
        btnkhongthich = (ImageButton) findViewById(R.id.btnkhongthich);
        btnthich = (ImageButton) findViewById(R.id.btnthich);

        // Nhận Intent từ myarrayAdapter, lấy dữ liệu khởi Bundle
        Intent callerIntent1 = getIntent();
        Bundle packageCaller1 = callerIntent1.getBundleExtra("package");
        String maso = packageCaller1.getString("maso");

        // Truy vấn dữ liệu từ maso nhận được
        Cursor c = MainActivity.database.rawQuery(
                "SELECT * FROM AriangSongList WHERE MABH LIKE '" + maso + "'", null);
        c.moveToFirst();
        txtmaso.setText(maso);
        txtbaihat.setText(c.getString(2));
        txtloibaihat.setText(c.getString(3));
        txttacgia.setText(c.getString(4));
        if (c.getInt(6) == 0) {
            btnthich.setVisibility(View.INVISIBLE);
            btnkhongthich.setVisibility(View.VISIBLE);
        } else {
            btnkhongthich.setVisibility(View.INVISIBLE);
            btnthich.setVisibility(View.VISIBLE);
        }
        c.close();

        // Xử lý khi click vào nút "Thích" → cập nhật CSDL
        btnthich.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH", 0);
                MainActivity.database.update(
                        "AriangSongList", values,
                        "MABH=?", new String[] { txtmaso.getText().toString() }
                );
                btnthich.setVisibility(View.INVISIBLE);
                btnkhongthich.setVisibility(View.VISIBLE);
            }
        });

        // Xử lý khi click vào nút "Không thích"
        btnkhongthich.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH", 1);
                MainActivity.database.update(
                        "AriangSongList", values,
                        "MABH=?", new String[] { txtmaso.getText().toString() }
                );
                btnkhongthich.setVisibility(View.INVISIBLE);
                btnthich.setVisibility(View.VISIBLE);
            }
        });
    }
}