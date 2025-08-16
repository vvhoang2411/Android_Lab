package com.example.demosharedpreferences;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout layout;
    private Button toggleButton;
    private SharedPreferences prefs;
    private boolean isDarkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.mainLayout);
        toggleButton = findViewById(R.id.btnToggle);

        // Lấy SharedPreferences
        prefs = getSharedPreferences("AppPrefs", MODE_PRIVATE);

        // Đọc trạng thái lưu trước đó (mặc định = false)
        isDarkMode = prefs.getBoolean("dark_mode", false);

        // Áp dụng nền ban đầu
        applyTheme();

        // Sự kiện khi nhấn nút
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDarkMode = !isDarkMode; // Đảo trạng thái
                saveTheme();              // Lưu lại vào SharedPreferences
                applyTheme();             // Áp dụng thay đổi
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void applyTheme() {
        if (isDarkMode) {
            layout.setBackgroundColor(Color.BLACK);
            toggleButton.setText("Chuyển sang nền sáng");
            toggleButton.setTextColor(Color.WHITE);
        } else {
            layout.setBackgroundColor(Color.WHITE);
            toggleButton.setText("Chuyển sang nền tối");
            toggleButton.setTextColor(Color.BLACK);
        }
    }

    private void saveTheme() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("dark_mode", isDarkMode);
        editor.apply(); // Lưu không đồng bộ
    }
}
