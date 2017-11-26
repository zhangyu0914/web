package com.yw.common.beansUtils.utils.excel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.yw.common.beansUtils.utils.date.DateUtils;

public class ExcelUtil {
	public static List<String[]> readXml(String fileName) throws Exception {
		boolean isE2007 = false; // 判断是否是excel2007格式
		if (fileName.endsWith("xlsx"))
			isE2007 = true;
		InputStream input = new FileInputStream(fileName); // 建立输入流
		Workbook wb = null;
		// 根据文件格式(2003或者2007)来初始化
		if (isE2007)
			wb = new XSSFWorkbook(input);
		else
			wb = new HSSFWorkbook(input);
		List<String[]> list = new ArrayList<String[]>();
		Sheet sheet = wb.getSheetAt(0); // 获得第一个表单
		Iterator<Row> rows = sheet.rowIterator(); // 获得第一个表单的迭代器
		int columnIndex = 0;
		int rowIndex = 0;
		while (rows.hasNext()) {

			rowIndex++;
			columnIndex = 0;
			String[] data = new String[15];
			Row row = rows.next(); // 获得行数据
			Iterator<Cell> cells = row.cellIterator(); // 获得第一行的迭代器
			while (cells.hasNext()) {

				Cell cell = cells.next();
				switch (cell.getCellType()) { // 根据cell中的类型来输出数据
				case HSSFCell.CELL_TYPE_NUMERIC:
					if (rowIndex > 1) {
						if (columnIndex == 0) {
							data[columnIndex] = Double.valueOf(
									cell.getNumericCellValue()).intValue()
									+ "";
						} else {
							data[columnIndex] = DateUtils.format(
									cell.getDateCellValue(),
									DateUtils.PATTERN_24_YYYY_MM_DD_HH_MM_SS);
						}
						columnIndex++;
					}
					break;
				case HSSFCell.CELL_TYPE_STRING:
					if (rowIndex > 1) {
						data[columnIndex] = cell.getStringCellValue();
						columnIndex++;
					}
					break;
				case HSSFCell.CELL_TYPE_BOOLEAN:
					System.out.println(cell.getBooleanCellValue());
					break;
				case HSSFCell.CELL_TYPE_FORMULA:
					System.out.println(cell.getCellFormula());
					if (rowIndex > 1) {
						data[columnIndex] = cell.getCellFormula();
						columnIndex++;
					}
					break;
				default:
					if (rowIndex > 1) {
						data[columnIndex] = cell.getStringCellValue();
						columnIndex++;
					}
					break;
				}
			}
			if (rowIndex > 1) {
				list.add(data);
			}
		}
		return list;
	}
	
	/**
	 * <pre>
	 * 说       明: 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 涉及版本: V3.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2016年8月3日上午11:57:01
	 * Q    Q: 308053847
	 * @param params 需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 * </pre>
	 */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }
	
	public static void main(String[] args) {
		try {
			ExcelUtil.readXml("D:/2.xlsx");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
