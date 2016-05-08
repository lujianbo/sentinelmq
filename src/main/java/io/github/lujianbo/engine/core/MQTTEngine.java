package io.github.lujianbo.engine.core;

import io.github.lujianbo.context.service.ContextService;
import io.github.lujianbo.engine.common.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class MQTTEngine {

    private final ContextService contextService;

    /**
     * MQTT engine上的监听者
     */
    private Set<MQTTEngineListener> listeners = new HashSet<>();

    /**
     * 执行广播任务的线程池
     * */
    private ExecutorService pool = Executors.newCachedThreadPool(runnable -> {
        Thread thread = new Thread();
        thread.setDaemon(true);
        return thread;
    });

    public MQTTEngine(ContextService contextService) {
        this.contextService = contextService;
    }

    public void addListener(MQTTEngineListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(MQTTEngineListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * 推送信息
     */
    public void publish(PublishMessage publishMessage) {
        pool.submit(() -> {
            Iterator<String> subscribers = contextService.findSubscriber(publishMessage.getTopic());
            if (subscribers != null) {
                BroadcastMessage broadcastMessage = new BroadcastMessage();
                subscribers.forEachRemaining(s -> {
                    broadcastMessage.getClientIds().add(s);
                });
                broadcastMessage.setPayload(publishMessage.getPlayLoad());
                this.listeners.forEach(mqttEngineListener -> mqttEngineListener.broadcast(broadcastMessage));
            }
        });
    }


    /**
     * 为clientId 订阅topicName的信息
     * 返回订阅的结果
     */
    public byte subscribe(String clientId, String topicName, byte qos) {
        contextService.subscribe(clientId, topicName, qos);
        return 0;
    }

    /**
     * 不管成功失败，都没有返回值
     */
    public void unSubscribe(String clientId, String topicName) {
        contextService.unSubscribe(clientId, topicName);
    }

    public boolean auth(String clientId, String username, byte[] password) {
        return true;
    }

    public void disconnect(String clientId) {
        contextService.clear(clientId);
    }
}