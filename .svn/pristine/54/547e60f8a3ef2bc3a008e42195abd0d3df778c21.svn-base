package com.gpdi.hqplus.message.service.impl;

import com.gpdi.hqplus.common.constants.RedisConstants;
import com.gpdi.hqplus.message.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceImplTest {
    @Autowired
    MessageService messageService;

    @Test
    public void sendMessage() {
        messageService.sendMessage(RedisConstants.SMS_KEY_REGISTER,"17728041510");
    }
}