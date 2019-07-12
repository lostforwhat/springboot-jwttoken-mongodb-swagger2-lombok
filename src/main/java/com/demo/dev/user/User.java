package com.demo.dev.user;


import lombok.Data;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="用户对象user")
@Data
public class User {
    @Id
    private String id;

    @ApiModelProperty(value="用户名",name="username")
    @Indexed(unique=true, direction= IndexDirection.DESCENDING, dropDups=true)
    private String username;

    @ApiModelProperty(value="密码",name="password")
    private String password;
    
    @ApiModelProperty(value="邮箱",name="email")
    private String email;
    private Date lastPasswordResetDate;
    private List<String> roles;
}
