package com.example.bth15;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edtmalop, edttenlop, edtsiso;
    Button btninsert, btndelete, btnupdate, btnquery;

    // Khai báo ListView
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;

    SQLiteDatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtmalop = findViewById(R.id.edtMaLop);
        edttenlop = findViewById(R.id.edtTenLop);
        edtsiso = findViewById(R.id.edtSiSo);

        btninsert = findViewById(R.id.btnInsert);
        btndelete = findViewById(R.id.btnDelete);
        btnupdate = findViewById(R.id.btnUpdate);
        btnquery = findViewById(R.id.btnQuery);

        // Tạo ListView
        lv = findViewById(R.id.listViewLop);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(myadapter);

        // Tạo và mở cơ sở dữ liệu SQLite
        mydatabase = openOrCreateDatabase("qlsinhvien.db", MODE_PRIVATE, null);

        // Tạo bảng tblLop nếu chưa tồn tại
        try {
            String sql = "CREATE TABLE tblLop(malop TEXT primary key, tenlop TEXT, siso INTEGER)";
            mydatabase.execSQL(sql);
        } catch (Exception e) {
            Log.e("Error", "Table đã tồn tại");
        }

        // Xử lý nút INSERT
        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String malop = edtmalop.getText().toString();
                String tenlop = edttenlop.getText().toString();
                int siso = Integer.parseInt(edtsiso.getText().toString());

                ContentValues myvalue = new ContentValues();
                myvalue.put("malop", malop);
                myvalue.put("tenlop", tenlop);
                myvalue.put("siso", siso);

                String msg = "";
                if (mydatabase.insert("tblLop", null, myvalue) == -1) {
                    msg = "Fail to Insert Record!";
                } else {
                    msg = "Insert record Successfully";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý nút DELETE
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String malop = edtmalop.getText().toString();
                int n = mydatabase.delete("tblLop", "malop = ?", new String[]{malop});
                String msg = "";
                if (n == 0) {
                    msg = "No record to Delete";
                } else {
                    msg = n + " record is deleted";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý nút UPDATE
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int siso = Integer.parseInt(edtsiso.getText().toString());
                String malop = edtmalop.getText().toString();

                ContentValues myvalue = new ContentValues();
                myvalue.put("siso", siso);

                int n = mydatabase.update("tblLop", myvalue, "malop = ?", new String[]{malop});
                String msg = "";
                if (n == 0) {
                    msg = "No record to Update";
                } else {
                    msg = n + " record is updated";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý nút QUERY
        btnquery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mylist.clear();
                Cursor c = mydatabase.query("tblLop", null, null, null, null, null, null);
                c.moveToNext();
                String data = "";
                while (!c.isAfterLast()) {
                    data = c.getString(0) + " - " + c.getString(1) + " - " + c.getInt(2);
                    mylist.add(data);
                    c.moveToNext();
                }
                c.close();
                myadapter.notifyDataSetChanged();
            }
        });
    }
}