package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: <a href="mailto:jiangjiabin3@jd.com">江家宾</a>
 * @date: 2019/1/18
 */
@FeignClient("tensquare-user")
public interface UserClient {

    @RequestMapping(value = "/user/{userid}/{friendid}/{x}", method = RequestMethod.PUT)
    void updateFansCountAndFollowCount(@PathVariable String userid, @PathVariable String friendid, @PathVariable int x);

}
