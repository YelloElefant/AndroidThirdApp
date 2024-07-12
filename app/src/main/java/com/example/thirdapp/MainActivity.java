package com.example.thirdapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import org.apache.http.client.HttpClient;

public class MainActivity extends AppCompatActivity {

    // private TextView mText;
    public int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // mText = (TextView) findViewById(R.id.textView);
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    public void changeText(View v) {
        count++;
        String URL = "https://www.google.com";
        final TextView mText = (TextView) findViewById(R.id.textView);

        HttpClient httpclient = new DefaultHttpClient();
        java.net.http.HttpResponse<T> response = httpclient.execute(new HttpGet(URL));
        if (response.StatusCode() == 200) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            response.getEntity().writeTo(out);
            String responseString = out.toString();
            out.close();
            // ..more logic
            mText.setText(responseString);

        } else {
            // Closes the connection.
            response.getEntity().getContent().close();
        }

    }

}