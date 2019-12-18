package com.nsyw.routerdemo;

import android.app.Application;

import com.nsyw.routerdemo.router_api.Router;
import com.nsyw.routerdemo.router_compiler.annotation.Route;

/**
 * @author : yetianhua
 * @project: RouterDemo
 * @date : 2019-12-18
 * @time : 16:01
 * @note :
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Router.init(this);
    }
}
