package com.nsyw.routerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.nsyw.routerdemo.router_compiler.annotation.Route;

@Route(path = "/main/one")
public class MainOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_one);
    }
}
