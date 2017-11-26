package com.yw.common.web;

import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * <pre>
 * 功       能: REDIS订阅线程
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017年6月29日下午1:14:19
 * Q    Q: 308053847
 * </pre>
 */
public class SubThread extends Thread {
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	
    private final JedisPool jedisPool;
    private Subscriber subscriber = null;
    private String channel;

    public Subscriber getInstance(){
    	if (subscriber == null) {
    		subscriber = new Subscriber();
		}
    	return subscriber;
    }

    public SubThread(JedisPool jedisPool, String channel) {
        
    	super("SubThread");
        this.jedisPool = jedisPool;
        this.channel = channel;
    }

    @Override
    public void run() {
    	
    	log.info(String.format("subscribe redis, channel %s, thread will be blocked", channel));
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.subscribe(getInstance(), channel);
        } catch (Exception e) {
            log.info(String.format("subsrcibe channel error, %s", e));
        } finally {
            if (jedis != null) {
                jedis.disconnect();
            }
        }
    }
}