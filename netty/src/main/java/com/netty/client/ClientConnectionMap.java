package com.netty.client;

import com.sun.deploy.util.SessionState;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

public class ClientConnectionMap {
    private static Logger logger = LoggerFactory.getLogger(ClientConnectionMap.class);
    public  static ConcurrentHashMap<Long,ClientConnection> allClientMap = new ConcurrentHashMap<Long, ClientConnection>();


    public static ClientConnection getClientConnection(ChannelHandlerContext ctx){
        Long netId = ctx.attr(ClientConnection.NETID).get();
        ClientConnection connection = allClientMap.get(netId);
        if (connection != null){
            return connection;
        }else{
            logger.error("ClientConnection not found in allClientConnection,netId: {}",netId);
            return null;
        }
    }

    public static void addClientConnection(ChannelHandlerContext ctx){
        ClientConnection connection = new ClientConnection(ctx);
        long netId;
        ClientConnection c = allClientMap.putIfAbsent(netId = connection.getNetId(), connection);
        if (c!=null){
            logger.error("netId: {} is existed", netId);
        }
    }

    public static void removeClientConnection(ChannelHandlerContext ctx) {
        ClientConnection connection = getClientConnection(ctx);
        if (connection != null) {
            long netId = connection.getNetId();
            ClientConnection remove = allClientMap.remove(netId);
            if (remove == null) {
                logger.error("netId: {},is not existed in allClientMap",netId);
            }
        }
    }
}
