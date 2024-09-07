package org.dada.demo.test;

import com.googlecode.protobuf.format.JsonFormat;
import org.dada.demo.netty.domain.MsgBody;


public class ApiTest {

    public static void main(String[] args) throws JsonFormat.ParseException {
        System.out.println("hi ");

        MsgBody.Builder msg = MsgBody.newBuilder();
        msg.setChannelId("abD01223");
        msg.setMsgInfo("hi helloworld");
        MsgBody msgBody = msg.build();

        //protobuf转Json 需要引入protobuf-java-format
        String msgBodyStr = JsonFormat.printToString(msgBody);
        System.out.println(msgBodyStr);

        //json转protobuf 需要引入protobuf-java-format
        JsonFormat.merge("{\"channelId\": \"HBdhi993\",\"msgInfo\": \"hi 算法猫叔\"}", msg);
        msgBody = msg.build();
        System.out.println(msgBody.getChannelId());
        System.out.println(msgBody.getMsgInfo());

    }

}
