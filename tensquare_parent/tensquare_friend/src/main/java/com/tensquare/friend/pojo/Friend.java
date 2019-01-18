package com.tensquare.friend.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: jiangjiabin
 * @Description:
 * @Date: Create in 2:21 2019/1/18
 */
@Data
@Table(name = "tb_friend")
@IdClass(Friend.class)
@Entity
public class Friend implements Serializable {

    @Id
    private String userid;

    @Id
    private String friendid;

    private String islike;
}
