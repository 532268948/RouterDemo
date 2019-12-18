package com.nsyw.routerdemo.router_compiler;

import java.util.Map;

/**
 * @author : yetianhua
 * @project: RouterDemo
 * @date : 2019-12-18
 * @time : 15:25
 * @note :
 */
public interface IRoute {
    /**
     *
     * @param routes 模块下的路由集合
     */
    void loadInto(Map<String, RouteMeta> routes);
}
