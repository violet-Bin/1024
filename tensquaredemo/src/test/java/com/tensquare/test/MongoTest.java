package com.tensquare.test;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: jiangjiabin
 * @Description:
 * @Date: Create in 2:53 2019/1/13
 */
public class MongoTest {

    public static void main(String[] args) {

        //链接mongo服务器
        MongoClient mongoClient = new MongoClient("39.105.189.50");
        //得到要操作的数据库
        MongoDatabase spitdb = mongoClient.getDatabase("spitdb");
        //得到要操作的集合
        MongoCollection<Document> spit = spitdb.getCollection("spit");

        //封装查询条件: 只查询用户id为1013的
//        BasicDBObject bson = new BasicDBObject("userid", "1013");
        //封装条件：查询访问量大于1000的
//        BasicDBObject bson = new BasicDBObject("visits", new BasicDBObject("$gt", 1000));
//        //得到集合中所有的文档
//        FindIterable<Document> documents = spit.find(bson);
//        //遍历数据
//        for (Document d : documents) {
//            System.out.println("内容：" + d.getString("content"));
//            System.out.println("用户id：" + d.getString("userid"));
//            System.out.println("访问量：" + d.getInteger("visits"));
//        }

        //添加纪录
        Map<String, Object> map = new HashMap();
        map.put("content","时间过的很好快");
        map.put("userid","1016");
        map.put("visits",999);
        map.put("_id", "001");
        Document document = new Document(map);
        spit.insertOne(document);



        mongoClient.close();

    }

}
