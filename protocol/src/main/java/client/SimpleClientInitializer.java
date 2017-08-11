package client;

import code.SimpleDecoder;
import code.SimpleEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class SimpleClientInitializer extends ChannelInitializer<SocketChannel>{
    private static final SimpleEncoder ENCODER = new SimpleEncoder();
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(ENCODER);
        pipeline.addLast(new SimpleDecoder());
        pipeline.addLast(new SimpleClientHandler());
    }
}
