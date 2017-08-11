package starter;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettySimpleServer {
    /** 指定端口号*/
    private static final int PORT = 8888;

    public static void main(String[] args) throws InterruptedException{
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            //指定socket的一些属性
            serverBootstrap.option(ChannelOption.SO_BACKLOG,1024);
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new NettySimpleInitializer());

            // 绑定对应端口号，并启动开始监听端口上的连接
            Channel channel = serverBootstrap.bind(PORT).sync().channel();
            System.out.printf("simple协议启动地址： 127.0.0.1:%d",PORT);

            // 等待关闭，同步接口
            channel.closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
