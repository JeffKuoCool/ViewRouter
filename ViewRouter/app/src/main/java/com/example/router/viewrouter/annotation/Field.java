package com.example.router.viewrouter.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Description:$Method$
 * Data: $Date$ $Time$
 *
 * @author: guojian
 */
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface Field {
    String value();
}
