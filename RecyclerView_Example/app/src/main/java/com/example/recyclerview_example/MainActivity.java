package com.example.recyclerview_example;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview_example.models.Laptop;
import com.example.recyclerview_example.models.LaptopAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Laptop> laptopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        laptopList = new ArrayList<>();
        laptopList.add(new Laptop(
                "https://shopdunk.com/images/uploaded/kich-thuoc-macbook-air-m2/macbook-air-m1-trong-luong-kich-thuoc.jpeg",
                "Apple MacBook Air",
                "Core i5, 8GB RAM, 128GB SSD",
                60000.0,
                4.3f));
        laptopList.add(new Laptop(
                "https://product.hstatic.net/1000167396/product/2730_2649_laptopaz5425_6_0833e42e2e254bfcbefa8c3650ef2a58_master.png",
                "Dell Inspiron 7000",
                "Core i5, 8GB RAM, 1TB HDD",
                60000.0,
                4.3f));
        laptopList.add(new Laptop(
                "https://dangvu.vn/wp-content/uploads/2024/09/man-hinh-surface-laptop-6-2.webp",
                "Microsoft Surface Pro 4",
                "Core m3, 4GB RAM, 128GB SSD",
                60000.0,
                4.3f));

        LaptopAdapter adapter = new LaptopAdapter(this, laptopList, laptop -> {
            Toast.makeText(this, "Clicked: " + laptop.laptopName, Toast.LENGTH_SHORT).show();
        });

        recyclerView.setAdapter(adapter);
    }
}

