package com.test.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.stream.Record;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RedisStreamManager {

    private static final Logger logger = LoggerFactory.getLogger(RedisStreamManager.class);

    @Resource
    StringRedisTemplate stringRedisTemplate;

    /**
     * 将消息投放到 ws server 的消息队列中
     *
     * @param route   ws server 的路由
     * @param msgData 消息
     */
    public void produce(WsServerRoute route, MsgData msgData) {
        String key = RedisConstant.STREAM_DELIVER_WS_PREFIX + route.getIp() + ":" + route.getPort();
        ObjectRecord<String, MsgData> record = Record.of(msgData).withStreamKey(key);
        RecordId recordId = stringRedisTemplate.opsForStream().add(record);
    }
}