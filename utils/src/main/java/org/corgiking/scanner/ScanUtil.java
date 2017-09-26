package org.corgiking.scanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

@Deprecated
public class ScanUtil {
	private CKLogger log = CKLogger.getLogger(getClass());

	private static final String SEPARATOR = File.separator;

	private String basePackage;

	private ClassLoader cLoader;

	public ScanUtil(String basePackage) {
		this.basePackage = basePackage;
		cLoader = getClass().getClassLoader();
	}

	public List<String> scanAllClassNameList() {
		log.info("开始扫描" + basePackage + "包下的类");

		String packageDirName = getDirName();

		URL url = cLoader.getResource(packageDirName);
		String filePath = getRootPath(url);

		List<String> classNames = null;

		String protocol = url.getProtocol();
		if ("jar".equalsIgnoreCase(protocol)) {
			log.info(StringFormat.strcat("从Jar包中扫描类：", filePath));
			classNames = readFromJarFile(filePath, packageDirName);
		} else if ("war".equalsIgnoreCase(protocol)) {
			log.info(StringFormat.strcat("从War包中扫描类：", filePath));
			throw new RuntimeException(filePath + "是个war包");
		} else {
			log.info(StringFormat.strcat("从目录中扫描类：", filePath));
			classNames = readFromDirFile(filePath);
		}
		log.info("读取类名: ", classNames);

		return null;
	}

	private List<String> readFromDirFile(String path) {
		log.info("从目录中读取类:" + path);

		return readFromDirFile(path, basePackage);
	}

	private List<String> readFromDirFile(String path, String packageName) {
		List<String> classNames = new ArrayList<>();

		File dir = new File(path);
		String[] names = dir.list();
		if (names == null) {
			return null;
		}
		for (String str : names) {
			if (str.endsWith(".class")) {// 是个类
				classNames.add(StringFormat.strcat(packageName, ".", str));
			} else {
				File file = new File(StringFormat.strcat(path, SEPARATOR, str));
				boolean b = file.isDirectory();
				if (b) {
					readFromDirFile(StringFormat.strcat(path, "/", str), StringFormat.strcat(packageName, ".", str));
				}
			}
		}

		return classNames;
	}

	/**
	 * 
	 * @param jarPath
	 *            /E:/GitHub/MyUtils/bin/foo.jar
	 * @param packageDirName
	 *            org/corgiking
	 * @return
	 */
	private List<String> readFromJarFile(String jarPath, String packageDirName) {
		log.info("从jar文件中读取类:" + jarPath);

		JarInputStream jis = null;
		List<String> nameList = new ArrayList<String>();
		try {
			jis = new JarInputStream(new FileInputStream(jarPath));

			JarEntry entry = jis.getNextJarEntry();

			while (entry != null) {
				String name = entry.getName();
				if (name.startsWith(packageDirName) && name.endsWith(".class")) {
					nameList.add(name);
				}
				entry = jis.getNextJarEntry();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (jis != null) {
				try {
					jis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return nameList;
	}

	/**
	 * 获取绝对路径 file:/E:/GitHub/MyUtils/bin/org/corgiking -->
	 * /E:/GitHub/MyUtils/bin/org/corgiking
	 * jar:file:/E:/GitHub/MyUtils/bin/MyUtils.jar!org/corgiking -->
	 * /E:/GitHub/MyUtils/bin/MyUtils.jar
	 * 
	 * @param url
	 * @return
	 */
	private String getRootPath(URL url) {
		String fileUrl = url.getFile();
		int start = fileUrl.indexOf('/');
		int end = fileUrl.indexOf('!');

		if (-1 == end) {
			return fileUrl;
		}

		return fileUrl.substring(start, end);
	}

	/**
	 * org.corgiking --> org/corgiking
	 * 
	 * @return
	 */
	private String getDirName() {
		return basePackage.replaceAll("\\.", "/");
	}

	public static void main(String[] args) throws IOException {
		// ScanUtil scanUtil = new ScanUtil("org.corgiking");
		// scanUtil.scanAllClassNameList();
		String basePackage = "org.corgiking";
		ScanFromDirFile(basePackage);
		ScanFromJarFile(basePackage);
	}

	private static void ScanFromDirFile(String basePackage) {
		String classpath = ScanUtil.class.getResource("/").getPath();
		System.out.println(classpath);

		basePackage = basePackage.replace(".", SEPARATOR);

		String scanPath = classpath + basePackage;
		scanFromPath(new File(scanPath));
		System.out.println("end");
	}
	
	private static void scanFromPath(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for(File f:files){
				scanFromPath(f);
			}
		}else {
			
			if (file.getName().endsWith(".class")) {
				System.out.println(file.getPath());
			}
		}
		
	}

	private static void ScanFromJarFile(String basePackage) throws IOException {
		Enumeration<URL> resource = ClassLoader
				.getSystemResources(basePackage.replace(".", "/"));
		while (resource.hasMoreElements()) {
			URL url = resource.nextElement();
			System.out.println(url);

			String protocol = url.getProtocol();
			if ("jar".equalsIgnoreCase(protocol)) {

				JarURLConnection con = (JarURLConnection) url.openConnection();

				if (con != null) {

					JarFile jarFile = con.getJarFile();
					if (jarFile != null) {

						Enumeration<JarEntry> entries = jarFile.entries();

						while (entries.hasMoreElements()) {
							JarEntry jarEntry = entries.nextElement();
							String name = jarEntry.getName();
							if (name.endsWith(".class") && name.startsWith(basePackage.replace('.', '/'))) {
								System.out.println(name);
							}

						}
					}
				}

			}

		}
		System.out.println("end");
	}

}
