package com.itplayer.core.base.validate;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description：数据验证<br>
 * @author：xxq<br>
 * @date：2017年8月7日<br>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Validate {
    /**
     * 是否可以为空
     */
    boolean nullable() default true;

    /**
     * 最大长度
     */
    int maxLength() default 0;

    /**
     * 最小长度
     */
    int minLength() default 0;

    /**
     * 常用正则验证
     */
    RegexType regexType() default RegexType.NONE;

    /**
     * 自定义正则验证
     */
    String regexExpression() default "";

    /**
     * 参数或者字段描述,这样能够显示友好的异常信息
     */
    String description() default "";

}
