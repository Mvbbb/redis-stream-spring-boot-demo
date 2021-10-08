package com.test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MsgHandler {

    @Resource
    RedisStreamManager redisStreamManager;
    private Logger logger = LoggerFactory.getLogger(MsgHandler.class);

    public void sendSingleMsg(MsgData msgData, boolean fromGroup) {
        redisStreamManager.produce(userWsServer, msgData);
    }
}
}
