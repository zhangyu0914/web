package com.yw.common.beansUtils.utils.ref;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yw.common.beansUtils.utils.DBUtil;
import com.yw.common.beansUtils.utils.date.DateUtils;
import com.yw.common.beansUtils.utils.log.LoggerUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 *<pre>
 * 功   能: 反射实体
 * 创建者: 陈    林(Vickey)
 * 日   期: 2014-8-4下午5:00:01
 * Q  Q: 308053847
 *</pre>
 */
public class ReflectUtil {
	
	private enum Constants{
		
		GET("get"),
        SET("set");
        private String value;

		private Constants(String value) {
			this.value = value;
		}
	}

    public static String toParams(Object dto) {
        Class<?> clz = dto.getClass();
        if (String.class.isAssignableFrom(clz))
            return dto.toString();

        if (Map.class.isAssignableFrom(clz)) {
            Map<String, Object> m = (Map) dto;
            StringBuilder sb = new StringBuilder();
            for (Iterator<String> it = m.keySet().iterator(); it.hasNext();) {
                String key = it.next();
                String value = m.get(key).toString();
                // if(!StringUtil.isBlank(value)){
                sb.append(key);
                sb.append("=");
                sb.append(value);
                sb.append("&");
                // }
            }
            sb.deleteCharAt(sb.length()-1);
            return sb.toString();
        }

        Field[] fields = clz.getDeclaredFields();
        StringBuilder params = new StringBuilder();

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                String name = field.getName();
                Object o = field.get(dto);
                if (o == null)
                    continue;
                String value = String.valueOf(o);
                params.append(name);
                params.append("=");
                params.append(value);
                params.append("&");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        params.deleteCharAt(params.length() - 1);
        return params.toString();
    }
    
