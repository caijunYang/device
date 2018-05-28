package com.itplayer.core.base.validate;

/**
 * @Description：动态推荐位延伸方向<br>
 * @author：xxq<br>
 * @date：2017年8月4日<br>
 */
public enum RegexType {
	NONE,
	/**
	 * 特殊字符
	 */
	SPECIALCHAR,
	/**
	 * 中文字符
	 */
	CHINESE,
	/**
	 * 电子邮件
	 */
	EMAIL,
	/**
	 * IP地址
	 */
	IP,
	/**
	 * 数字
	 */
	NUMBER,
	/**
	 * 电话号码
	 */
	PHONENUMBER;
}
