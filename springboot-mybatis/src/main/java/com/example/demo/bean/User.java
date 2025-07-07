package com.example.demo.bean;

import lombok.Data;

@Data
public class User {
    private Integer id;

    /**
     * 姓名
     *
     * @mbg.generated Thu Jan 18 15:35:08 CST 2018
     */
    private String name;

    /**
     * 昵称
     *
     * @mbg.generated Thu Jan 18 15:35:08 CST 2018
     */
    private String nickName;

    private String password;

    private Integer age;

    /**
     * 状态，1：正常，2：锁定，3：不完整用户
     *
     * @mbg.generated Thu Jan 18 15:35:08 CST 2018
     */
    private String status;
}