package org.dada.demo.netty.util;

import org.dada.demo.netty.domain.MsgInfo;


public class MsgUtil {

    public static MsgInfo buildMsg(String channelId, String msgContent) {
        return new MsgInfo(channelId,msgContent);
    }

}
