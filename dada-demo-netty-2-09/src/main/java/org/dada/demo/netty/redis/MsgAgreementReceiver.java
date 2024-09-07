package org.dada.demo.netty.redis;

import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import org.dada.demo.netty.domain.MsgAgreement;
import org.dada.demo.netty.util.CacheUtil;
import org.dada.demo.netty.util.MsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 虫洞栈：https://bugstack.cn
 * 公众号：算法猫叔  ｛获取学习源码｝
 * 虫洞群：①群5398358 ②群5360692
 * Create by fuzhengwei on @2019
 */
@Service
public class MsgAgreementReceiver extends AbstractReceiver {

    private Logger logger = LoggerFactory.getLogger(MsgAgreementReceiver.class);

    @Override
    public void receiveMessage(Object message) {
        logger.info("接收到PUSH消息：{}", message);
        MsgAgreement msgAgreement = JSON.parseObject(message.toString(), MsgAgreement.class);
        String toChannelId = msgAgreement.getToChannelId();
        Channel channel = CacheUtil.cacheChannel.get(toChannelId);
        if (null == channel) return;
        channel.writeAndFlush(MsgUtil.obj2Json(msgAgreement));
    }

}
