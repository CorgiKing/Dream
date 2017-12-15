package org.corgiking.release;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.corgiking.other.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvFileUtil {
	private static Logger log = LoggerFactory.getLogger(CsvFileUtil.class);

	private static final String DEFAULT_PATH = "/tmp/upload";

	/**
	 * 导出数据
	 * 
	 * @param dataList
	 * @param fileName
	 * @return
	 */
	public static String exportCsv(List<?> dataList, String fileName) {
		return exportCsv(dataList, DEFAULT_PATH, fileName, false);
	}

	/**
	 * 导出数据
	 * 
	 * @param dataList
	 * @param fileName
	 * @param columnName
	 * @return
	 */
	public static String exportCsv(List<?> dataList, String fileName, boolean columnName) {
		return exportCsv(dataList, DEFAULT_PATH, fileName, columnName);
	}

	/**
	 * 导出数据
	 * 
	 * @param dataList
	 * @param dirName
	 * @param fileName
	 * @param columnName
	 * @return
	 */
	public static String exportCsv(List<?> dataList, String dirName, String fileName, boolean columnName) {

		File targetFile = generateTargetFile(dirName, fileName);
		if (targetFile == null) {
			return null;
		}
		boolean flag = exportData(dataList, targetFile, columnName);
		if (flag) {
			return targetFile.getAbsolutePath();
		}
		return null;
	}

	/**
	 * 导出数据到指定文件
	 * 
	 * @param dataList
	 * @param targetFile
	 * @param columnName
	 * @return
	 */
	public static boolean exportData(List<?> dataList, File targetFile, boolean columnName) {
		if (dataList == null || dataList.size() == 0) {
			return true;
		}
		BufferedWriter bufw;
		try {
			bufw = new BufferedWriter(new FileWriter(targetFile));
		} catch (IOException e) {
			log.error(e.getMessage());
			return false;
		}

		// 写入列名
		if (columnName) {
			writeColumnName(dataList, bufw);
		}
		// 写入数据
		dataList.forEach(data -> {
			writeData(dataList, bufw, data);
		});
		try {
			bufw.flush();
			return true;
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			try {
				if (bufw != null) {
					bufw.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
		return false;
	}

	/**
	 * 写入数据
	 * @param dataList
	 * @param bufw
	 * @param data
	 * @return
	 */
	private static boolean writeData(List<?> dataList, BufferedWriter bufw, Object data) {
		try {
			Class<?> clazz = dataList.get(0).getClass();
			Field[] fields = clazz.getDeclaredFields();
			StringBuilder str = new StringBuilder();
			for (Field field : fields) {
				field.setAccessible(true);
				Object v = field.get(data);
				str.append(v + ",");
			}
			bufw.write(str.substring(0, str.length() - 1) + "\n");
			return true;
		} catch (Exception e) {
			log.error("写入数据出错:data-{},error-{}", data, e.getMessage());
		}
		return false;
	}

	/**
	 * 写入列名
	 * @param dataList
	 * @param bufw
	 * @return
	 */
	private static boolean writeColumnName(List<?> dataList, BufferedWriter bufw) {
		Class<?> clazz = dataList.get(0).getClass();
		Field[] fields = clazz.getDeclaredFields();
		StringBuilder str = new StringBuilder();
		for (Field field : fields) {
			String name = field.getName();
			str.append(name + ",");
		}
		try {
			bufw.write(str.substring(0, str.length() - 1) + "\n");
			return true;
		} catch (IOException e) {
			log.error("写入列名出错:error-{}", e.getMessage());
		}
		return false;
	}

	/**
	 * 生成Csv文件
	 * 
	 * @param dirName
	 * @param fileName
	 * @return
	 */
	public static File generateTargetFile(String dirName, String fileName) {
		if (StringUtils.isAnyBlank(dirName, fileName)) {
			return null;
		}
		String targetFileName = dirName + File.separator + fileName + ".csv";
		try {
			// 目录
			File dir = new File(dirName);
			if (!dir.exists() && !dir.createNewFile()) {
				log.error("创建目录失败：{}", dirName);
				return null;
			}
			// 文件
			File targetFile = new File(targetFileName);
			if (!targetFile.exists() && !targetFile.createNewFile()) {
				log.error("创建文件失败：{}", targetFileName);
				return null;
			}
			return targetFile;
		} catch (Exception e) {
			log.error("创建文件失败:file-{},error-{}", targetFileName, e.getMessage());
			return null;
		}
	}

	public static void main(String[] args) {
		List<User> users = new ArrayList<User>();
		users.add(new User("aaa", "a", 11));
		users.add(new User("aaa", "a", 11));
		users.add(new User("aaa", "a", 11));
		users.add(new User("aaa", "a", 11));
		String fileName = CsvFileUtil.exportCsv(users, "userTable", true);
		System.out.println(fileName);
	}

}
