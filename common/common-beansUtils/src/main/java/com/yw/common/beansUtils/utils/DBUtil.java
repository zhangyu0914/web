package com.yw.common.beansUtils.utils;

import java.util.List;

import org.apache.log4j.Logger;

import com.yw.common.beansUtils.utils.file.FileUtil;

/**
 *<pre>
 * 功       能: 有关数据库工具
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-9-23下午2:26:55
 * Q    Q: 308053847
 *</pre>
 */
public class DBUtil {
	
	private static Logger log = Logger.getLogger(DBUtil.class);
	
	/**
	 * <pre>
	 * 说       明: 获取属性的GET SET方法 TODO DBUTIL
	 * @param propertyName
	 * @param getset
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-6-18上午11:10:38
	 * </pre>
	 */
	public static String getGetSet(String propertyName, String getset)
			throws Exception {

		if (propertyName == null || getset == null) {
			return null;
		}
		return getset + propertyName.substring(0, 1).toUpperCase()
				+ propertyName.substring(1, propertyName.length());
	}

	/**
	 * <pre>
	 * 说       明: 根据数据库中的字段名，得到适应于 set/get 方法
	 * @param columnName TODO DBUTIL
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-2-28上午11:36:12
	 * </pre>
	 */
	public static String getGetSet(String columnName) throws Exception {

		columnName = columnName.toLowerCase();
		if (columnName.indexOf("_") == -1) {
			return columnName.substring(0, 1).toUpperCase()
					+ columnName.substring(1, columnName.length());
		}
		String[] array = columnName.split("_");
		if (array.length < 2) {
			return columnName;
		}
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(array[0].substring(0, 1).toUpperCase()
				+ array[0].substring(1, array[0].length()));
		String newStr = "";
		for (int i = 1; i < array.length; i++) {
			newStr = "";
			if (array[i].length() > 1) {
				newStr = array[i].substring(0, 1).toUpperCase()
						+ array[i].substring(1, array[i].length());
			} else {
				newStr = array[i].substring(0, 1).toUpperCase();
			}
			stringBuffer.append(newStr);
		}
		return stringBuffer.toString();
	}

	/**
	 * <pre>
	 * 说       明: 把输入的表名变为小写
	 * @param tableName : T_TEST TODO DBUTIL
	 * @return : TT
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-2-28下午1:48:32
	 * </pre>
	 */
	public static String getShortTableName(String tableName) throws Exception {

		if (tableName == null || tableName.trim().equals("")) {
			return null;
		}
		tableName = tableName.toUpperCase();
		if (tableName.indexOf("_") == -1) {
			return tableName.substring(0, 1)
					+ tableName.substring(tableName.length() - 1);
		}
		String[] array = tableName.split("_");
		if (array.length < 2) {
			return tableName.substring(0, 1)
					+ tableName.substring(tableName.length() - 1);
		}
		StringBuffer stringBuffer = new StringBuffer();
		String newStr = "";
		for (String str : array) {
			stringBuffer.append(str.subSequence(0, 1));
		}
		return stringBuffer.toString();
	}

	/**
	 * <pre>
	 * 说       明: 根据表名，得到模块名称，如：ForeignKey
	 * @param tableName :T_FOREIGN_KEY TODO DBUTIL
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-3-2上午10:39:54
	 * </pre>
	 */
	public static String getModuleName(String tableName) throws Exception {

		if (tableName == null || tableName.trim().equals("")) {
			return null;
		}
		tableName = tableName.toLowerCase();
		if (tableName.indexOf("_") == -1) {
			return tableName.substring(0, 1).toUpperCase()
					+ tableName.substring(1, tableName.length());
		}
		String[] array = tableName.split("_");
		if (array.length < 2) {
			return tableName;
		}
		StringBuffer stringBuffer = new StringBuffer();
		String newStr = "";
		for (int i = 1; i < array.length; i++) {
			stringBuffer.append(array[i].substring(0, 1).toUpperCase()
					+ array[i].substring(1, array[i].length()));
		}
		return stringBuffer.toString();
	}

	/**
	 * <pre>
	 * 说       明: 根据表名，得到模块名称，如：foreignKey
	 * @param tableName :T_FOREIGN_KEY TODO DBUTIL
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-3-2上午10:45:47
	 * </pre>
	 */
	public static String getModuleNameLower(String tableName) throws Exception {

		if (tableName == null || tableName.trim().equals("")) {
			return null;
		}
		tableName = tableName.toLowerCase();
		if (tableName.indexOf("_") == -1) {
			return tableName.substring(0, 1).toLowerCase()
					+ tableName.substring(1, tableName.length());
		}
		String[] array = tableName.split("_");
		if (array.length < 2) {
			return tableName;
		}
		StringBuffer stringBuffer = new StringBuffer();
		String newStr = "";
		stringBuffer.append(array[1].substring(0, 1).toLowerCase()
				+ array[1].substring(1, array[1].length()));
		for (int i = 2; i < array.length; i++) {
			stringBuffer.append(array[i].substring(0, 1).toUpperCase()
					+ array[i].substring(1, array[i].length()));
		}
		return stringBuffer.toString();
	}

	

	

	

	/**
	 * <pre>
	 * 说       明: 根据表名,得到指标规范的MAPPER文件名
	 * @param tableName TODO DBUTIL
	 * @param prefix
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-3-5下午5:01:51
	 * </pre>
	 */
	public static String getMapperName(String tableName, String prefix)
			throws Exception {

		if (tableName == null || tableName.trim().equals("")) {
			return null;
		}
		prefix = prefix.toLowerCase();
		tableName = tableName.toLowerCase();
		if (tableName.indexOf("_") == -1) {
			return prefix + "_" + tableName;
		}
		String[] array = tableName.split("_");
		if (array.length < 2) {
			return prefix + "_" + tableName;
		}
		if (array[0].equalsIgnoreCase(prefix)) {
			return tableName.toLowerCase();
		}
		return prefix + "_" + tableName;
	}
	
	/**
	 * <pre>
	 * 说       明: 根据表名、模板，生成建表的脚本
	 * @param sourceTables
	 * @param createTableModel TODO DBUTIL
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-5-6下午3:45:19
	 * </pre>
	 */
	public static String createTable(String sourceTables,
			String createTableModel) throws Exception {
		List<String> list = FileUtil.readFileForDir(sourceTables);
		String sqlModel = FileUtil.readFile(createTableModel);
		StringBuffer sqlFile = new StringBuffer();
		for (String str : list) {
			sqlFile.append(sqlModel.replace("｛tableName｝",
					str.split(",")[1].toLowerCase()).replace("｛tableComment｝",
					str.split(",")[0]));
			sqlFile.append("\n");
		}
		return sqlFile.toString();// 生成文件
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * @param columnName TODO DBUTIL
	 * @return
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-1-23下午2:13:06
	 * </pre>
	 */
	public static String convertColumnName(String columnName) {
		columnName = columnName.toLowerCase();
		if (columnName.indexOf("_") == -1) {
			return columnName;
		}
		String[] array = columnName.split("_");
		if (array.length < 2) {
			return columnName;
		}
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(array[0]);
		String newStr = "";
		for (int i = 1; i < array.length; i++) {
			newStr = "";
			if (array[i].length() > 1) {
				newStr = array[i].substring(0, 1).toUpperCase()
						+ array[i].substring(1, array[i].length());
			} else {
				newStr = array[i].substring(0, 1).toUpperCase();
			}
			stringBuffer.append(newStr);
		}
		return stringBuffer.toString();
	}
	
	public static void main(String[] args) {
		
	}
}
