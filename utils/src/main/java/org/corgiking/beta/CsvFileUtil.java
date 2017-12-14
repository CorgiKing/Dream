package org.corgiking.beta;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvFileUtil {
	private static Logger log = LoggerFactory.getLogger(CsvFileUtil.class);

	private static String filePath = "/tmp/upload";

	public static void exportCsv(List<?> dataList, String fileName) {
		BufferedWriter bufw = null;
		try {
			String targetFileName = filePath + File.separator + fileName + ".csv";
			// 目录
			File dir = new File(filePath);
			if (!dir.exists()&&!dir.createNewFile()) {
				log.error("创建目录失败：{}",filePath);
				return;
			}
			//文件
			File targetFile = new File(targetFileName);
			if (!targetFile.exists()&&!targetFile.createNewFile()) {
				log.error("创建文件失败：{}",targetFileName);
				return;
			}
			
			FileWriter fw = new FileWriter(targetFile);
			bufw = new BufferedWriter(fw);
			dataList.forEach(data -> {
				try {
					Class<?> clazz = data.getClass();
					Field[] fields = clazz.getDeclaredFields();
					StringBuilder str = new StringBuilder();
					for (int i = 0; i < fields.length; i++) {
						Method m1 = clazz.getDeclaredMethod("get" + fields[i].getName().substring(0, 1).toUpperCase()
								+ fields[i].getName().substring(1));
						str.append(m1.invoke(data) + ",");
					}
					fw.write(str.substring(0, str.length() - 1) + "\n");
				} catch (Exception e) {
					log.error(e);
				}
			});
			bufw.flush();
		} catch (Exception e) {
			log.error(e);
		} finally {
			try {
				bufw.close();
			} catch (IOException e) {
				log.error(e);
			}
		}
	}

}
