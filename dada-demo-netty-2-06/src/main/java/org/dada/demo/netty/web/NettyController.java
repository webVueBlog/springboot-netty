package org.dada.demo.netty.web;

import org.dada.demo.netty.server.NettyServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class NettyController {

    @Resource
    private NettyServer nettyServer;

    @RequestMapping("/localAddress")
    public String localAddress() {
        return "nettyServer localAddress " + nettyServer.getChannel().localAddress();
    }

}
