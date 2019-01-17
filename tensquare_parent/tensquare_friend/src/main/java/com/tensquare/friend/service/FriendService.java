package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.pojo.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: jiangjiabin
 * @Description:
 * @Date: Create in 2:07 2019/1/18
 */
@Service("friendService")
@Transactional
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    public int addFriend(String userid, String friendid) {
        //先判断userid到friendid是否有数据，有就是重复添加好友，return 0
        Friend friend = friendDao.findByUseridAndFriendid(userid, friendid);
        if (friend != null) {
            return 0;
        }
        //直接添加好友，让好友表中userid到friendid方向的type为0
        friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);
        //判断从friendid到userid是否有数据，如果有，把双方的状态都改为1
        if (friendDao.findByUseridAndFriendid(friendid, userid) != null) {
            //把双方都置为1
            friendDao.updateIsLike("", userid, friendid);
            friendDao.updateIsLike("", friendid, userid);
        }
        return 0;
    }
}
