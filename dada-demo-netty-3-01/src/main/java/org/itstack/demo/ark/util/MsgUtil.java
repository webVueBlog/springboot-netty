package org.dada.demo.ark.util;

import com.alibaba.fastjson.JSON;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.dada.demo.ark.domain.InfoProtocol;
import org.dada.demo.ark.domain.msgobj.Text;


public class MsgUtil {

    public static String buildMsg(InfoProtocol infoProtocol) {
        return JSON.toJSONString(infoProtocol) + "\r\n";
    }

    public static InfoProtocol getMsg(String str) {
        return JSON.parseObject(str, InfoProtocol.class);
    }

    public static TextWebSocketFrame buildWsMsgText(String channelId, String msgInfo) {
        InfoProtocol infoProtocol = new InfoProtocol();
        infoProtocol.setChannelId(channelId);
        infoProtocol.setMsgType(1);
        infoProtocol.setMsgObj(new Text(msgInfo));
        return new TextWebSocketFrame(JSON.toJSONString(infoProtocol));
    }

}
