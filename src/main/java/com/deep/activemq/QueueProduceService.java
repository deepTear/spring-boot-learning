package com.deep.activemq;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/3/2 0002.
 * desc : 定义一个点对点的消息生产者
 */
@Service
public class QueueProduceService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    //在此处去获取到定义的Bean类型的Queue，带有不同的destination值
    @Autowired
    private Queue queue;


    //向指定队列中发送消息
    public void send(String msg) {
        this.jmsMessagingTemplate.convertAndSend("sample.queue",msg);
    }

}
