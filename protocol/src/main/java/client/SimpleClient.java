package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import simple.Header;
import simple.Message;
import starter.NettySimpleInitializer;

import java.io.IOException;
import java.net.Socket;
import java.util.UUID;

public class SimpleClient {
    public static void main(String args[]) throws InterruptedException, IOException {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new NettySimpleInitializer());

            // Start the connection attempt.
            ChannelFuture channelFuture = b.connect("127.0.0.1", 8888).sync();
            channelFuture.addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        System.out.println("client connected");
                    } else {
                        System.out.println("server attemp failed");
                        channelFuture.cause().printStackTrace();
                    }
                }
            });
            Channel ch = channelFuture.channel();

            int version = 1;
            String sessionId = UUID.randomUUID().toString();
            String content = "I'm the luck protocol!";

            Header header = new Header(version, content.length(), sessionId);
            Message message = new Message(header, content);
            ch.writeAndFlush(message);

            ch.close();

        } finally {
            group.shutdownGracefully().sync();
        }
    }

}
