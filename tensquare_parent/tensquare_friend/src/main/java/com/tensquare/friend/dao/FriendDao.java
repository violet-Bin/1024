package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author: jiangjiabin
 * @Description:
 * @Date: Create in 2:20 2019/1/18
 */
public interface FriendDao extends JpaRepository<Friend, String> {

    Friend findByUseridAndFriendid(String userid, String friendis);

    @Query(value = "UPDATE tb_friend SET islike=? WHERE userid=? AND friendid=?", nativeQuery = true)
    void updateIsLike(String islike, String userid, String friendid);

}
