package com.nsyw.routerdemo.router_compiler;

/**
 * @author : yetianhua
 * @project: RouterDemo
 * @date : 2019-12-18
 * @time : 15:24
 * @note :
 */
public class RouteMeta {
    /**
     * 路径名称
     */
    private String path;
    /**
     * 路由类型 ,现在只考虑Activity
     */
    private RouteType routeType;
    /**
     * 注解的Activity的类
     */
    private Class clazz;
    public RouteMeta(String path, RouteType routeType) {
        this.path = path;
        this.routeType = routeType;
    }
    public RouteMeta(String path, RouteType routeType, Class clazz) {
        this.path = path;
        this.routeType = routeType;
        this.clazz = clazz;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public RouteType getRouteType() {
        return routeType;
    }
    public void setRouteType(RouteType routeType) {
        this.routeType = routeType;
    }
    public Class getClazz() {
        return clazz;
    }
    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
    public static RouteMeta build(String path, RouteType routeType, Class clazz) {
        return new RouteMeta(path, routeType, clazz);
    }
}
