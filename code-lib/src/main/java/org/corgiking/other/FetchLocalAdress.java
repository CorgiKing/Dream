package org.corgiking.other;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class FetchLocalAdress {

	public static void main(String[] args) throws SocketException, UnknownHostException {
		/**
		 * 使用getLocalHost方法未必能够获取到想要的本机的IP地址
		 * （有人说，在windows上getLocalHost可以正确执行，获取到本机的IPV4地址，
		 * 而在Linux上调用getLocalHost返回的是一个127.0.0.1的ip地址）。
		 * 现在假想有一台主机有多张网卡，而且有多个IP地址，甚至这台主机即配置了IPV4又配置了IPV6，
		 * 调用getLocalHost将会返回哪一个IP地址？
		 * 很明显getLocalHost只能返回一个IP地址，因此就限定了getLocalHost方法并不是获取本机IP地址的最好方式。
		 * 
		 * 使用getLocalHost方法可以获取本地IP地址，但是这种方式并不可靠，
		 * 当出现多张网卡，或一个网络接口配置了多个IP，或者不同的操作系统类型，都不能保证能够获得想要的IP
		 */
		System.out.println(InetAddress.getLocalHost().getHostAddress());

		
		
		
		
		/**
		 * NetworkInterface可以获取本机网络接口的相关信息，包括硬件地址，MTU，所有的IP地址等信息，
		 * 需要获取本机IP时，最好使用NetworkInterface对配置的IP地址进行筛选。
		 */
		Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();

		while (networks.hasMoreElements()) {
			NetworkInterface networkInterface = (NetworkInterface) networks.nextElement();
			Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
			while (inetAddresses.hasMoreElements()) {
				InetAddress inetAddress = inetAddresses.nextElement();
				if (inetAddress instanceof Inet4Address) {
					String ip = inetAddress.getHostAddress();
					if (ip != null && ip.equals("127.0.0.1")) {
						System.out.println(ip);
					}
				}
			}

		}

	}

}
