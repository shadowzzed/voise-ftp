package com.ntu.gmc.graduation.design.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Zed
 * @date 2020/5/20 下午7:48
 * @contact shadowl91@163.com
 */
@Entity
@Data
@Table(name = "t_voise")
public class Voise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voiseid")
    private int id;

    @Column(name = "patientid")
    private int patientid;

    @Column(name = "url")
    private String url;

    @Column(name = "start")
    private String start;

    @Column(name = "end")
    private String end;
}
