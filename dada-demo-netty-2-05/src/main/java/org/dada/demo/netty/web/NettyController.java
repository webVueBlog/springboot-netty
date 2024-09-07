package org.dada.demo.netty.web;

import org.dada.demo.netty.server.NettyServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 消息传输协议
 * 虫洞栈：https://bugstack.cn
 * 公众号：算法猫叔  ｛获取学习源码｝
 * 虫洞群：①群5398358 ②群5360692
 * Create by fuzhengwei on @2019
 */
@Controller
public class NettyController {

    @Resource
    private NettyServer nettyServer;

    @RequestMapping("/index")
    public String index(Model model){
        model.addAttribute("name", "xiaohao");
        return "index";
    }

}
