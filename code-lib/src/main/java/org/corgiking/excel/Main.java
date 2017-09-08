package org.corgiking.excel;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

public class Main {
	
	
	
	public static Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) {
		
		String filename = "C:/Users/SONGJC/Desktop/users.xls";
		
		ReadExcel readExcel = new ReadExcel();
		
		List<HashMap<String, String>> list_map = readExcel.readExcel(filename);
		
		for(HashMap<String, String> map:list_map){
			System.out.println(map.get("用户名")+map.get("邮箱"));
		}
		
	}
	
}
