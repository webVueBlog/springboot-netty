package org.dada.demo.netty.util;

import io.netty.channel.Channel;
import org.dada.demo.netty.domain.ServerInfo;
import org.dada.demo.netty.server.NettyServer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class CacheUtil {

    // 缓存channel
    public static Map<String, Channel> cacheChannel = Collections.synchronizedMap(new HashMap<String, Channel>());

    // 缓存服务信息
    public static Map<Integer, ServerInfo> serverInfoMap = Collections.synchronizedMap(new HashMap<Integer, ServerInfo>());

    // 缓存服务端
    public static Map<Integer, NettyServer> serverMap = Collections.synchronizedMap(new HashMap<Integer, NettyServer>());

}
