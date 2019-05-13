package com.fzy.erpsystem.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: User
 * @description:
 * @author: fzy
 * @date: 2019/05/10 11:27:03
 **/
@Data
public class User  implements Serializable {

    private Long id;

    private String userName;

    private String password;
}
