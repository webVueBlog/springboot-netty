package org.dada.demo.netty.redis;

import com.alibaba.fastjson.JSON;
import org.dada.demo.netty.domain.UserChannelInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 虫洞栈：https://bugstack.cn
 * 公众号：算法猫叔  ｛关注获取学习源码｝
 * Create by fuzhengwei on 2019
 */
@Service("redisUtil")
public class RedisUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void pushObj(UserChannelInfo userChannelInfo) {
        redisTemplate.opsForHash().put("dada-demo-netty-2-09-user", userChannelInfo.getChannelId(), JSON.toJSONString(userChannelInfo));
    }

    public List<UserChannelInfo> popList() {
        List<Object> values = redisTemplate.opsForHash().values("dada-demo-netty-2-09-user");
        if (null == values) return new ArrayList<>();
        List<UserChannelInfo> userChannelInfoList = new ArrayList<>();
        for (Object strJson : values) {
            userChannelInfoList.add(JSON.parseObject(strJson.toString(), UserChannelInfo.class));
        }
        return userChannelInfoList;
    }

    public void remove(String channelId) {
        redisTemplate.opsForHash().delete("dada-demo-netty-2-09-user",channelId);
    }

    public void clear(){
        redisTemplate.delete("dada-demo-netty-2-09-user");
    }

}
