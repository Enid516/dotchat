package starter;

import code.SimpleDecoder;
import code.SimpleEncoder;
import handler.NettySimpleHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


public class NettySimpleInitializer extends ChannelInitializer<SocketChannel>{

    private static final SimpleEncoder ENCODER = new SimpleEncoder();

    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        // 添加编码器，由于ByteToMessageDecoder的子类无法使用@Shareable注解，
        // 这里必须给每个Handler都添加一个独立的Decoder.
        pipeline.addLast(ENCODER);
        pipeline.addLast(new SimpleDecoder());

        // 添加逻辑控制层
        pipeline.addLast(new NettySimpleHandler());

    }
}
