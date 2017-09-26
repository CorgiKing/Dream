package org.corgiking.scanner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CKLogger {
	
	private Class<?> clazz;
	
	private String datePattern = "yyyy-MM-dd HH:mm:ss:SSS";
	
	private SimpleDateFormat sdf;
	
	public CKLogger(Class<?> clazz){
		this.clazz = clazz;
	}
	
	public static CKLogger getLogger(Class<?> clazz){
		return new CKLogger(clazz);
	}
	
	public void info(String msg){
		System.out.print(getDateStr()+"    ");
		System.out.print(clazz.getName()+"    ");
		System.out.println(msg);
	}
	
	public void setDatePattern(String pattern){
		this.datePattern = pattern;
	}
	
	private String getDateStr(){
		if (sdf == null) {			
			sdf = new SimpleDateFormat(datePattern);
		}
		return sdf.format(new Date());
	}

	public void info(String msg, List<String> list) {
		info(msg);
		for(String str:list){
			info(str);
		}
	}
	
	
	
}
