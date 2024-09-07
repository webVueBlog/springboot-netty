package org.dada.demo.test;

import org.dada.demo.netty.domain.MsgAgreement;
import org.dada.demo.netty.util.MsgUtil;

/**
 * 虫洞栈：https://bugstack.cn
 * 公众号：算法猫叔  ｛获取学习源码｝
 * 虫洞群：①群5398358 ②群5360692
 * Create by fuzhengwei on @2019
 */
public class ApiTest {

    public static void main(String[] args) {
        System.out.println("公众号：算法猫叔 | 获取学习源码");
        MsgAgreement msgAgreement = MsgUtil.buildMsg("6e0216ea", "hi! I'm  | 欢迎关注&获取源码。* 来自A服务端里用户向B服务端里用户发送信息。[结尾换行，用于处理半包粘包]");
        String json = MsgUtil.obj2Json(msgAgreement);
        System.out.println(json);
        //{"content":"hi! I'm  | 欢迎关注&获取源码。* 来自A服务端里用户向B服务端里用户发送信息。[结尾换行，用于处理半包粘包]","toChannelId":"6e0216ea"}
    }

}
