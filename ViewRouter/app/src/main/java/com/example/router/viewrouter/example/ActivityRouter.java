package com.example.router.viewrouter.example;

import android.content.Context;

import com.example.router.viewrouter.annotation.Clazz;
import com.example.router.viewrouter.annotation.Field;
import com.example.router.viewrouter.annotation.Schame;

/**
 * Description:$Method$
 * Data: $Date$ $Time$
 *
 * @author: guojian
 */
public interface ActivityRouter {

    /**
     * native跳转
     * @param context
     * @param num
     * @param s
     */
    @Clazz(MainActivity2.class)
    void start(Context context, int num, String s);

    /**
     * schame协议跳转
     * @param context
     * @param num
     * @param s
     */
    @Schame(schame = "schame", host = "host", path = "/path")
    void schame(Context context, @Field("num") int num, @Field("str") String s);

}
