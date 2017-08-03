package com.netty.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 客户端连接封装类
 */
public class ClientConnection {
    private static final AtomicLong netIdGenerator = new AtomicLong(0);
    private long netId;
    private ChannelHandlerContext ctx;

    public static AttributeKey<Long> NETID = AttributeKey.valueOf("netid");

    public ClientConnection(ChannelHandlerContext ctx) {
        netId = netIdGenerator.incrementAndGet();
        this.ctx = ctx;
        this.ctx.attr(ClientConnection.NETID).set(netId);
    }

    public long getNetId() {
        return netId;
    }

    public void setNetId(long netId) {
        this.netId = netId;
    }

    public ChannelHandlerContext getCtx() {
        return ctx;
    }

    public void setCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }
}
