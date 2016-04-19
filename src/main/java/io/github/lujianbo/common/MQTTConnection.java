package io.github.lujianbo.common;

import io.github.lujianbo.gateway.protocol.MQTTProtocol;


/**
 *  代表的是一个MQTT的链接
 * */
public interface MQTTConnection {

    /**
     * write操作
     */
    public void write(MQTTProtocol message);

    public void close();

    public void onException();
}