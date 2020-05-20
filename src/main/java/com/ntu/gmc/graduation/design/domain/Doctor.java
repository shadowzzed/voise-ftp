package com.ntu.gmc.graduation.design.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Zed
 * @date 2020/5/20 下午7:38
 * @contact shadowl91@163.com
 */
@Entity
@Data
@Table(name = "t_doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "account")
    private String account;

    @Column(name = "password")
    private String password;
}
