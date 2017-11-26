package com.yw.common.beansUtils.utils.redisUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yw.common.beansUtils.utils.file.ConfigUtil;

import org.apache.log4j.Logger;

import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.JavaBeanUtil;

/**
 * <pre>
 * 功   能: redis存储与读取工具类   
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-7-22上午11:00:17
 * Q  Q: 308053847
 * </pre>
 */
public class RedisUtil {

	private static final Logger log = Logger.getLogger(Constants.LOG_MODEL);

	public static String REDIS_HOST = "";// 主机IP
	public static Integer REDIS_PORT = -1;// 端口号
	public static String REDIS_AUTH = "";// 访问密码
	public static JedisPool pool = null;
	public static boolean SESSION_TIMEOUT = false;//是否设置失效
	public static Integer SESSION_TIMEOUT_SECONDS = 0;//设置失效时间

	/**
	 * <pre>
	 * 说       明: 初始化Redis
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年6月20日下午1:45:52
	 * Q    Q: 308053847
	 * </pre>
	 */
	public static boolean init(){
		
		if (!StringUtils.isBlank(RedisUtil.REDIS_HOST)) {
			return true;//防止重复初始化
		}
		//判断当前系统是否配置redis环境变量，有就用环境变量里面的ip
		String host=System.getenv("IS_DOCKER");
		if(!StringUtils.isBlank(host)){
			
			RedisUtil.REDIS_HOST="yw_redis";
			RedisUtil.REDIS_PORT=6379;//内部端口，外部测试使用：6001
			RedisUtil.REDIS_AUTH = null;
		}else{
			// Redis
			RedisUtil.REDIS_HOST = ConfigUtil.get("REDIS_HOST");// REIDS服务器IP
			RedisUtil.REDIS_PORT = Integer.valueOf(ConfigUtil.get("REDIS_PORT"));// 端口号
			RedisUtil.REDIS_AUTH = ConfigUtil.get("REDIS_AUTH");
			if (ConfigUtil.get("REDIS_AUTH").equals("")) {
				RedisUtil.REDIS_AUTH = null;
			}
		}
		

		int redisMaxActive = Integer
				.valueOf(ConfigUtil.get("REDIS_MAX_ACTIVE"));// 最大jedis实例数
		int redisMaxIdle = Integer.valueOf(ConfigUtil.get("REDIS_MAX_IDLE"));// 最大idle(空闲的)的jedis实例数
		long redisMaxWait = Integer.valueOf(ConfigUtil.get("REDIS_MAX_WAIT"));// 最大等待时间
		int redisTimeout = Integer.valueOf(ConfigUtil.get("REDIS_TIMEOUT"));// 超时时间

		JedisPoolConfig config = new JedisPoolConfig();
		// 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
		// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
		config.setMaxActive(redisMaxActive);
		// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
		config.setMaxIdle(redisMaxIdle);
		// 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
		config.setMaxWait(redisMaxWait);
		// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
		config.setTestOnBorrow(true);
		RedisUtil.pool = new JedisPool(config, RedisUtil.REDIS_HOST,
				RedisUtil.REDIS_PORT, redisTimeout, RedisUtil.REDIS_AUTH);
		return true;
	}
	
	/**
	 * 初始化Redis
	 */
	public static void init(String ip,int port,String password){
		
		log.info("初始化Redis开始");

		// Redis
		RedisUtil.REDIS_HOST = ip;//REIDS服务器IP
		RedisUtil.REDIS_PORT = port;//端口号
		RedisUtil.REDIS_AUTH = password;//密码

		int redisMaxActive = 10000;//最大jedis实例数
		int redisMaxIdle = 100;//最大idle(空闲的)的jedis实例数
		long redisMaxWait = 10;//最大等待时间
		int redisTimeout = 14400;//超时时间

		JedisPoolConfig config = new JedisPoolConfig();
		// 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
		// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
		config.setMaxActive(redisMaxActive);
		// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
		config.setMaxIdle(redisMaxIdle);
		// 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
		config.setMaxWait(redisMaxWait);
		// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
		config.setTestOnBorrow(true);
		RedisUtil.pool = new JedisPool(config, RedisUtil.REDIS_HOST,RedisUtil.REDIS_PORT, redisTimeout, RedisUtil.REDIS_AUTH);

		log.info("初始化Redis结束");
	}


