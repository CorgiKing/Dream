package org.corgiking.release;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FTPUtil {
	private static Logger log = LoggerFactory.getLogger(FTPUtil.class);

	private FTPClient client;
	
	private FTPUtil(){}

	public static FTPUtil build(String ip, int port, String name, String pwd) {
		FTPUtil ftpUtil = new FTPUtil();
		FTPClient ftpClient = new FTPClient();

		try {
			ftpClient.setConnectTimeout(5000);
			ftpClient.connect(ip, port);
			ftpClient.login(name, pwd);
		} catch (Exception e) {
			log.error("ftp连接失败:IP-{},PORT-{},NAME-{},PWD-{},error-{}", ip, port, name, pwd, e.getMessage());
			return null;
		}
		int replyCode = ftpClient.getReplyCode();
		if (!FTPReply.isPositiveCompletion(replyCode)) {
			log.error("ftp Reply not positive :IP-{},PORT-{},NAME-{},PWD-{}", ip, port, name, pwd);
			return null;
		}
		ftpUtil.prepare();
		return ftpUtil;
	}

	private boolean prepare() {
		client.setBufferSize(2048);
		client.setControlEncoding("utf-8");
		try {
			client.setFileTransferMode(FTPClient.STREAM_TRANSFER_MODE);
			// 设置文件格式
			client.setFileType(FTPClient.BINARY_FILE_TYPE);
			client.setRemoteVerificationEnabled(false);
			client.enterLocalPassiveMode();
		} catch (IOException e) {
			log.error("ftpClient设置错误:{}", e.getMessage());
			return false;
		}
		return true;
	}

	public boolean upload(String localFileName, String targetDir, String targetFileName) {
		File file = new File(localFileName);
		return upload(file, targetDir, targetFileName);
	}

	public boolean upload(File localFile, String targetDir, String targetFileName) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(localFile);
			return upload(fis, targetDir, targetFileName);
		} catch (FileNotFoundException e) {
			log.error("读取{}文件失败:{}", localFile, e.getMessage());
			return false;
		}
	}

	public boolean upload(FileInputStream fis, String targetDir, String targetFileName) {
		// 切换工作目录
		try {
			boolean changeDir = client.changeWorkingDirectory(targetDir);
			if (!changeDir) {
				log.error("切换工作目录失败:{}", targetDir);
				return false;
			}
		} catch (IOException e) {
			log.error("切换工作目录失败:" + e.getMessage());
			return false;
		}
		// 上传文件
		try {
			client.storeFile(targetFileName, fis);
			return true;
		} catch (IOException e) {
			log.error("ftpClient上传文件出错:{}", e.getMessage());
			return false;
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				log.error("fis关闭时出错:{}", e.getMessage());
			}
		}
	}
}
