package com.tensquare.friend.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * @Author: jiangjiabin
 * @Description:
 * @Date: Create in 2:21 2019/1/18
 */
@Data
@Table(name = "tb_nofriend")
@IdClass(NoFriend.class)
public class NoFriend {

    @Id
    private String userid;

    @Id
    private String friendid;

}
