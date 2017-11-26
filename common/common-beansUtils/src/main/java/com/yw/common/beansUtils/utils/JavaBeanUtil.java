package com.yw.common.beansUtils.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 *<pre>
 * 功       能: JavaBean 与 MAP 互转
 * 			当把Person类作为BeanUtilTest的内部类时，程序出错<br>
 * 			java.lang.NoSuchMethodException: Property '**' has no setter method<br>
 * 			本质：内部类 和 单独文件中的类的区别 <br>
 * 			BeanUtils.populate方法的限制：<br>
 * 			The class must be public, and provide a public constructor that accepts no arguments. <br>
 * 			This allows tools and applications to dynamically create new instances of your bean, <br>
 *			without necessarily knowing what Java class name will be used ahead of time
 * 涉及版本: V1.0.0 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-6-30下午4:35:06
 * Q    Q: 308053847
 *</pre>
 */
public class JavaBeanUtil {

	private static Logger log = Logger.getLogger(JavaBeanUtil.class);
	
	public static void main(String[] args) {
		try {
			String json = "{ \"DevID\":\"8001000000010203\", \"AP\":\"0000050300000000\", \"IP\":\"192.168.10.88:49175\", \"HearBeat\":34,\"Battery\":96, \"MsgName\":\"HeartBeat\"}";
			
			
			
			System.out.println(JavaBeanUtil.jsonToMap(json));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /**
     *<pre>
     * 说       明: Map --> Bean 2: 利用org.apache.commons.beanutils 工具类实现 Map --> Bean
     * @param map
     * @param obj
     * 涉及版本: V1.0.0 
     * 创  建  者: 陈林林(Vickey)
     * 日       期: 2015-6-30下午4:36:05
     *</pre>
     */
    public static void mapToJavaBean(Map map, Object obj) throws Exception{
        if (map == null || obj == null) {
            return;
        }
        BeanUtils.populate(obj, map);
    }

    /**
     *<pre>
     * 说       明: Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
     * @param map
     * @param obj
     * @throws Exception
     * 涉及版本: V1.0.0 
     * 创  建  者: 陈林林(Vickey)
     * 日       期: 2015-6-30下午4:37:24
     *</pre>
     */
    public static void map2ToJavaBean(Map<String, Object> map, Object obj) throws Exception{

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();

            if (map.containsKey(key)) {
                Object value = map.get(key);
                // 得到property对应的setter方法
                Method setter = property.getWriteMethod();
                setter.invoke(obj, value);
            }

        }
        return;
    }

    /**
     *<pre>
     * 说       明: Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
     * @param obj
     * @return
     * @throws Exception
     * 涉及版本: V1.0.0 
     * 创  建  者: 陈林林(Vickey)
     * 日       期: 2015-6-30下午4:38:03
     *</pre>
     */
    public static Map<String, Object> javaBeanToMap(Object obj) throws Exception{

        if(obj == null){
            return null;
        }
        if(obj instanceof Map){
			return (Map<String, Object>) obj;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();

            // 过滤class属性
            if (!key.equals("class")) {
                // 得到property对应的getter方法
                Method getter = property.getReadMethod();
                Object value = getter.invoke(obj);

                map.put(key, value);
            }

        }
        return map;
    }
    
    /**
     *<pre>
     * 说       明: 将MAP转换成List 
     * @param map
     * @return
     * 涉及版本: 
     * 创  建  者: 陈林林(Vickey)
     * 日       期: 2015-9-23下午2:12:55
     *</pre>
     */
	public static <K, V> List<V> convertMapToList(Map<K, V> map) {
		List<V> list = new ArrayList<V>();
		Iterator<Map.Entry<K, V>> iter = map.entrySet().iterator(); // 获得map的Iterator
		while (iter.hasNext()) {
			Map.Entry<K, V> entry = (Map.Entry<K, V>) iter.next();
			list.add(entry.getValue());
		}
		return list;
	}
	
	/**
	 * <pre>
	 * 说   明: 拼接参数 
	 * 创建者: 陈林林(Vickey)
	 * 修改者:
	 * 日   期: 2014-5-26 下午03:31:11
	 * 
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 * </pre>
	 */
	public static String getAppendParams(Map<String, String> paramsMap)
			throws Exception {
		if (paramsMap == null || paramsMap.size() == 0) {
			return null;
		}
		StringBuffer paramsSB = new StringBuffer();
		String tempKey = "";
		for (Iterator<String> iterator = paramsMap.keySet().iterator(); iterator
				.hasNext();) {
			tempKey = iterator.next();
			paramsSB.append(tempKey + "=" + paramsMap.get(tempKey) + "&");
		}
		return StringUtils.resplaceStr(paramsSB.toString());
	}
	
	/**
	 * <pre>
	 * 说       明: JSON数据转为实体 
	 * @param str
	 * @param cls
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-6下午4:03:16
	 * </pre>
	 */
	public static <W extends Object> W jsonToJavaBean(String str, Class cls)
			throws Exception {
		return (W) JSON.parseObject(str, cls);
	}
	
	/**
	 * <pre>
	 * 说       明: 把JSON数组字符串转为LIST 
	 * @param str
	 * @param cls
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-8-12下午6:36:56
	 * </pre>
	 */
	public static <W extends Object> W jsonToList(String str, Class cls)
			throws Exception {
		return (W) JSON.parseArray(str, cls);
	}
	
	/**
	 *<pre>
	 * 说       明: 对象或数组类型自动判断
	 * @param str
	 * @param cls
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-11-9上午11:41:33
	 *</pre>
	 */
	public static <W extends Object>  W jsonToObjOrList(String str, Class cls)throws Exception {
		if (str == null || cls == null) {
			return null;
		}
		String prefix = str.substring(0, 1);//前缀
		if (prefix.equals("{")) {
			return jsonToJavaBean(str, cls);
		}else if(prefix.equals("[")){
			return jsonToList(str, cls);
		}
		return null;
	}

	/**
	 * <pre>
	 * 说       明: 针对 ResultUtil 对象把JSON数组字符串转为LIST 
	 * @param resultUtil
	 * @param cls
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-8-14下午2:45:54
	 * </pre>
	 */
	public static <W extends Object> W jsonToList(String data, Class cls,
			boolean returnObj) throws Exception {
		if (StringUtils.isBlank(data) || cls == null) {
			return null;
		}
		List list = JSON.parseArray(data, cls);
		if (list == null || list.isEmpty()) {
			return null;
		}
		if (returnObj) {
			return (W) list.get(0);// 只返回一条数据
		}
		return (W) list;// 返回集合
	}

	/**
	 * <pre>
	 * 说       明: JSON字符串转为MAP 
	 * @param str
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-8-14下午2:19:44
	 * </pre>
	 */
	public static Map<String, Object> jsonToMap(String str) throws Exception {
		if (str.indexOf("\"") != -1) {
			return (Map) JSON.parseObject(str, Map.class);
		}else{
			str = str.substring(1,str.length()-1);
			String[] keyValue = str.split(",");
			Map<String, Object> map = new HashMap<String, Object>();
			String[] tempArray = null;
			for (String  temp : keyValue) {
				tempArray = temp.split("=");
				map.put(tempArray[0].trim(), tempArray[1].trim());
			}
			return map;
		}
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * @param obj 
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-14下午7:19:09
	 * </pre>
	 */
	public static String javaBeanToString(Object obj) throws Exception {

		if (obj == null) {
			return "";
		}
		SerializerFeature[] feature=new SerializerFeature[]{SerializerFeature.DisableCircularReferenceDetect
			,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullListAsEmpty
			,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullNumberAsZero
			,SerializerFeature.WriteNullBooleanAsFalse,SerializerFeature.QuoteFieldNames};
		return JSON.toJSONString(obj,feature);
	}
	
	/**
	 *<pre>
	 * 说       明: 把双引号改为逗号
	 * @param obj
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-12-8下午1:57:55
	 *</pre>
	 */
	public static String javaBeanToStringReplace(Object obj) throws Exception {
		String json = javaBeanToString(obj);
		if (StringUtils.isBlank(json)) {
			return json;
		}
		
		return json.replace("\"", "'");
	}
	
	/**
	 *<pre>
	 * 说       明: 根据URL，获取此URL所有参数，以MAP形式返回
	 * @param url
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-12-8下午2:34:28
	 *</pre>
	 */
	public static Map<String, String> urlParamsToMap(String url) throws Exception {
		
		if (StringUtils.isBlank(url)) {
			return null;
		}
		if (url.indexOf("?") == -1) {
			return new LinkedHashMap<String, String>();
		}
		url = url.substring(url.indexOf("?")+1, url.length());
		String[] urlArray = url.split("&");
		Map<String, String> data = new LinkedHashMap<String, String>();
		String[] array = null;
		for (String str : urlArray) {
			
			array = str.split("=");
			data.put(array[0], array[1]);
		}
		return data;
	}
	
	/**
	 *<pre>
	 * 说       明: 从JSON字符串中得到指定KEY的VALUE
	 * @param json
	 * @param key
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-10-22上午11:37:12
	 *</pre>
	 */
	public static String getValueForJsonStr(String json, String key) throws Exception {
		
		if (json == null) {
			return "";
		}
		String[] keyArray = key.split("\\.");
		for (String strKey : keyArray) {
			json = JSON.parseObject(json).get(strKey) + "";//递归
		}
		return json;
	}
	
	/**
	 *<pre>
	 * 说       明: STRING 类型的XML转换为JSON
	 * @param xmlString
	 * @return
	 * @throws DocumentException
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2016-1-23下午4:58:19
	 *</pre>
	 */
	public static Map<String, HashMap> xmlToMap(String xmlString) throws DocumentException {
		Document doc = DocumentHelper.parseText(xmlString);
		Element rootElement = doc.getRootElement();
		Map<String, HashMap> map = new HashMap<String, HashMap>();
		ele2map(map, rootElement);
		System.out.println(map);
		// 到此xml2map完成，下面的代码是将map转成了json用来观察我们的xml2map转换的是否ok
		return map;
	}

	/***
	 * 核心方法，里面有递归调用
	 * 
	 * @param map
	 * @param ele
	 */
	public static void ele2map(Map map, Element ele) {
		System.out.println(ele);
		// 获得当前节点的子节点
		List<Element> elements = ele.elements();
		if (elements.size() == 0) {
			// 没有子节点说明当前节点是叶子节点，直接取值即可
			map.put(ele.getName(), ele.getText());
		} else if (elements.size() == 1) {
			// 只有一个子节点说明不用考虑list的情况，直接继续递归即可
			Map<String, Object> tempMap = new HashMap<String, Object>();
			ele2map(tempMap, elements.get(0));
			map.put(ele.getName(), tempMap);
		} else {
			// 多个子节点的话就得考虑list的情况了，比如多个子节点有节点名称相同的
			// 构造一个map用来去重
			Map<String, Object> tempMap = new HashMap<String, Object>();
			for (Element element : elements) {
				tempMap.put(element.getName(), null);
			}
			Set<String> keySet = tempMap.keySet();
			for (String string : keySet) {
				Namespace namespace = elements.get(0).getNamespace();
				List<Element> elements2 = ele.elements(new QName(string,
						namespace));
				// 如果同名的数目大于1则表示要构建list
				if (elements2.size() > 1) {
					List<Map> list = new ArrayList<Map>();
					for (Element element : elements2) {
						Map<String, Object> tempMap1 = new HashMap<String, Object>();
						ele2map(tempMap1, element);
						list.add(tempMap1);
					}
					map.put(string, list);
				} else {
					// 同名的数量不大于1则直接递归去
					Map<String, Object> tempMap1 = new HashMap<String, Object>();
					ele2map(tempMap1, elements2.get(0));
					map.put(string, tempMap1);
				}
			}
		}
	}
}