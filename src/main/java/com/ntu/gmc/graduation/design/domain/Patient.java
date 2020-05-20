package com.ntu.gmc.graduation.design.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Zed
 * @date 2020/5/20 下午7:45
 * @contact shadowl91@163.com
 */
@Entity
@Data
@Table(name = "t_patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private String age;

    @Column(name = "phone")
    private String phone;

    @Column(name = "illness")
    private String illness;

    @Column(name = "doctorid")
    private int doctorid;

    @Column(name = "account")
    private String account;

    @Column(name = "password")
    private String password;


}
