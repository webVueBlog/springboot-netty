package org.dada.demo.netty.domain;


public class MsgInfo {

    private String channelId;
    private String msgContent;

    public MsgInfo() {
    }

    public MsgInfo(String channelId, String msgContent) {
        this.channelId = channelId;
        this.msgContent = msgContent;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
}
