package com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

public class ParseJwt {

    public static void main(String[] args) {
        Claims claims = Jwts.parser().setSigningKey("bingo")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiLlrr7lrr4iLCJpYXQiOjE1NDc2NDQxODQsImV4cCI6MTU0NzY0NDI0NCwicm9sZSI6ImFkbWluIn0.zqQRHMEPWWU2MoVzWZKxSCPY0YQ7QbJ5yUPoY0Fq6J0")
                .getBody();
        System.out.println("用户id:" + claims.getId());
        System.out.println("用户名:" + claims.getSubject());
        System.out.println("登录时间:" + new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(claims.getIssuedAt()));
        System.out.println("用户角色:" + claims.get("role"));
    }


}
