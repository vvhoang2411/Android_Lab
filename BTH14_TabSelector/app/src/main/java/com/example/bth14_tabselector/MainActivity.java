package com.example.bth14_tabselector;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends Activity {
    EditText edta, edtb;
    Button btncong;
    ListView lv1;
    ArrayList<String> list;
    ArrayAdapter<String> myarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        addEvent();
    }

    private void addEvent() {
        // TODO Auto-generated method stub
        btncong.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                XuLyCong();
            }

            private void XuLyCong() {
                // TODO Auto-generated method stub
                int a = Integer.parseInt(edta.getText().toString());
                int b = Integer.parseInt(edtb.getText().toString());
                String c = a + " + " + b + " = " + (a + b);
                list.add(c);
                myarray.notifyDataSetChanged();
                edta.setText("");
                edtb.setText("");
            }
        });
    }

    private void addControl() {
        // TODO Auto-generated method stub
        TabHost tab = (TabHost) findViewById(android.R.id.tabhost);
        tab.setup();

        TabHost.TabSpec tab1 = tab.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("Tính toán", getResources().getDrawable(R.drawable.cong));
        tab.addTab(tab1);

        TabHost.TabSpec tab2 = tab.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("Lịch sử", getResources().getDrawable(R.drawable.lichsu));
        tab.addTab(tab2);

        edta = (EditText) findViewById(R.id.edtA);
        edtb = (EditText) findViewById(R.id.edtB);
        btncong = (Button) findViewById(R.id.btnTinh);
        lv1 = (ListView) findViewById(R.id.listView);

        list = new ArrayList<String>();
        myarray = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                list
        );
        lv1.setAdapter(myarray);
    }
}