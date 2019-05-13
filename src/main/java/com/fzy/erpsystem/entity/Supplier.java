package com.fzy.erpsystem.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: Supplier
 * @description:
 * @author: fzy
 * @date: 2019/05/10 11:28:06
 **/
@Data
public class Supplier implements Serializable {

    private Long id;

    private String name;

    private String address;

    private String phone;
}
