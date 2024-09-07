package org.dada.demo.netty;

import org.dada.demo.netty.redis.RedisUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;

/**
 * 消息传输协议
 * 虫洞栈：https://bugstack.cn
 * 公众号：算法猫叔  ｛获取学习源码｝
 * 虫洞群：①群5398358 ②群5360692
 * Create by fuzhengwei on @2019
 */
@SpringBootApplication
@ComponentScan("org.dada.demo.netty")
public class Application implements CommandLineRunner{

    @Resource
    private RedisUtil redisUtil;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        redisUtil.clear();
    }

}
