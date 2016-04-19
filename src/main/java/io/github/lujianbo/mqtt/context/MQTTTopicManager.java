package io.github.lujianbo.mqtt.context;

/**
 * Topic管理，
 * 根据规范,Topic利用 /xx/xx  的方式进行描述
 */
public interface MQTTTopicManager {

    /**
     * 订阅
     * */
    public boolean subscribe(String clientId,String topicFilter);

    /**
     * 反订阅
     * */
    public boolean unSubscribe(String clientId,String topicFilter);

    /**
     *  返回topic下的订阅者的迭代器
     * */
    public Iterable<String> findSubscriber(String topicFilter);

}