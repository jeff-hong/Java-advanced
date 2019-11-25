package com.atguigu.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.leveldb.replicated.MasterLevelDBStore;

import javax.jms.*;

/**
 * 消息生产者
 * JMS=java message service
 *
 * @author Ahuan
 * @version 1.0
 * @date 2019/10/23 18:02
 */
public class JMSProduce {

    public static final String DEFAULT_BROKER_URL = "tcp://192.168.128.88:61616";
    public static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException {

        //1. 创建连接工厂,使用默认用户名密码,编码不在体现
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(DEFAULT_BROKER_URL);

        //2. 获得连接并启动
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        //3. 创建会话,此步骤有两个参数，第一个是事务,默认false，第二个参数是签收方式,默认自动签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //4. 通过Session创建队列
        Queue queue = session.createQueue(QUEUE_NAME);

        //5. 通过Session创建消息生产者
        MessageProducer messageProducer = session.createProducer(queue);

        for (int i = 1; i <= 3 ; i++) {
            //6. 创建发送消息
            TextMessage textMessage = session.createTextMessage("msg--" + i);
            //7. 通过消息生产者发送消息给MQ
            messageProducer.send(textMessage);
        }
        //8. 关闭资源
        messageProducer.close();
        session.close();
        connection.close();

        System.out.println("====> messageProducer send is ok,O(∩_∩)O");
    }
}
