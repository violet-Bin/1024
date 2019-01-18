package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: jiangjiabin
 * @Description:
 * @Date: Create in 2:20 2019/1/18
 */
public interface NoFriendDao extends JpaRepository<NoFriend, String> {

    NoFriend findByUseridAndFriendid(String userid, String friendis);

}
