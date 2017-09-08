package org.corgiking.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcel {
	public Logger log = Logger.getLogger(Main.class);

	private List<HashMap<String, String>> list_map = new ArrayList<HashMap<String, String>>();

	public List<HashMap<String, String>> readExcel(String filename) {
		Workbook workbook = null;
		try {
			FileInputStream fis = new FileInputStream(filename);

			workbook = Workbook.getWorkbook(fis);

			Sheet sheet = workbook.getSheet(0);

			readSheet(sheet);
			
			return list_map;

		} catch (FileNotFoundException e) {
			log.error("未发现文件:" + filename, e);
			e.printStackTrace();
		} catch (BiffException e) {
			log.error("读取execl错误", e);
			e.printStackTrace();
		} catch (IOException e) {
			log.error("IO异常", e);
			e.printStackTrace();
		}
		
		return null;
	}

	private List<HashMap<String, String>> readSheet(Sheet sheet) {
		int col_num = sheet.getColumns();// 读取列数
		int row_num = sheet.getRows();// 读取行数

		int row_complete = 0;

		ArrayList<String> col_names = new ArrayList<String>();

		for (int i = 0; i < col_num; i++)
			col_names.add(sheet.getCell(i, 0).getContents());

		for (int r = 1; r < row_num; r++) {
			HashMap<String, String> map = new HashMap<String, String>();

			for (int c = 0; c < col_num; c++) {
				Cell cell = sheet.getCell(c, r);

				map.put(col_names.get(c), cell.getContents());
			}

			list_map.add(map);

			row_complete++;
		}

		log.info(list_map);
		log.info("存储的行数:" + list_map.size());
		log.info("读取数据行数:" + row_complete);

		return list_map;
	}

}
