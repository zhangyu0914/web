package com.yw.common.beansUtils.utils.redisUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * <pre>
 * 功       能: 推送
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017年6月29日上午11:45:44
 * Q    Q: 308053847
 * </pre>
 */
public class Publisher {
    private final JedisPool jedisPool;

    public Publisher(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public void start() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Jedis jedis = jedisPool.getResource();
        while (true) {
            String line = null;
            try {
                line = reader.readLine();
                if (!"quit".equals(line)) {
                    jedis.publish("mychannel", line);
                } else {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
         }
    }
}