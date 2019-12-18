package com.nsyw.routerdemo.router_compiler;

import com.nsyw.routerdemo.router_compiler.annotation.Route;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import static com.nsyw.routerdemo.router_compiler.consts.ACTIVITY;
import static com.nsyw.routerdemo.router_compiler.consts.IROUTE;
import static com.nsyw.routerdemo.router_compiler.consts.KEY_MODULE_NAME;
import static com.nsyw.routerdemo.router_compiler.consts.METHOD_LOAD_INTO;
import static com.nsyw.routerdemo.router_compiler.consts.NAME_OF_ROUTE;
import static com.nsyw.routerdemo.router_compiler.consts.PACKAGE_OF_GENERATE_FILE;
import static com.nsyw.routerdemo.router_compiler.consts.WARNING_TIPS;
import static javax.lang.model.element.Modifier.PUBLIC;

/**
 * @author : yetianhua
 * @project: RouterDemo
 * @date : 2019-12-18
 * @time : 15:19
 * @note :
 */
/**
 * @SupportedAnnotationTypes表示支持的注解类型
 */
@SupportedAnnotationTypes("com.nsyw.routerdemo.router_compiler.annotation.Route")
public class RouteProcessor extends AbstractProcessor {
    private Filer mFiler;
    private Types types;
    private Elements mElementsUtil;
    /**
     * 当前App的包名
     */
    private String moduleName;
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnvironment.getFiler();
        types = processingEnv.getTypeUtils();
        mElementsUtil = processingEnvironment.getElementUtils();
        // 获取当前Application的包名
        Map<String, String> options = processingEnv.getOptions();
        if (MapUtils.isNotEmpty(options)) {
            moduleName = options.get(KEY_MODULE_NAME);
        }
    }
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> routeElements = roundEnvironment.getElementsAnnotatedWith(Route.class);
        if (CollectionUtils.isNotEmpty(routeElements)) {
            TypeMirror type_Activity = mElementsUtil.getTypeElement(ACTIVITY).asType();
            /*
              参数类型Map<String,RouteMeta>
             */
            ParameterizedTypeName inputMapTypeOfGroup = ParameterizedTypeName.get(
                    ClassName.get(Map.class),
                    ClassName.get(String.class),
                    ClassName.get(RouteMeta.class)
            );
            ParameterSpec groupParamSpec = ParameterSpec.builder(inputMapTypeOfGroup, "routes").build();
            /*
              methodBuilder 方法名
              addAnnotation 方法添加注解
              addModifiers  方法访问限制类型
              addParameter  添加参数
             */
            MethodSpec.Builder loadIntoMethodOfGroupBuilder = MethodSpec.methodBuilder(METHOD_LOAD_INTO)
                    .addAnnotation(Override.class)
                    .addModifiers(PUBLIC)
                    .addParameter(groupParamSpec);
            ClassName routeMetaCn = ClassName.get(RouteMeta.class);
            ClassName routeTypeCn = ClassName.get(RouteType.class);
            //遍历@Route注解的所有Activity
            for (Element element : routeElements) {
                TypeMirror tm = element.asType();
                //获取注解
                Route route = element.getAnnotation(Route.class);
                RouteMeta routeMeta = null;
                if (types.isSubtype(tm, type_Activity)) {
                    routeMeta = new RouteMeta(route.path(), RouteType.ACTIVITY);
                }
                //获取被注解的类的类名
                ClassName className = ClassName.get((TypeElement) element);
                /*
                  方法内的添加路由语句
                  routes.put(routeMeta.getPath(),RouteMeta.build(routeMeta.getPath(),RouteType.ACTIVITY,className.class))
                 */
                loadIntoMethodOfGroupBuilder.addStatement(
                        "routes.put($S,$T.build($S,$T." + routeMeta.getRouteType() + ", $T.class))",
                        routeMeta.getPath(),
                        routeMetaCn,
                        routeMeta.getPath(),
                        routeTypeCn,
                        className);
            }
            /*
              构建java文件
             */
            try {
                JavaFile.builder(PACKAGE_OF_GENERATE_FILE,
                        TypeSpec.classBuilder(NAME_OF_ROUTE + moduleName)
                                .addJavadoc(WARNING_TIPS)
                                .addSuperinterface(ClassName.get(mElementsUtil.getTypeElement(IROUTE)))
                                .addModifiers(PUBLIC)
                                .addMethod(loadIntoMethodOfGroupBuilder.build())
                                .build()
                ).build().writeTo(mFiler);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
}
