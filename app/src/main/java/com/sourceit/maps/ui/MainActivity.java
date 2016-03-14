package com.sourceit.maps.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.sourceit.maps.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(), "Сервис запущен", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, MyService.class);
        startService(intent);
    }
}
