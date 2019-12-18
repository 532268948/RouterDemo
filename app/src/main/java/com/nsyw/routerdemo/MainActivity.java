package com.nsyw.routerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nsyw.routerdemo.router_api.Router;

public class MainActivity extends AppCompatActivity {

    private TextView mOneTv, mTwoTv, mThreeTv, mFourTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOneTv = findViewById(R.id.tv_one);
        mTwoTv = findViewById(R.id.tv_two);
        mThreeTv = findViewById(R.id.tv_three);
        mFourTv = findViewById(R.id.tv_four);

        mOneTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.getInstance().build("/main/one").navigation(MainActivity.this);
//                startActivity(new Intent(MainActivity.this, TestOneActivity.class));
            }
        });
        mTwoTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.getInstance().build("/main/two").navigation(MainActivity.this);
            }
        });
        mThreeTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.getInstance().build("/chat/one").navigation(MainActivity.this);
            }
        });
        mFourTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.getInstance().build("/chat/two").navigation(MainActivity.this);
            }
        });

    }

}
