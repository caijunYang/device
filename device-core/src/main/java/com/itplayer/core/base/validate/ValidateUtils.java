package com.itplayer.core.base.validate;

import com.itplayer.core.base.exception.SystemException;
import com.itplayer.core.base.utils.StrUtils;

import java.lang.reflect.Field;

/**
 * @Description：对象属性注解验证工具类<br>
 * @author：xxq<br>
 * @date：2017年8月7日<br>
 */
public class ValidateUtils {
    private static Validate validate;

    public ValidateUtils() {
        super();
    }

    /**
     * 对象属性值数据验证
     *
     * @param object 验证对象
     * @throws Exception
     */
    public static void validate(Object object) {
        // 获取object的类型
        Class<? extends Object> clazz = object.getClass();
        // 获取该类型声明的成员
        Field[] fields = clazz.getDeclaredFields();
        // 遍历属性
        for (Field field : fields) {
            // 对于private私有化的成员变量，通过setAccessible来修改器访问权限
            field.setAccessible(true);
            try {
                validate(field, object);
            } catch (Exception e) {
                throw new SystemException(e);
            }
            // 重新设置会私有权限
            field.setAccessible(false);
        }
    }

    /**
     * 指定属性值数据验证
     *
     * @param field  属性
     * @param object 值
     * @throws Exception
     */
    public static void validate(Field field, Object object) throws Exception {

        // 获取对象的成员的注解信息
        validate = field.getAnnotation(Validate.class);
        if (validate == null) {// 没有注解信息不做数据验证
            return;
        }
        Object value = field.get(object);
        String description = validate.description().equals("") ? field.getName() : validate.description();

        /************* 注解解析工作开始 ******************/
        if (!validate.nullable()) {
            if (value == null || StrUtils.isNull(value.toString())) {
                throw new SystemException(description + "不能为空");
            }
        } else {
            if (value == null) {
                return;
            }
        }

        if (value.toString().length() > validate.maxLength() && validate.maxLength() != 0) {
            throw new SystemException(description + "长度不能超过" + validate.maxLength());
        }

        if (value.toString().length() < validate.minLength() && validate.minLength() != 0) {
            throw new SystemException(description + "长度不能小于" + validate.minLength());
        }

        if (validate.regexType() != null) {
            switch (validate.regexType()) {
                case NONE:
                    break;
                case SPECIALCHAR:
                    if (RegexUtils.hasSpecialChar(value.toString())) {
                        throw new SystemException(description + "不能含有特殊字符");
                    }
                    break;
                case CHINESE:
                    if (RegexUtils.isChinese2(value.toString())) {
                        throw new SystemException(description + "不能含有中文字符");
                    }
                    break;
                case EMAIL:
                    if (!RegexUtils.isEmail(value.toString())) {
                        throw new SystemException(description + "地址格式不正确");
                    }
                    break;
                case IP:
                    if (!RegexUtils.isIp(value.toString())) {
                        throw new SystemException(description + "地址格式不正确");
                    }
                    break;
                case NUMBER:
                    if (!RegexUtils.isNumber(value.toString())) {
                        throw new SystemException(description + "不是数字");
                    }
                    break;
                case PHONENUMBER:
                    if (!RegexUtils.isPhoneNumber(value.toString())) {
                        throw new SystemException(description + "不是数字");
                    }
                    break;
                default:
                    break;
            }
        }

        if (StrUtils.isNotNull(validate.regexExpression())) {
            if (!value.toString().matches(validate.regexExpression())) {
                throw new SystemException(description);
            }
        }
        /************* 注解解析工作结束 ******************/
    }
}
