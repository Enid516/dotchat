package com.netty.handler;

import com.netty.client.ClientConnection;
import com.netty.client.ClientConnectionMap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GateServerHandler extends ChannelInboundHandlerAdapter{
    private static final Logger logger = LoggerFactory.getLogger(GateServerHandler.class);
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //save client connection
        ClientConnectionMap.addClientConnection(ctx);
        logger.info("channelActive is execute");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            ByteBuf in = (ByteBuf)msg;
            byte[] bytes = new byte[in.readableBytes()];
            in.readBytes(bytes);
            String s = new String(bytes,"UTF-8");
            long netIdTo = 1;
            ClientConnection connection = ClientConnectionMap.getClientConnection(netIdTo);
            if (connection == null){
                ctx.writeAndFlush(Unpooled.copiedBuffer("消息发送失败", CharsetUtil.UTF_8));
            }else {
                ChannelHandlerContext ctxTo = connection.getCtx();
                ctxTo.writeAndFlush(Unpooled.copiedBuffer(s,CharsetUtil.UTF_8));
            }

            System.out.println("server received: " + s);

        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
