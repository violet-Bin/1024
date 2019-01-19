package com.tensquare.qa.client.imp;

import com.tensquare.qa.client.BaseClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @Author: jiangjiabin
 * @Description:
 * @Date: Create in 2:01 2019/1/20
 */
@Component
public class BaseClientImpl implements BaseClient {


    @Override
    public Result findById(String labelId) {
        return new Result(false, StatusCode.ERROR, "熔断器触发了！");
    }
}
