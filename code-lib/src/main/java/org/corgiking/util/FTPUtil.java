package org.corgiking.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUtil {
	
	
	
	public static void main(String[] args) throws Exception {
		FTPClient ftpClient = new FTPClient();
		ftpClient.connect("192.168.1.130", 21);
		ftpClient.login("root", "root");
		//设置文件格式
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		//获取FTP Server应答
		int reply = ftpClient.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftpClient.disconnect();
			return ;
		}
		//切换工作目录
		ftpClient.changeWorkingDirectory("/workspace");
		//创建目录
		
		
		
	}

}
