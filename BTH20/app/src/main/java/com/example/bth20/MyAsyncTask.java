package com.example.bth20;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
    // Khai báo Activity để lưu trữ context của MainActivity
    Activity contextCha;

    // Constructor này được truyền vào từ MainActivity
    public MyAsyncTask(Activity ctx) {
        contextCha = ctx;
    }

    // Hàm này sẽ được thực hiện đầu tiên
    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        Toast.makeText(contextCha, "onPreExecute!", Toast.LENGTH_LONG).show();
    }

    // Sau đó tới hàm doInBackground
    // Tuyệt đối không được cập nhật giao diện trong hàm này
    @Override
    protected Void doInBackground(Void... arg0) {
        for (int i = 0; i < 100; i++) {
            // Nghỉ 100 millisecond rồi tiến hành update UI
            SystemClock.sleep(100);

            // Khi gọi hàm này thì onProgressUpdate sẽ thực thi
            publishProgress(i);
        }
        return null;
    }

    /**
     * Hàm cập nhật giao diện trong hàm này
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        // TODO Auto-generated method stub
        super.onProgressUpdate(values);

        // Thông qua contextCha để lấy được control trong MainActivity
        ProgressBar pacha = (ProgressBar) contextCha.findViewById(R.id.progressBar);

        // Vì publishProgress chỉ truyền 1 đối số nên mảng values chỉ có 1 phần tử
        int giatri = values[0];

        // Thay giá trị của ProgressBar lên
        pacha.setProgress(giatri);

        // Đồng thời hiển thị giá trị i ra TextView
        TextView txtmsg = (TextView) contextCha.findViewById(R.id.textView1);
        txtmsg.setText(giatri + "%");
    }

    /**
     * Sau khi tiến trình thực hiện xong thì hàm này xảy ra
     */
    @Override
    protected void onPostExecute(Void result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        Toast.makeText(contextCha, "Update xong rồi đó!", Toast.LENGTH_LONG).show();
    }
}