	public static void init(String host, String port, String auth) {
		init(host,Integer.valueOf(port),auth);
	}

	/**
	 * <pre>
	 * 说   明: 获取实例
	 * @return
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-8-29上午10:00:41
	 * </pre>
	 */
	public static JedisPool getInstance() {
		return pool;
	}

	/**
	 * <pre>
	 * 说   明:  存储任意类型数据
	 * @param redisType
	 * @param key
	 * @param timeOut 
	 * @param seconds 
	 * @param value
	 * @return
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-7-22上午11:04:27
	 * </pre>
	 */
	public static boolean set(String redisType, String key, Object obj,
			boolean timeOut, Integer seconds) throws Exception {
		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {
			key = redisType + "_" + key;
			jedisPool = RedisUtil.getInstance();
			jedis = jedisPool.getResource();

			boolean result = jedis.set(key, JSON.toJSONString(obj))
					.toUpperCase().equalsIgnoreCase("OK");
			if (timeOut && seconds != null) {
				jedis.expire(key, seconds);
			}
			return result;
		} catch (Exception e) {
			returnBrokenResource(jedisPool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(jedisPool, jedis);
		}
		return false;
	}

	/**
	 * <pre>
	 * 说   明:  添加MAP值
	 * @param redisType
	 * @param key
	 * @param map
	 * @return
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-7-22上午11:04:58
	 * </pre>
	 */
	public static boolean set(String redisType, String key, Map map)
			throws Exception {
		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {
			key = redisType + "_" + key;
			jedisPool = RedisUtil.getInstance();
			jedis = jedisPool.getResource();
			boolean result = jedis.hmset(key, map).equalsIgnoreCase("OK");
			return result;
		} catch (Exception e) {
			returnBrokenResource(jedisPool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(jedisPool, jedis);
		}
		return false;
	}

	/**
	 * <pre>
	 * 说   明:  存储LIST 
	 * @param redisType
	 * @param key
	 * @param list
	 * @return
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-7-23下午2:00:34
	 * </pre>
	 */
	public static <W extends List> boolean set(String redisType, String key,
			W list) throws Exception {
		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {
			if (key == null || key.equals("") || list == null || list.isEmpty()) {
				return false;
			}
			key = redisType + "_" + key;
			jedisPool = RedisUtil.getInstance();
			jedis = jedisPool.getResource();
			for (Object obj : list) {
				jedis.lpush(key, JSON.toJSONString(obj));
			}
			return true;
		} catch (Exception e) {
			returnBrokenResource(jedisPool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(jedisPool, jedis);
		}
		return false;
	}

	/**
	 * <pre>
	 * 说   明:  存储SET
	 * @param redisType
	 * @param key
	 * @param set
	 * @return
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-7-23下午2:58:26
	 * </pre>
	 */
	public static boolean set(String redisType, String key, Set set)
			throws Exception {
		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {
			if (key == null || key.equals("") || set == null || set.isEmpty()) {
				return false;
			}
			key = redisType + "_" + key;
			jedisPool = RedisUtil.getInstance();
			jedis = jedisPool.getResource();
			for (Object obj : set) {
				jedis.sadd(key, JSON.toJSONString(obj));
			}
			return true;
		} catch (Exception e) {
			returnBrokenResource(jedisPool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(jedisPool, jedis);
		}
		return false;
	}

	public static boolean hset(String mainKey, String key, Object obj)
			throws Exception {
		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {
			if (mainKey == null || mainKey.equals("") || key == null
					|| key.equals("") || obj == null) {
				return false;
			}
			jedisPool = RedisUtil.getInstance();
			jedis = jedisPool.getResource();
			jedis.hset(mainKey, key, JSON.toJSONString(obj));
			return true;
		} catch (Exception e) {
			returnBrokenResource(jedisPool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(jedisPool, jedis);
		}
		return false;
	}

	/**
	 * <pre>
	 * 说   明:  获取任意对象类型数据
	 * @param redisType
	 * @param key
	 * @return
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-8-28下午7:54:43
	 * </pre>
	 */
	public static String getObject(String redisType, String key) throws Exception {
		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {
			if (key == null || key.equals("")) {
				return null;
			}
			key = redisType + "_" + key;
			jedisPool = RedisUtil.getInstance();
			jedis = jedisPool.getResource();
			return jedis.get(key);
		} catch (Exception e) {
			returnBrokenResource(jedisPool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(jedisPool, jedis);
		}
		return null;
	}
	

/**
	 * <pre>
	 * 说   明:  获取任意对象类型数据
	 * @param redisType
	 * @param key
	 * @param cls
	 * @return
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-8-28下午7:54:43
	 * </pre>
	 */
	public static <W extends Object> W getObject(String key,Class cls) throws Exception {
		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {
			if (key == null || key.equals("")) {
				return null;
			}
			if (cls == null) {
				cls = Object.class;
			}
			jedisPool = RedisUtil.getInstance();
			jedis = jedisPool.getResource();
			String data = jedis.get(key);
			if (data == null || data.equals("")) {
				return null;
			}
			return (W) JSON.parseObject(data, cls);
		} catch (Exception e) {
			returnBrokenResource(jedisPool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(jedisPool, jedis);
		}
		return null;
	}


	/**
	 * <pre>
	 * 说   明:  获取MAP值
	 * @param redisType
	 * @param key
	 * @return
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-7-22上午11:40:56
	 * </pre>
	 */
	public static Map<String, Object> getMap(String redisType, String key)
			throws Exception {
		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {
			if (key == null || key.equals("")) {
				return null;
			}
			key = redisType + "_" + key;
			jedisPool = RedisUtil.getInstance();
			jedis = jedisPool.getResource();
			Set<String> keySet = jedis.hkeys(key);
			if (keySet == null || keySet.isEmpty()) {
				return null;
			}
			Map<String, Object> data = new HashMap<String, Object>();
			String tempKey = null;
			List tempList = null;
			for (Iterator<String> iterator = keySet.iterator(); iterator
					.hasNext();) {
				tempKey = iterator.next();
				tempList = jedis.hmget(key, tempKey);
				data.put(tempKey, tempList != null ? tempList.get(0) : null);
			}
			return data;
		} catch (Exception e) {
			returnBrokenResource(jedisPool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(jedisPool, jedis);
		}
		return null;
	}

	/**
	 * <pre>
	 * 说   明:  获取LIST类型数据
	 * @param redisType
	 * @param key
	 * @return
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-8-28下午7:54:28
	 * </pre>
	 */
	public static List getList(String redisType, String key)
			throws Exception {
		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {
			if (key == null || key.equals("")) {
				return null;
			}
			key = redisType + "_" + key;
			jedisPool = RedisUtil.getInstance();
			jedis = jedisPool.getResource();
			List<String> list = jedis.lrange(key, 0, -1);
			if (list == null || list.isEmpty()) {
				return null;
			}
			List<Object> data = new ArrayList<Object>();
			for (String str : list) {
				data.add(str);
			}
			return data;
		} catch (Exception e) {
			returnBrokenResource(jedisPool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(jedisPool, jedis);
		}
		return null;
	}

	/**
	 * <pre>
	 * 说   明:  获取SET类型数据 
	 * @param redisType
	 * @param key
	 * @return
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-8-28下午7:54:11
	 * </pre>
	 */
	public static Set getSet(String redisType, String key)
			throws Exception {
		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {
			if (key == null || key.equals("")) {
				return null;
			}
			key = redisType + "_" + key;
			jedisPool = RedisUtil.getInstance();
			jedis = jedisPool.getResource();
			Set<String> set = jedis.smembers(key);
			if (set == null || set.isEmpty()) {
				return null;
			}
			Set<Object> data = new HashSet<Object>();
			for (String str : set) {
				data.add(str);
			}
			return data;
		} catch (Exception e) {
			returnBrokenResource(jedisPool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(jedisPool, jedis);
		}
		return null;
	}

	/**
	 * <pre>
	 * 说   明:  获取HSET值
	 * @param redisType
	 * @param key
	 * @return
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-9-16上午9:46:25
	 * </pre>
	 */
	public static String getHSet(String redisType, String key) throws Exception {
		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {
			if (redisType == null || redisType.equals("") || key == null
					|| key.equals("")) {
				return null;
			}
			jedisPool = RedisUtil.getInstance();
			jedis = jedisPool.getResource();
			return jedis.hget(redisType, key);
		} catch (Exception e) {
			returnBrokenResource(jedisPool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(jedisPool, jedis);
		}
		return null;
	}
	
	public static String getHList(String redisType, String key) {
		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {
			if (redisType == null || redisType.equals("")
					|| key == null || key.equals("")) {
				return null;
			}
			jedisPool = RedisUtil.getInstance();
			jedis = jedisPool.getResource();
			return jedis.hget(redisType, key);
		} catch (Exception e) {
			returnBrokenResource(jedisPool, jedis);
			e.printStackTrace();
		}finally{
			returnResource(jedisPool, jedis);
		}
		return null;
	}
	
	public static Map<String, String> getHSetAll(String key) throws Exception{
        JedisPool jedisPool = null;
        Jedis jedis = null;
        try {
            if (key == null || key.equals("")) {
				return null;
			}
            jedisPool = RedisUtil.getInstance();
            jedis = jedisPool.getResource();
            return jedis.hgetAll(key);
        } catch (Exception e) {
            returnBrokenResource(jedisPool, jedis);
            e.printStackTrace();
        }finally{
            returnResource(jedisPool, jedis);
        }
        return null;
    }

	public static boolean delHSet(String redisType, String key)
			throws Exception {
		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {
			if (redisType == null || redisType.equals("") || key == null
					|| key.equals("")) {
				return false;
			}
			jedisPool = RedisUtil.getInstance();
			jedis = jedisPool.getResource();
			return jedis.hdel(redisType, key) > 0;
		} catch (Exception e) {
			returnBrokenResource(jedisPool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(jedisPool, jedis);
		}
		return false;
	}

	public static boolean delObj(String redisType)throws Exception{
        JedisPool jedisPool = null;
        Jedis jedis = null;
        try {
            jedisPool =  RedisUtil.getInstance();
            jedis = jedisPool.getResource();
            return jedis.del(redisType) > 0 ? true : false;
        } catch (Exception e) {
            jedisPool.returnBrokenResource(jedis);
            e.printStackTrace();
        }finally{
            returnResource(jedisPool, jedis);
        }
        return false;
    }
	
	/**
	 * <pre>
	 * 说   明:  删除指定KEY
	 * @param redisType
	 * @param key
	 * @return
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-7-24下午2:14:45
	 * </pre>
	 */
	public static boolean delObj(String redisType, String key) throws Exception {
		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {
			if (key == null || key.equals("")) {
				return false;
			}
			key = redisType + "_" + key;
			jedisPool = RedisUtil.getInstance();
			jedis = jedisPool.getResource();
			return jedis.del(key) > 0 ? true : false;
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(jedisPool, jedis);
		}
		return false;
	}

	/**
	 * <pre>
	 * 说   明:  设置超时
	 * @param redisType
	 * @param key
	 * @param seconds
	 * @return
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-7-24下午5:57:20
	 * </pre>
	 */
	public static boolean expire(String redisType, String key, int seconds)
			throws Exception {
		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {
			key = redisType + "_" + key;
			jedisPool = RedisUtil.getInstance();
			jedis = jedisPool.getResource();
			return jedis.expire(key, seconds) > 0 ? true : false;
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(jedisPool, jedis);
		}
		return false;
	}

	/**
	 * <pre>
	 * 说 明: 获取还剩多长时间过期 
	 * @param key
	 * @return
	 * 创建者: 陈  宏(Luke)
	 * 日 期: 2014-7-30下午6:06:49
	 * </pre>
	 */
	public static long getTTL(String key) throws Exception {
		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {
			jedisPool = RedisUtil.getInstance();
			jedis = jedisPool.getResource();
			if (jedis.exists(key)) {
				return jedis.ttl(key);
			}
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(jedisPool, jedis);
		}
		return -2;
	}

	/**
	 * <pre>
	 * 说   明:  获取VALUE类型
	 * @param key
	 * @return
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-8-25下午5:29:34
	 * </pre>
	 */
	public static String getValueType(String key) throws Exception {
		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {
			jedisPool = RedisUtil.getInstance();
			jedis = jedisPool.getResource();
			String str = jedis.type(key);
			return str;
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(jedisPool, jedis);
		}
		return null;
	}

	/**
	 * <pre>
	 * 说   明:  获取当前Redis中所有KEY
	 * @param key
	 * @return
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-8-25下午2:16:31
	 * </pre>
	 */
	public static Set<String> getAllKeys(String key) throws Exception {
		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {
			jedisPool = RedisUtil.getInstance();
			jedis = jedisPool.getResource();
			Set<String> set = jedis.keys(key);
			return set;
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(jedisPool, jedis);
		}
		return null;
	}
	
	/**
	 * 判断Hash中Key是否存在
	 * 
	 * @param mainKey
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static boolean hExists(String mainKey, String key) throws Exception {

		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {

			if (StringUtils.isBlank(mainKey) || StringUtils.isBlank(key))
				return false;

			jedisPool = RedisUtil.getInstance();
			jedis = jedisPool.getResource();
			return jedis.hexists(mainKey, key);
		} catch (Exception e) {
			jedisPool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(jedisPool, jedis);
		}
		return false;

	}

	public static boolean hset(String key1, String key2, byte[] data) throws Exception {
		return hset(key1, key2, data, 0);
	}

	public static boolean hset(String key1, String key2, byte[] data, int dbIndex) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.select(dbIndex);
			boolean result = jedis.hset(key1.getBytes(), key2.getBytes(), data) > 0;
			return result;
		} catch (Exception e) {
			returnBrokenResource(pool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(pool, jedis);
		}
		return false;
	}

	public static boolean set(String key, byte[] data, boolean timeOut, Integer seconds) throws Exception {
		return set(key, data, timeOut,seconds, 0);
	}

	public static boolean set(String key, byte[] data, boolean timeOut, Integer seconds, int dbIndex) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.select(dbIndex);

			boolean result = jedis.set(key.getBytes(), data).toUpperCase().equalsIgnoreCase("OK");
			if (timeOut && seconds != null) {
				jedis.expire(key, seconds);
			}
			return result;
		} catch (Exception e) {
			returnBrokenResource(pool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(pool, jedis);
		}
		return false;
	}

	public static byte[] hget(String key1, String key2) throws Exception {
		return hget(key1, key2, 0);
	}


	public static Long llen(String key1) throws Exception {
		return llen(key1, 0);
	}

	public static Long llen(String key1, int dbIndex) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.select(dbIndex);
			Long data = jedis.llen(key1);
			if (data == null || data.equals("")) {
				return null;
			}
			return data;
		} catch (Exception e) {
			returnBrokenResource(pool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(pool, jedis);
		}
		return null;
	}

	public static Long increment(String key1) throws Exception {
		return increment(key1, 0);
	}

	public static Long increment(String key1, int dbIndex) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.select(dbIndex);
			Long data = jedis.incr(key1);
			if (data == null || data.equals("")) {
				return null;
			}
			return data;
		} catch (Exception e) {
			returnBrokenResource(pool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(pool, jedis);
		}
		return null;
	}

	public static String lpop(String key1, long index, int dbIndex) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.select(dbIndex);
			String data = jedis.lindex(key1, index);
			if (data == null || data.equals("")) {
				return null;
			}
			return data;
		} catch (Exception e) {
			returnBrokenResource(pool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(pool, jedis);
		}
		return null;
	}
	
	/**
	 * <pre>
	 * 说       明: 从最右边POP数据
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年9月8日上午9:16:22
	 * Q    Q: 308053847
	 * </pre>
	 */
	public static List brpop(String key) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			List data = jedis.brpop(10, key);
			if (data == null || data.isEmpty()) {
				return null;
			}
			List list = data.subList(1, data.size());//去掉第1个KEY
			return list;
		} catch (Exception e) {
			returnBrokenResource(pool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(pool, jedis);
		}
		return null;
	}

	public static String lindex(String key1, long index) throws Exception {
		return lindex(key1, index, 0);
	}

	public static String lindex(String key1, long index, int dbIndex) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.select(dbIndex);
			String data = jedis.lindex(key1, index);
			if (data == null || data.equals("")) {
				return null;
			}
			return data;
		} catch (Exception e) {
			returnBrokenResource(pool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(pool, jedis);
		}
		return null;
	}

	public static Long lpush(String key1,  String... values) throws Exception {
		return lpush(key1, 0, values);
	}

	public static Long lpush(String key1, int dbIndex, String... values) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.select(dbIndex);
			Long data = jedis.lpush(key1, values);
			if (data == null || data.equals("")) {
				return null;
			}
			return data;
		} catch (Exception e) {
			returnBrokenResource(pool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(pool, jedis);
		}
		return null;
	}

	public static Map<String, String> hgetAll(String key1) throws Exception {
		return hgetAll(key1,0);
	}

	public static Map<String, String> hgetAll(String key1, int dbIndex) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.select(dbIndex);
			Map<String, String> data = jedis.hgetAll(key1);
			if (data == null || data.equals("")) {
				return null;
			}
			return data;
		} catch (Exception e) {
			returnBrokenResource(pool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(pool, jedis);
		}
		return null;
	}
	public static byte[] hget(String key1, String key2, int dbIndex) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.select(dbIndex);
			byte[] data = jedis.hget(key1.getBytes(), key2.getBytes());
			if (data == null || data.equals("")) {
				return null;
			}
			return data;
		} catch (Exception e) {
			returnBrokenResource(pool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(pool, jedis);
		}
		return null;
	}

	public static byte[] get(String key) throws Exception {
		return get(key, 0);
	}

	public static byte[] get(String key, int dbIndex) throws Exception {
		Jedis jedis = null;
		try {
			if (key == null || key.equals("")) {
				return null;
			}
			jedis = pool.getResource();
			jedis.select(dbIndex);
			byte[] data = jedis.get(key.getBytes());
			if (data == null || data.equals("")) {
				return null;
			}
			return data;
		} catch (Exception e) {
			returnBrokenResource(pool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(pool, jedis);
		}
		return null;
	}

	public static List<byte[]> random(int size) throws Exception {
		return random(size, 0);
	}

	public static List<byte[]> random(int size, int dbIndex) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.select(dbIndex);
			LinkedList<byte[]> list = new LinkedList<byte[]>();
			for (int i = 0; i < size; i++) {
				byte[] key = jedis.randomBinaryKey();
				if(key != null){
					byte[] data = jedis.get(key);
					if (data != null) {
						list.add(data);
					}
				}
			}
			return list;
		} catch (Exception e) {
			returnBrokenResource(pool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(pool, jedis);
		}
		return null;
	}

	public static boolean hdel(String key1, String key2) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.hdel(key1, key2) > 0;
		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(pool, jedis);
		}
		return false;
	}

	public static boolean del(String key) throws Exception {
		return del(key,0);
	}
	public static boolean del(String key, int dbIndex) throws Exception {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.select(dbIndex);
			return jedis.del(key) > 0;
		} catch (Exception e) {
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			returnResource(pool, jedis);
		}
		return false;
	}

	/**
	 * <pre>
	 * 说   明:  一旦拿到的jedis实例使用完毕，必须要返还给池中
	 * @param pool
	 * @param jedis
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-8-28下午7:53:00
	 * </pre>
	 */
	public static void returnResource(JedisPool pool, Jedis jedis) {
		try {
			// 这里很重要，一旦拿到的jedis实例使用完毕，必须要返还给池中
			if (pool != null && jedis != null) {
				pool.returnResource(jedis);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * <pre>
	 * 说   明:  释放redis对象
	 * @param pool
	 * @param jedis
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-8-28下午7:52:50
	 * </pre>
	 */
	public static void returnBrokenResource(JedisPool pool, Jedis jedis) {
		try {
			if (pool != null && jedis != null) {
				pool.returnBrokenResource(jedis);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 清空Redis所有数据
	 */
	public static void redisDestroy() {
		log.info("DESTORY START[Redis]");
		// RedisUtil.getInstance().flushAll();//清空REDIS所有数据，由于还在测试，暂时屏蔽
		RedisUtil.pool.destroy();
		log.info("DESTORY END[Redis]");
	}
	
	/**
	 *<pre>
	 * 说   明: 原子递增操作，
	 * @param redisType
	 * @param key
	 * @param timeOut
	 * @param seconds
	 * @return
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-7-22上午11:04:27
	 *</pre>
	 */
	public static Long incr(String redisType, String key, boolean timeOut, Integer seconds)  throws Exception{
		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {
			key = redisType + "_" + key;
			jedisPool = RedisUtil.getInstance();
			jedis = jedisPool.getResource();
			if (timeOut && seconds != null) {
				jedis.expire(key, seconds);
			}
			Long result = jedis.incr(key);

			return result;
		} catch (Exception e) {
			returnBrokenResource(jedisPool, jedis);
			e.printStackTrace();
		}finally{
			returnResource(jedisPool, jedis);
		}
		return 0l;
	}
	
	/**
	 * <pre>
	 * 说       明: rpush
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月24日下午2:09:08
	 * Q    Q: 308053847
	 * </pre>
	 */
	public static <W extends List> boolean rpush(String redisType, String key,
			W list) throws Exception {
		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {
			if (key == null || key.equals("") || list == null || list.isEmpty()) {
				return false;
			}
			key = redisType + "_" + key;
			jedisPool = RedisUtil.getInstance();
			jedis = jedisPool.getResource();
			for (Object obj : list) {
				jedis.rpush(key, JSON.toJSONString(obj));
			}
			return true;
		} catch (Exception e) {
			returnBrokenResource(jedisPool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(jedisPool, jedis);
		}
		return false;
	}
	
	/**
	 * <pre>
	 * 说       明: LPUSH
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月24日下午2:08:59
	 * Q    Q: 308053847
	 * </pre>
	 */
	public static <W extends Object> boolean lpush(String redisType, String key,
			W obj) throws Exception {
		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {
			if (key == null || key.equals("") || obj == null) {
				return false;
			}
			key = redisType + "_" + key;
			jedisPool = RedisUtil.getInstance();
			jedis = jedisPool.getResource();
			if (jedis.lpush(key, JSON.toJSONString(obj)) > 0) {
				return true;
			}
		} catch (Exception e) {
			returnBrokenResource(jedisPool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(jedisPool, jedis);
		}
		return false;
	}
	
	/**
	 * <pre>
	 * 说       明: LPUSH
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月24日下午2:08:59
	 * Q    Q: 308053847
	 * </pre>
	 */
	public static <W extends Object> boolean lpush(String key,
			W obj) throws Exception {
		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {
			if (key == null || key.equals("") || obj == null) {
				return false;
			}
			jedisPool = RedisUtil.getInstance();
			jedis = jedisPool.getResource();
			jedis.lpush(key, JSON.toJSONString(obj));
			return true;
		} catch (Exception e) {
			returnBrokenResource(jedisPool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(jedisPool, jedis);
		}
		return false;
	}
	/**
	 * <pre>
	 * 说       明: LPUSH
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年5月24日下午2:08:59
	 * Q    Q: 308053847
	 * </pre>
	 */
	public static <W extends List> boolean lpushList(String redisType, String key,
			W list) throws Exception {
		JedisPool jedisPool = null;
		Jedis jedis = null;
		try {
			if (key == null || key.equals("") || list == null || list.isEmpty()) {
				return false;
			}
			key = redisType + "_" + key;
			jedisPool = RedisUtil.getInstance();
			jedis = jedisPool.getResource();
			for (Object obj : list) {
				jedis.lpush(key, JSON.toJSONString(obj));
			}
			return true;
		} catch (Exception e) {
			returnBrokenResource(jedisPool, jedis);
			e.printStackTrace();
		} finally {
			returnResource(jedisPool, jedis);
		}
		return false;
	}

	public static void main(String[] args) {
		try {// Redis
			RedisUtil.REDIS_HOST = "192.168.10.166";
			RedisUtil.REDIS_PORT = 6001;
			RedisUtil.REDIS_AUTH = null;

			JedisPoolConfig config = new JedisPoolConfig();
			// 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
			// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
			config.setMaxActive(10000);
			// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
			config.setMaxIdle(100);
			// 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
			config.setMaxWait(1000 * 10);
			// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
			config.setTestOnBorrow(true);
			// RedisUtil.pool = new JedisPool(config,
			// RedisUtil.REDIS_HOST,RedisUtil.REDIS_PORT,
			// Integer.valueOf(dictionaryMap.get(DictionaryConstants.TYPE_KEY_REDIS).get(DictionaryConstants.DATA_KEY_REDIS_KEY_MAX_WAIT)),
			// RedisUtil.REDIS_AUTH);

			RedisUtil.pool = new JedisPool(config, RedisUtil.REDIS_HOST,
					RedisUtil.REDIS_PORT, 200, RedisUtil.REDIS_AUTH);
			// RedisUtil.hset(RedisTypeEnum.ONLINES.getCode(), "1", new
			// Object());
			// RedisUtil.getHSet((RedisTypeEnum.ONLINES.getCode(), "1", new
			// Object());
			Object obj = RedisUtil.getHSet("user_list","h3cuser");
			System.out.println(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
