package com.dsdl.eidea.core.spring.annotation;

import java.lang.annotation.*;

/**
 * Created by cityre on 2017/7/25.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.PARAMETER})
@Documented
public @interface DataSourceName {
    public String dataSourceName() default "dataSource_core";
}
