package com.example.bth12;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bth12.R;

public class MainActivity extends AppCompatActivity {
    private TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo TextView
        txt1 = findViewById(R.id.selection);
        if (txt1 == null) {
            // Xử lý lỗi nếu TextView không tìm thấy (debugging)
            return;
        }

        // Khởi tạo mảng dữ liệu
        String[] arrl = {"Iphone 7", "SamSung Galaxy S7", "Nokia Lumia 730", "Sony Xperia XZ", "HTC One E9"};

        // Tạo ArrayAdapter
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrl);

        // Khởi tạo ListView
        ListView lv1 = findViewById(R.id.lv1);
        if (lv1 == null) {
            // Xử lý lỗi nếu ListView không tìm thấy (debugging)
            return;
        }
        lv1.setAdapter(adapter1);

        // Xử lý sự kiện nhấp vào mục
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    String selectedItem = arrl[position];
                    txt1.setText("Vị trí " + position + " : " + selectedItem);
                } catch (ArrayIndexOutOfBoundsException e) {
                    txt1.setText("Lỗi: Vị trí không hợp lệ");
                }
            }
        });
    }
}