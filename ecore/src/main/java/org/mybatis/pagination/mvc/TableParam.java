/*
 * Copyright © 2012-2013 mumu@yfyang. All Rights Reserved.
 */

package org.mybatis.pagination.mvc;

import java.lang.annotation.*;

/**
 * <p>
 * Spring MVC argument resolver annotation.
 * </p>
 *
 * @author mumu@yfyang
 * @version 1.0 2013-09-05 10:43 PM
 * @since JDK 1.5
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableParam {
}
