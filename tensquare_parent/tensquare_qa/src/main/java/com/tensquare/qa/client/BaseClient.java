package com.tensquare.qa.client;

import com.tensquare.qa.client.imp.BaseClientImpl;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: jiangjiabin
 * @date: 2019/1/17
 */
@FeignClient(value = "tensquare-base", fallback = BaseClientImpl.class)
public interface BaseClient {

    @RequestMapping(value = "/label/{labelId}", method = RequestMethod.GET)
    Result findById(@PathVariable("labelId") String labelId);

}
