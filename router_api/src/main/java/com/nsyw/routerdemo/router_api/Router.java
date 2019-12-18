package com.nsyw.routerdemo.router_api;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import com.nsyw.routerdemo.router_compiler.IRoute;
import com.nsyw.routerdemo.router_compiler.RouteMeta;
import com.nsyw.routerdemo.router_compiler.consts;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author : yetianhua
 * @project: RouterDemo
 * @date : 2019-12-18
 * @time : 15:48
 * @note :
 */
public class Router {

    private static volatile Router mInstance = new Router();
    private Context mContext;
    private String path;
    private Map<String, RouteMeta> map = new HashMap<>();

    public static void init(Application application) {
        mInstance.mContext = application;
        Set<String> routerMap;
        try {
            routerMap = ClassUtils.getFileNameByPackageName(mInstance.mContext, consts.PACKAGE_OF_GENERATE_FILE);
            Log.e("Router", routerMap.toString());
            for (String className : routerMap) {
                ((IRoute) (Class.forName(className).getConstructor().newInstance())).loadInto(mInstance.map);
            }
        } catch (PackageManager.NameNotFoundException | InterruptedException | IOException | ClassNotFoundException | NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static synchronized Router getInstance() {
        return mInstance;
    }

    public Router build(String path) {
        mInstance.path = path;
        return mInstance;
    }

    public void navigation(Context context) {
        RouteMeta routeMeta = mInstance.map.get(mInstance.path);
        if (routeMeta != null) {
            context.startActivity(new Intent(context, routeMeta.getClazz()));
        }
    }

}