    public static Map<String, Object> toParamsMap(Object dto) {
        Class<?> clz = dto.getClass();
        if (String.class.isAssignableFrom(clz))
            return null;
        Map<String, Object> data = new HashMap<String, Object>();
        if (Map.class.isAssignableFrom(clz)) {
            Map<String, Object> m = (Map) dto;
            StringBuilder sb = new StringBuilder();
            for (Iterator<String> it = m.keySet().iterator(); it.hasNext();) {
                
            	String key = it.next();
                String value = m.get(key).toString();
                data.put(key, value);
                
            }
            return data;
        }

        Field[] fields = clz.getDeclaredFields();
        StringBuilder params = new StringBuilder();

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                String name = field.getName();
                Object o = field.get(dto);
                if (o == null)
                    continue;
                String value = String.valueOf(o);
                data.put(name, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    /**
	 *<pre>
	 * 说   明:  获取指定对象中的属性值
	 * @param object
	 * @param property
	 * @return
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-8-4下午5:08:21
	 *</pre>
	 */
	public static void getValue(Object object) throws Exception{
		Class c = Class.forName(object.getClass().getName());//获取该实体的元类型
		Method[] me = c.getMethods();
		for (Method method : me) {
			if (method.getName().indexOf("get") != -1) {
				System.out.println(method.invoke(object));
			}
		}
	}
	
	public static boolean defalutValue(Object object) throws Exception{
		if (object == null) {
			return false;
		}
		Class cc = Class.forName(object.getClass().getName());//获取该实体的元类型
		String property = null;
		String temp = null;
		Method[] me = cc.getDeclaredMethods();
		
		//此类中没有属性
		if (cc.getDeclaredFields().length == 0) {
			return false;
		}
		
		//得到此类下的所有方法
		for (Method method : me) {
			
			if (method.getName().equals("getDoctor")) {
				System.out.println();
			}
			
			method.setAccessible(true);//防止调用private方法报错，并且可以提高效率
			System.out.println(method.getName());
			
			if (method.getName().indexOf(Constants.GET.value) != -1) {//只处理GET方法
				
				if (method.invoke(object) == null) {//当这个GET方法值为空时，则赋值一个默认值
					
					Class c = method.getDeclaringClass();//获取该方法真实所在的CLASS，如：继承
					temp = method.getName().replace(Constants.GET.value, "");
					property = Constants.SET.value + method.getName().replace(Constants.GET.value, "");
					Field field = c.getDeclaredField(temp.substring(0,1).toLowerCase() + temp.substring(1, temp.length()));//获取setName方法中参数的字段
					Method m = c.getDeclaredMethod(property, field.getType());
					m.invoke(object, ReflectUtil.getDefaultValue(field.getType().toString(), field.getType()));
				}else{
					//递归调用
					if (method.invoke(object).toString().indexOf("com.ihygeia.mh") != -1) {
						Object obj = method.invoke(object);
						Class c2 = obj.getClass();
						if (c2.isAssignableFrom(ArrayList.class)) {
							Field field = c2.getDeclaredField("size");//获取setName方法中参数的字段
							Method m = c2.getDeclaredMethod("size");
							int size = (Integer) m.invoke(obj);
							for(int i=0; i<size; i++){
								
								Method m2 = c2.getDeclaredMethod("get", int.class);
								defalutValue(m2.invoke(obj, i));
							}
						}else{
							defalutValue(method.invoke(object));
						}
					}
				}
			}
		}
		return true;
	}
	
	public static Object getDefaultValue(String objStr, Class typeCls) throws Exception{
//		String timeStr = "1970-01-01 00:00:00";//1970-01-01 00:00:00 为正负数分界线
		int value = -1;//28800000
		if (objStr.equals(String.class.toString())) {
			return "";
			
		}else if (objStr.equals(Timestamp.class.toString())) {
			return DateUtils.formatDate(new Date());	//默认为系统时间
			
		}else if (objStr.equals(Date.class.toString())) {
			return new Date();	//默认为系统时间
			
		}else if (objStr.equals(Byte.class.toString())) {
			return (byte)value;
			
		}else if (objStr.equals(Short.class.toString())) {
			return (short)value;
			
		}else if (objStr.equals(Integer.class.toString())) {
			return (int)value;
			
		}else if (objStr.equals(Float.class.toString())) {
			return (float)value;
			
		}else if (objStr.equals(Long.class.toString())) {
			return (long)value;
			
		}else if (objStr.equals(Double.class.toString())) {
			return (double)value;
			
		}else if (objStr.equals(String[].class.toString())) {
			return new String[]{};
			
		}else if (objStr.equals(Byte[].class.toString())) {
			return new Byte[]{};
			
		}else if (objStr.equals(Short[].class.toString())) {
			return new Short[]{};
			
		}else if (List.class.isAssignableFrom(typeCls)) {
			return new ArrayList();
			
		}else if (objStr.indexOf("class [") != -1 && objStr.length() > "class [X".length()) {
			String forName = typeCls.getName().substring(2,typeCls.getName().length() - 1);
			return Array.newInstance(Class.forName(forName), 0);
			
		}else if (objStr.equals(byte[].class.toString())) {
			return new byte[]{};
			
		}else if (objStr.equals(short[].class.toString())) {
			return new short[]{};
			
		}else if (objStr.equals(int[].class.toString())) {
			return new int[]{};
			
		}else if (objStr.equals(float[].class.toString())) {
			return new float[]{};
			
		}else if (objStr.equals(long[].class.toString())) {
			return new long[]{};
			
		}else if (objStr.equals(double[].class.toString())) {
			return new double[]{};
			
		}else if (objStr.equals(Object.class.toString())) {
			return new HashMap();
		}else {
			return null;
		}
	}
	
	/**
	 *<pre>
	 * 说   明:  设置属性的值
	 * @param entity
	 * @param fieldName
	 * @param val
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-11-4下午12:19:49
	 *</pre>
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public static void setProfitValue(Object entity,String fieldName,Object val) throws Exception{
		if (StringUtils.isBlank(entity) || StringUtils.isBlankOr(
				fieldName,
				val)) {
			return;
		}
		Class parentClass = entity.getClass();//获取该实体的元类型
		Field field =  null;
		//判断是否是实体属性
		int index = fieldName.indexOf("."); // usersDetail.displayName
		if (index != -1) {
			
			//为此实体NEW一个对象，并获对此对象的属性进行赋值
			String childrenEntityName = fieldName.substring(0, index); // usersDetail
			String childrenEntityPropertyName = fieldName.substring(index + 1, fieldName.length()); // displayName
			Field parentField = parentClass.getDeclaredField(childrenEntityName);//获取setName方法中参数的字段
			Class childrenClass = parentField.getType();//获取该实体的元类型
			Object childrenObj = childrenClass.newInstance();//创建这个实体的对象
			Field childrenField = childrenClass.getDeclaredField(childrenEntityPropertyName);//获取setName方法中参数的字段
			Method method = childrenClass.getDeclaredMethod(DBUtil.getGetSet(childrenEntityPropertyName, "set"), childrenField.getType());
			method.invoke(childrenObj, val);//赋值
			
			//把此对象赋值进去
			field=entity.getClass().getDeclaredField(childrenEntityName);
			fieldName = childrenEntityName;
			val = childrenObj;
			
		}else{
			try {
				field=entity.getClass().getDeclaredField(fieldName);//普通属性
			} catch (Exception e) {
				field = entity.getClass().getSuperclass().getDeclaredField(fieldName);//父类中找
			}
		}
		
		if(field!=null){
			
			Method m = null;
			Field field2 = null;
			Method setMethod = null;
			try {
				
				//当前类中
				m = entity.getClass().getDeclaredMethod(DBUtil.getGetSet(fieldName, "get"));
				field2 = parentClass.getDeclaredField(fieldName);//获取setName方法中参数的字段
				setMethod=entity.getClass().getMethod(DBUtil.getGetSet(fieldName, "set"),field2.getType());
			} catch (Exception e) {//父类中获取
				
				m = entity.getClass().getSuperclass().getDeclaredMethod(DBUtil.getGetSet(fieldName, "get"));
				field2 = parentClass.getSuperclass().getDeclaredField(fieldName);//获取setName方法中参数的字段
				setMethod=entity.getClass().getSuperclass().getMethod(DBUtil.getGetSet(fieldName, "set"),field2.getType());
			}
			
			if(setMethod!=null){
				if (field2.getType().toString().equals(Integer.class.toString())) {
					Object value=setMethod.invoke(entity,Integer.valueOf(val.toString()));
				}else{
					Object value=setMethod.invoke(entity,val);
				}
			}
		}
	}

	/**
	 *<pre>
	 * 说   明: 
	 * @param entity
	 * @param fieldName
	 * @param val
	 * @throws Exception
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-11-4下午12:19:39
	 *</pre>
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public static void setProfitValue(Object entity,String fieldName,Integer val) throws Exception{
		if (StringUtils.isBlank(entity) || StringUtils.isBlankOr(
				fieldName,
				val)) {
			return;
		}
		Field field=entity.getClass().getDeclaredField(fieldName);
		if(field!=null)
		{
			String prefix=null;
			
			Class fieldClass=field.getDeclaringClass();
			prefix="set";
			String setMethodName=prefix+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1,fieldName.length());
			String getMethodName=Constants.GET.toString().toLowerCase()+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1,fieldName.length());
			Method m = entity.getClass().getDeclaredMethod(getMethodName);
			Class c = m.getDeclaringClass();
			Field field2 = c.getDeclaredField(fieldName);//获取setName方法中参数的字段
			field2.getGenericType();
			Method setMethod=entity.getClass().getMethod(setMethodName,field2.getType());
			if(setMethod!=null){
				if (field2.getType().toString().equals(Integer.class.toString())) {
					Object value=setMethod.invoke(entity,Integer.valueOf(val));
				}else{
					Object value=setMethod.invoke(entity,val);
				}
			}
		}
	}

	public static Object getMapValue(Map<?, ?> map, Object key, Object defaultValue) {
		Object value = null;
		if(map.containsKey(key))value = map.get(key);
		else value = defaultValue;
		return value;
	}
	public static void main(String[] args) {
		try {
			String dir = "C:\\FilePersions\\Project\\Ihygeia\\Localhost\\Workspace_MyEclipse_NEW\\V2.1.0\\app\\ihygeia\\ihygeia-app\\src\\main\\java\\com\\ihygeia\\app\\controller";
			File dirFile = new File(dir);
			if (dirFile.isDirectory()) {
				for(File f : dirFile.listFiles()){
					
					if (f.isFile()) {
						
						String fileName = "com.ihygeia.mh.app.controller."+f.getName();
						Class cc = Class.forName(LoggerUtil.class.getName());//获取该实体的元类型
						String property = null;
						String temp = null;
						Method[] me = cc.getDeclaredMethods();
						
						//此类中没有属性
						if (cc.getDeclaredFields().length == 0) {
						}
						
						//得到此类下的所有方法
						for (Method method : me) {
							System.out.println(fileName.replaceAll(".java", "")+"="+method.getName());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
