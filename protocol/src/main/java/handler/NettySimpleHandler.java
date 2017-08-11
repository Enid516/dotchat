package handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import simple.Message;

public class
NettySimpleHandler extends SimpleChannelInboundHandler<Message>{
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) throws Exception {
        System.out.println(message.toString());
    }
}
