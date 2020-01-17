package com.github.houbb.pinyin.exception;

/**
 * 拼音异常类
 * @author binbin.hou
 * @since 0.0.1
 */
public class PinyinException extends RuntimeException {

    public PinyinException() {
    }

    public PinyinException(String message) {
        super(message);
    }

    public PinyinException(String message, Throwable cause) {
        super(message, cause);
    }

    public PinyinException(Throwable cause) {
        super(cause);
    }

    public PinyinException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
