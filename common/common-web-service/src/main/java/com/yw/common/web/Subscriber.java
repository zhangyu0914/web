package com.yw.common.web;

import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;

import redis.clients.jedis.JedisPubSub;

/**
 * <pre>
 * 功       能: REDIS订阅
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017年6月29日上午11:45:53
 * Q    Q: 308053847
 * </pre>
 */
public class Subscriber extends JedisPubSub {
	
	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);
	public Subscriber() {
	}

	@Override
	public void onMessage(String channel, String message) {
		log.info("[REDIS订阅]"+channel+"内容有修改:" + message);
	}

	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		log.info(String.format("subscribe redis channel success, channel %s, subscribedChannels %d",
								channel, subscribedChannels));
	}

	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
		log.info(String.format(
				"unsubscribe redis channel, channel %s, subscribedChannels %d",
				channel, subscribedChannels));

	}

	@Override
	public void onPMessage(String pattern, String channel, String message) {
		log.info(String.format(
				"receive redis published message, channel %s, message %s",
				channel, message));

	}

	@Override
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
		log.info(String.format("subscribe redis channel success, channel %s, subscribedChannels %d",
								pattern, subscribedChannels));

	}

	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {
		log.info(String.format(
				"unsubscribe redis channel, channel %s, subscribedChannels %d",
				pattern, subscribedChannels));

	}
}