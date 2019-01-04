package com.example.router.viewrouter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.router.viewrouter.annotation.Clazz;
import com.example.router.viewrouter.annotation.Field;
import com.example.router.viewrouter.annotation.Schame;
import com.example.router.viewrouter.utils.MeepoUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:$Method$
 * Data: $Date$ $Time$
 *
 * @author: guojian
 */
public class Router {

    private HashMap<String, Integer> bundlePositions = new HashMap<>();

    private Map<String, Object> parse(Annotation[] annotations) {
        Map<String, Object> params = new HashMap<>();

        for (Annotation annotation : annotations) {
            if (annotation instanceof Clazz) {
                params.put("class", ((Clazz) annotation).value());
            }
            if (annotation instanceof Schame) {
                Map<String, Object> schame = new HashMap<>();
                schame.put("schame", ((Schame) annotation).schame());
                schame.put("host", ((Schame) annotation).host());
                schame.put("path", ((Schame) annotation).path());
                params.put("schame", schame);
            }
        }
        return params;
    }

    public <T> T init(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                        startActivity(getContextFromFirstParameter(objects), parse(method.getAnnotations()), bindData(method, objects));
                        return o;
                    }
                });
    }

    private void startActivity(Context context, Map<String, Object> params, Bundle bundle) {
        Class clazz = (Class) params.get("class");
        if (clazz != null) {
            Intent intent = new Intent();
            intent.setClass(context, clazz);
            context.startActivity(intent, bundle);
        }
        Map<String, Object> schame = (Map<String, Object>) params.get("schame");
        if (schame != null) {
            String uri = schame.get("schame") + "://" + schame.get("host") + schame.get("path");
            Intent intent = new Intent();
            intent.setData(Uri.parse(uri));
            context.startActivity(intent);
        }
    }

    private Bundle bindData(Method method, Object[] objects) {
        Annotation[][] parameterAnnotationsArray = method.getParameterAnnotations();
        Bundle bundle = new Bundle();
        bundlePositions = new HashMap<>();
        for (int i = 0; i < parameterAnnotationsArray.length; i++) {
            final Annotation[] annotations = parameterAnnotationsArray[i];
            for (Annotation annotation : annotations) {
                if (annotation instanceof Field) {
                    bundlePositions.put(((Field) annotation).value(), i);
                }
            }
        }

        for (Map.Entry<String, Integer> entry : bundlePositions.entrySet()) {
            MeepoUtils.putValueToBundle(bundle, entry.getKey(), objects[entry.getValue()]);
        }
        return bundle;
    }

    public Context getContextFromFirstParameter(Object[] args) {
        if ((args.length != 0) && (args[0] instanceof Context || args[0] == null)) {
            return (Context) args[0];
        } else {
            throw new RuntimeException("First parameter must be context.");
        }
    }

}
