package com.nsyw.routerdemo.module_chat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.nsyw.routerdemo.router_compiler.annotation.Route;

@Route(path = "/chat/one")
public class ChatOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_one);
    }
}
