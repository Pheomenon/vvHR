package com.gao.hr;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class HrApplicationTests {

    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("23","32");
    }

}
