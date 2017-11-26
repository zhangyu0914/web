package com.yw.common.beansUtils.utils.redisUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;

/**
 *<pre>
 * 功       能: Memcache存储与读取工具类
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-9-28下午5:22:54
 * Q    Q: 308053847
 *</pre>
 */
public class MemcacheUtil {

	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);

	public static String REDIS_HOST = "";// 主机IP
	public static Integer REDIS_PORT = -1;// 端口号
	public static String REDIS_AUTH = "";// 访问密码
	public static JedisPool pool = null;
	public static boolean SESSION_TIMEOUT = false;//是否设置失效
	public static Integer SESSION_TIMEOUT_SECONDS = 0;//设置失效时间

	/**
	 * 初始化Redis
	 * @return TODO
	 */
	public static boolean init(){
		return false;
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
		return false;
	}

	public static boolean hset(String mainKey, String key, Object obj)
			throws Exception {
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
	public static Map<String, Object> getMap(String redisType, String key)throws Exception {
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
	public static <W extends Object> W getHSet(String redisType, String key) throws Exception {

		return null;
	
	}
	
	public static String getHList(String redisType, String key) {

		return null;
	
	}
	
	public static Map<String, String> getHSetAll(String key) throws Exception{

		return null;
	
	}

	public static boolean delHSet(String redisType, String key)
			throws Exception {

		return false;
	}

	public static boolean delObj(String redisType)throws Exception{

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
	public static Long getTTL(String key) throws Exception {
		return null;
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
		
	}

	/**
	 * 清空Redis所有数据
	 */
	public static void redisDestroy() {

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
		return null;
	}
}
