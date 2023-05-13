package com.myblog.blogapp.utils;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncodePassword {
    public static void main(String[] args) {
        PasswordEncoder encodePassword= new BCryptPasswordEncoder();
        System.out.println(encodePassword.encode("bag"));
    }
}
