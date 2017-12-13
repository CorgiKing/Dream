package org.corgiking.scanner;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 扫描指定包下的类返回类名
 * @author Administrator
 *
 */
public class PackageScanner {

	private static final Logger log = LoggerFactory.getLogger(PackageScanner.class);

	private List<String> classNames;

	private String basePackage;

	public PackageScanner() {
		classNames = new ArrayList<>();
	}

	public List<String> scanning(String basePackage) {
		this.basePackage = basePackage;
		try {
			ScanFromJar(basePackage);
		} catch (IOException e) {
			e.printStackTrace();
			log.info("扫描Jar文件出错！");
		}

		ScanFromFile(basePackage);

		log.info("扫描到的类:{}", classNames);
		return classNames;
	}

	private void ScanFromJar(String basePackage) throws IOException {
		Enumeration<URL> resource = this.getClass().getClassLoader().getResources(basePackage.replace(".", "/"));
		while (resource.hasMoreElements()) {
			URL url = resource.nextElement();

			String protocol = url.getProtocol();
			if ("jar".equalsIgnoreCase(protocol)) {

				JarURLConnection con = (JarURLConnection) url.openConnection();

				if (con != null) {

					JarFile jarFile = con.getJarFile();
					if (jarFile != null) {
						log.info("扫描{}",jarFile.getName());
						Enumeration<JarEntry> entries = jarFile.entries();

						while (entries.hasMoreElements()) {
							JarEntry jarEntry = entries.nextElement();
							String name = jarEntry.getName();// org/corgiking/CKLogger.class
							if (name.endsWith(".class") && name.startsWith(basePackage.replace('.', '/'))) {
								classNames.add(name.substring(0, name.lastIndexOf('.')).replace('/', '.'));
							}

						}
					}
				}

			}

		}
	}

	private void ScanFromFile(String basePackage) {
		URL url = this.getClass().getResource("/" + basePackage.replace('.', '/'));
		if (url != null && "file".equalsIgnoreCase(url.getProtocol())) {
			String scanPath = url.getPath();
			log.info("扫描 :", scanPath);

			scanFromPath(new File(scanPath));
		}

	}

	private void scanFromPath(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				scanFromPath(f);
			}
		} else {

			if (file.getName().endsWith(".class")) {
				String name = file.getPath().replace(File.separatorChar, '.');
				int s = name.lastIndexOf(basePackage);
				int e = name.lastIndexOf('.');
				name = name.substring(s, e);
				classNames.add(name);
			}
		}

	}

	public static void main(String[] args) {
		PackageScanner scanner = new PackageScanner();
		List<String> classNames = scanner.scanning("org.corgiking");
		for(String name:classNames){
			System.out.println(name);
		}
	}
}
