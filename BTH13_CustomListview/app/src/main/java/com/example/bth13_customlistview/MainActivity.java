package com.example.bth13_customlistview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<phone> mylist;
    MyArrayAdapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        mylist = new ArrayList<>();

        mylist.add(new phone("Điện thoại Iphone", R.drawable.cellphone));
        mylist.add(new phone("Điện thoại SamSung", R.drawable.cellphone));
        mylist.add(new phone("Điện thoại Nokia", R.drawable.cellphone));
        mylist.add(new phone("Điện thoại HTC", R.drawable.cellphone));
        mylist.add(new phone("Điện thoại LG", R.drawable.cellphone));
        mylist.add(new phone("Điện thoại Sky", R.drawable.cellphone));
        mylist.add(new phone("Điện thoại WP", R.drawable.cellphone));

        myadapter = new MyArrayAdapter(MainActivity.this, R.layout.layout_listview, mylist);
        listView.setAdapter(myadapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, subActivity.class);
                intent.putExtra("name", mylist.get(position).getNamephone());
                startActivity(intent);
            }
        });
    }
}