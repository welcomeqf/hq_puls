package com.gpdi.hqplus.user.api;


import com.gpdi.hqplus.common.util.ExpirationMessagePostProcessor;
import com.gpdi.hqplus.base.listener.ProcessReceiver;
import com.gpdi.hqplus.base.ttl.RabbitDelayQueue;
import com.gpdi.hqplus.common.manager.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseApiTest {

    @Autowired
    private RedisService redisService;
//    @Autowired
//    private DelayedSender sender;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void hello() {
        boolean v1 = redisService.set("v1", "lianghb");
        System.out.println(v1);

        Object v11 = redisService.get("v1");
        System.out.println(v11);
    }

    @Test
    public void send() {

    }



    @Test
    public void testDelayQueuePerMessageTTL() throws InterruptedException {
        ProcessReceiver.latch = new CountDownLatch(3);
        for (int i = 1; i <= 3; i++) {
            long expiration = i * 1000;
            rabbitTemplate.convertAndSend(RabbitDelayQueue.DELAY_QUEUE_PER_MESSAGE_TTL_NAME,
                    (Object) ("Message From delay_queue_per_message_ttl with expiration " + expiration), new ExpirationMessagePostProcessor(expiration));
        }
        ProcessReceiver.latch.await();
    }

    @Test
    public void testDelayQueuePerQueueTTL() throws InterruptedException {
        ProcessReceiver.latch = new CountDownLatch(3);
        for (int i = 1; i <= 3; i++) {
            rabbitTemplate.convertAndSend(RabbitDelayQueue.DELAY_QUEUE_PER_QUEUE_TTL_NAME,
                    "Message From delay_queue_per_queue_ttl with expiration " + RabbitDelayQueue.QUEUE_EXPIRATION);
        }
        ProcessReceiver.latch.await();
    }

    @Test
    public void testFailMessage() throws InterruptedException {
        ProcessReceiver.latch = new CountDownLatch(6);
        for (int i = 1; i <= 3; i++) {
            rabbitTemplate.convertAndSend(RabbitDelayQueue.DELAY_PROCESS_QUEUE_NAME, ProcessReceiver.FAIL_MESSAGE);
        }
        ProcessReceiver.latch.await();
    }



}