package com.example.bth17;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase database = null;
    String DATABASE_NAME = "qlsach.db";

    // Khai báo ListView
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Gọi hàm Copy CSDL từ assets vào thư mục Databases
        processCopy();

        // Mở CSDL lên để dùng
        database = openOrCreateDatabase("qlsach.db", MODE_PRIVATE, null);

        // Tạo ListView
        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(myadapter);

        // Truy vấn CSDL và cập nhật hiển thị lên ListView
        Cursor c = database.query("tsach", null, null, null, null, null, null);
        c.moveToFirst();

        String data = "";
        while (!c.isAfterLast()) {
            data = c.getString(0) + " - " + c.getString(1) + " - " + c.getString(2);
            mylist.add(data);
            c.moveToNext();
        }

        c.close();
        myadapter.notifyDataSetChanged();
    }

    private void processCopy() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "Copying success from Assets folder", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private String getDatabasePath() {
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    public void CopyDataBaseFromAsset() {
        try {
            InputStream myInput;
            myInput = getAssets().open(DATABASE_NAME);

            // Path to the just created empty db
            String outFileName = getDatabasePath();

            // If the path doesn’t exist first, create it
            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!f.exists()) {
                f.mkdir();
            }

            // Open the empty db as the output stream
            OutputStream myOutput = new FileOutputStream(outFileName);

            // Transfer bytes from the inputfile to the outputfile
            int size = myInput.available();
            byte[] buffer = new byte[size];
            myInput.read(buffer);
            myOutput.write(buffer);

            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}