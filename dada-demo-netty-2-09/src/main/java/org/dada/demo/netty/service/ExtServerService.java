package org.dada.demo.netty.service;

import org.dada.demo.netty.domain.MsgAgreement;
import org.dada.demo.netty.redis.Publisher;
import org.dada.demo.netty.redis.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 扩展服务
 * 虫洞栈：https://bugstack.cn
 * 公众号：算法猫叔  ｛关注获取学习源码｝
 * 虫洞群：①群5398358 ②群5360692
 * Create by fuzhengwei on 2019
 */
@Service("extServerService")
public class ExtServerService {

    @Resource
    private Publisher publisher;
    @Resource
    private RedisUtil redisUtil;

    public void push(MsgAgreement msgAgreement){
        publisher.pushMessage("dada-demo-netty-push-msgAgreement", msgAgreement);
    }

    public RedisUtil getRedisUtil() {
        return redisUtil;
    }
}
