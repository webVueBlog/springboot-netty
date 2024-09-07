package org.dada.demo.netty.domain;

import org.dada.demo.netty.domain.protocol.Command;
import org.dada.demo.netty.domain.protocol.Packet;


public class MsgDemo02 extends Packet {

    private String channelId;
    private String demo02;

    public MsgDemo02(String channelId, String demo02) {
        this.channelId = channelId;
        this.demo02 = demo02;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getDemo02() {
        return demo02;
    }

    public void setDemo02(String demo02) {
        this.demo02 = demo02;
    }

    @Override
    public Byte getCommand() {
        return Command.Demo02;
    }
}
