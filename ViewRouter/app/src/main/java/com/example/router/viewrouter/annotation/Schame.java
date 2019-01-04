package com.example.router.viewrouter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description:$Method$
 * Data: $Date$ $Time$
 *
 * @author: guojian
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Schame {
    String schame();
    String host();
    String path();
}
