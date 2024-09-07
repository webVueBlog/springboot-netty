package org.dada.demo.netty.redis.config;

import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 发布者
 * 虫洞栈：https://bugstack.cn
 * 公众号：算法猫叔  ｛获取学习源码｝
 * 虫洞群：①群5398358 ②群5360692
 * Create by fuzhengwei on @2019
 */
@Configuration
public class PublisherConfig {

    @Bean
    public RedisTemplate<String, Object> redisMessageTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setDefaultSerializer(new FastJsonRedisSerializer<>(Object.class));
        return template;
    }

}
