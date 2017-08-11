package code;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import simple.Header;
import simple.Message;

public class SimpleEncoder extends MessageToByteEncoder<Message>{

    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf byteBuf) throws Exception {
        // 将Message 转换成二进制数据
        Header header = message.getHeader();

        //按协议规定的顺序写入

        //写入Header信息
        byteBuf.writeInt(header.getVersion());
        byteBuf.writeInt(header.getContentLength());
        byteBuf.writeBytes(header.getSessionId().getBytes());

        //写入消息主体信息
        byteBuf.writeBytes(message.getContent().getBytes());
    }
}
