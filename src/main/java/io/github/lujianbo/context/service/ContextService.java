package io.github.lujianbo.context.service;


/**
 * 资源服务接口
 * */
public interface ContextService {

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