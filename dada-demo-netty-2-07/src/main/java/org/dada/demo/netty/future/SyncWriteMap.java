package org.dada.demo.netty.future;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class SyncWriteMap {

    public static Map<String, WriteFuture> syncKey = new ConcurrentHashMap<String, WriteFuture>();

}
