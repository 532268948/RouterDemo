package com.nsyw.routerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.nsyw.routerdemo.router_compiler.annotation.Route;

@Route(path = "/main/two")
public class MainTwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);
    }
}
