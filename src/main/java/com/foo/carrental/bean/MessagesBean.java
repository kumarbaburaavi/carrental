package com.foo.carrental.bean;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * Utility class to make the gathering of properties from messages_en.properties easier.
 */
@Component
public class MessagesBean {

    private static final Locale locale = new Locale("en");
    private static final Object[] args = null;

    @Autowired
    private MessageSource messageSource;

    public String get(String code) {
        return messageSource.getMessage(code, args, locale);
    }
}