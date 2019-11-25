package com.atguigu.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息消费者
 *
 * @author Ahuan
 * @version 1.0
 * @date 2019/10/23 18:39
 */
public class JMSConsumer {

    public static final String DEFAULT_BROKER_URL = "tcp://192.168.128.88:61616";
    public static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws JMSException {

        //1. 创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(DEFAULT_BROKER_URL);

        //2. 或得连接并启动
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        //3. 创建Session会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //4. 通过Session创建队列
        Queue queue = session.createQueue(QUEUE_NAME);

        //5. 通过Session创建消费者
        MessageConsumer messageConsumer = session.createConsumer(queue);

         /*同步阻塞方式receive() ，订阅者或接收者调用MessageConsumer的receive()方法来接收消息，
        receive()将一直阻塞
        receive(long timeout) 按照给定的时间阻塞，到时间自动退出*/

        TextMessage textMessage = null;
        while (true) {
            textMessage = (TextMessage) messageConsumer.receive();
            if (null != textMessage) {
                System.out.println("***接受到的消息：" + textMessage.getText());
            } else {
                break;
            }
        }
        messageConsumer.close();
        session.close();
        connection.close();
    }

}

