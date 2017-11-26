package com.yw.common.beansUtils.utils;

/**
 * 功   能:
 * 创建者: 施俊帆
 * 日   期: 2015/3/13 11:23
 * Q  Q: 362116120
 */
public interface Convert<T> {
    public String convert(Object...params);
    public T reconvert(Object...params);
}
