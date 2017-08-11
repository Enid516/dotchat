package code;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import simple.Header;
import simple.Message;

import java.util.List;

public class SimpleDecoder extends ByteToMessageDecoder{
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        // 获取协议的版本
        int version = byteBuf.readInt();
        // 获取消息的长度
        int contentLength = byteBuf.readInt();
        // 获取SessionId
        byte[] sessionByte = new byte[36];
        byteBuf.readBytes(sessionByte);
        String sessionId = new String(sessionByte);

        // 组装协议头
        Header header = new Header(version,contentLength,sessionId);

        // 读取消息内容
        byte[] content = byteBuf.readBytes(byteBuf.readableBytes()).array();
        Message message = new Message(header,new String(content));

        list.add(message);

    }
}
