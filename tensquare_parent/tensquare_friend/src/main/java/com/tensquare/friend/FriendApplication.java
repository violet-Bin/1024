package com.tensquare.friend;

import com.tensquare.friend.client.UserClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import util.JwtUtil;

/**
 * @Author: jiangjiabin
 * @Description:
 * @Date: Create in 1:49 2019/1/18
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class FriendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FriendApplication.class);
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }

}
