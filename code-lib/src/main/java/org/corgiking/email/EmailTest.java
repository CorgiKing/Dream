package org.corgiking.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailTest {

	public static void main(String[] args) throws AddressException, MessagingException {
		// 设置属性
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "SMTP");
		props.setProperty("mail.smtp.host", "mail.github.com");
		props.setProperty("mail.smtp.port", "25");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.timeout", "1000");

		// 认证信息
		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("goaler@github.com", "goaler");
			}
		};
		// 获取会话
		Session session = Session.getInstance(props, auth);

		// 创建msg
		Message msg = new MimeMessage(session);
		// 发送者
		msg.setFrom(new InternetAddress("goaler@github.com"));
		// 发送方式和接收者
		msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("goaler@github.com"));
		// 主题
		msg.setSubject("测试邮件");
		// 内容
		msg.setContent("发送邮件测试", "text/html;charset=utf-8");

		// 设置邮件发送时期
		// msg.setSentDate(new Date());
		Transport.send(msg);

	}

}
