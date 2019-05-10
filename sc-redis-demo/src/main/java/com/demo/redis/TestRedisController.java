package com.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/redis")
public class TestRedisController {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    RedisStringUtils redisStringUtils;

    //@PathParam: http://localhost:9800/redis/getKey?key=test
    @GetMapping(value = "/getKey")
    public String testGetKey(@PathParam(value = "key") String key){
        System.out.println("key="+key);
        String msg = redisStringUtils.getValue(key);
        System.out.println("result="+msg);
        return msg;
    }

    //@PathParam: http://localhost:9800/redis/getKey2/test
    @GetMapping(value = "/getKey2/{key}")
    public String testSetKey2(@PathVariable String key){
        System.out.println("key="+key);

        return redisUtils.get(key);
    }
    //@QueryParam: http://localhost:9800/redis/getKey3?key=test
//    @GetMapping(value = "/getKey3")
//    public String testGetKey(@QueryParam String key){
//        System.out.println("key="+key);
//        return redisUtil.get(key).toString();
//    }

    @GetMapping(value = "/setKeyValue")
    public String setKeyVaule(@RequestParam(value = "key",required = true)String key,@RequestParam String value){
        System.out.println("key="+key+" value="+value);
//        redisStringUtils.setKey(key,value);


        redisUtils.set(key,value);
        redisUtils.expire(key,60);
        return "success";
    }

    //@PathParam: http://localhost:9800/redis/delKey?key=test
    @GetMapping(value = "/delKey")
    public String testDelKey(@RequestParam String key){
        System.out.println("del key="+key);

        boolean flag = redisUtils.delete(key);
//        boolean flag = redisStringUtils.delete(key);
        return "delete success:"+flag;
    }

}
