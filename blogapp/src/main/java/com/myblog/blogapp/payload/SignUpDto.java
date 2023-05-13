package com.myblog.blogapp.payload;

import lombok.Data;

@Data
public class SignUpDto {
    private String name;
    private String email;
    private String username;
    private String password;
}
