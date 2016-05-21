package io.github.lujianbo.sentinelmq.netty.handler;

import io.github.lujianbo.sentinelmq.common.protocol.*;
import io.github.lujianbo.sentinelmq.common.handler.MQTTConnection;
import io.github.lujianbo.sentinelmq.common.handler.MQTTProtocolHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * 完成MQTT和Netty部分的对接，实现数据的上下行
 */
public class MQTTServerHandler extends SimpleChannelInboundHandler<MQTTProtocol> {

    private MQTTProtocolHandler handler;

    private MQTTConnection connection;

    public MQTTServerHandler(MQTTProtocolHandler handler) {
        this.handler = handler;
    }

    /**
     * 将session注册到factory中，并且从中获得对应的handler
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        connection = new MQTTNettyConnection(ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MQTTProtocol msg) throws Exception {

        if (msg instanceof PingreqProtocol) {
            handler.onRead(connection, (PingreqProtocol) msg);
            return;
        }
        if (msg instanceof PingrespProtocol) {
            handler.onRead(connection, (PingrespProtocol) msg);
            return;
        }

        if (msg instanceof ConnectProtocol) {
            handler.onRead(connection, (ConnectProtocol) msg);
            return;
        }

        if (msg instanceof ConnackProtocol) {
            handler.onRead(connection, (ConnackProtocol) msg);
            return;
        }

        if (msg instanceof DisconnectProtocol) {
            handler.onRead(connection, (DisconnectProtocol) msg);
            return;
        }

        //处理subscribe
        if (msg instanceof SubscribeProtocol) {
            handler.onRead(connection, (SubscribeProtocol) msg);
            return;
        }

        if (msg instanceof SubackProtocol) {
            handler.onRead(connection, (SubackProtocol) msg);
            return;
        }

        //处理unSubscribe
        if (msg instanceof UnsubscribeProtocol) {
            handler.onRead(connection, (UnsubscribeProtocol) msg);
            return;
        }

        if (msg instanceof UnsubackProtocol) {
            handler.onRead(connection, (UnsubackProtocol) msg);
            return;
        }

        //处理 publish
        if (msg instanceof PublishProtocol) {
            handler.onRead(connection, (PublishProtocol) msg);
            return;
        }

        if (msg instanceof PubackProtocol) {
            handler.onRead(connection, (PubackProtocol) msg);
            return;
        }

        if (msg instanceof PubrecProtocol) {
            handler.onRead(connection, (PubrecProtocol) msg);
            return;
        }

        if (msg instanceof PubrelProtocol) {
            handler.onRead(connection, (PubrelProtocol) msg);
            return;
        }

        if (msg instanceof PubcompProtocol) {
            handler.onRead(connection, (PubcompProtocol) msg);
            return;
        }
    }

     private class MQTTNettyConnection implements MQTTConnection {

        private Channel channel;

        MQTTNettyConnection(Channel channel) {
            this.channel = channel;
        }

        @Override
        public void write(ConnectProtocol message) {
            channel.writeAndFlush(message);
        }

        @Override
        public void write(ConnackProtocol message) {
            channel.writeAndFlush(message);
        }

        @Override
        public void write(DisconnectProtocol message) {
            channel.writeAndFlush(message);
        }

        @Override
        public void write(PingreqProtocol message) {
            channel.writeAndFlush(message);
        }

        @Override
        public void write(PingrespProtocol message) {
            channel.writeAndFlush(message);
        }

        @Override
        public void write(PubcompProtocol message) {
            channel.writeAndFlush(message);
        }

        @Override
        public void write(PublishProtocol message) {
            channel.writeAndFlush(message);
        }

        @Override
        public void write(PubackProtocol message) {
            channel.writeAndFlush(message);
        }

        @Override
        public void write(PubrelProtocol message) {
            channel.writeAndFlush(message);
        }

        @Override
        public void write(PubrecProtocol message) {
            channel.writeAndFlush(message);
        }

        @Override
        public void write(SubackProtocol message) {
            channel.writeAndFlush(message);
        }

        @Override
        public void write(SubscribeProtocol message) {
            channel.writeAndFlush(message);
        }

        @Override
        public void write(UnsubscribeProtocol message) {
            channel.writeAndFlush(message);
        }

        @Override
        public void write(UnsubackProtocol message) {
            channel.writeAndFlush(message);
        }

        @Override
        public void close() {
            channel.close();
        }

        @Override
        public void onException() {
            channel.close();
        }
    }

}