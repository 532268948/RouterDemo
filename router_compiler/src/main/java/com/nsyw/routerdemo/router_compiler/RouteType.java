package com.nsyw.routerdemo.router_compiler;

/**
 * @author : yetianhua
 * @project: RouterDemo
 * @date : 2019-12-18
 * @time : 15:24
 * @note :
 */
public enum RouteType {
    ACTIVITY(0, "android.app.Activity"),
    UNKNOWN(-1, "Unknown route type");
    int id;
    String className;
    public int getId() {
        return id;
    }
    public RouteType setId(int id) {
        this.id = id;
        return this;
    }
    public String getClassName() {
        return className;
    }
    public RouteType setClassName(String className) {
        this.className = className;
        return this;
    }
    RouteType(int id, String className) {
        this.id = id;
        this.className = className;
    }
}
