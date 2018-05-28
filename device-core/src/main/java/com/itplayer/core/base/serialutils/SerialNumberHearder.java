package com.itplayer.core.base.serialutils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by caijun.yang on 2017/7/28.
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface SerialNumberHearder {
	/**
	 * 前缀
	 */
	String prefix() default "";

	/**
	 * 后缀
	 */
	String suffix() default "";
}
