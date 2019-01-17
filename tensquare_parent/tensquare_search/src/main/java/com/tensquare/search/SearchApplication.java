package com.tensquare.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import util.IdWorker;

/**
 * @Author: jiangjiabin
 * @Description:
 * @Date: Create in 0:00 2019/1/14
 */
@SpringBootApplication
@EnableEurekaClient
public class SearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class);
    }

    @Autowired
    public IdWorker idWorker() {
        return new IdWorker(1,1);
    }


}
