package org.corgiking.img;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImgTest {

	public static void main(String[] args) throws IOException {
		System.out.println(Integer.MAX_VALUE);
		
//		File file = new File("e:/反.jpg");
//		File file2 = new File("e:/f.jpg");
//		imageScale(file, 0.5, file2);

	}

	public static void imageScale(File source, int width, int height, File target) throws IOException {
		// 读取图片，并获得图片的宽和高
		BufferedImage src_image = ImageIO.read(source);
		
		// 声明一个图片容器，利用dramImage方法绘制新的图片
		BufferedImage buf_image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		buf_image.getGraphics().drawImage(src_image, 0, 0, width, height, null);
		// io流输出图片
		FileOutputStream fos = new FileOutputStream(target);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
		encoder.encode(buf_image);
		fos.close();
	}
	
	public static void imageScale(File source, double scale, File target) throws IOException {
		// 读取图片，并获得图片的宽和高
		BufferedImage src_image = ImageIO.read(source);
		
		// 这种是按比例压缩，得到新的长宽高。
		 int image_width = src_image.getWidth();
		 int image_height = src_image.getHeight();
		 int width=(int) (image_width*scale);
		 int height=(int) (image_height*scale);
		
		// 声明一个图片容器，利用dramImage方法绘制新的图片
		BufferedImage buf_image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		buf_image.getGraphics().drawImage(src_image, 0, 0, width, height, null);
		// io流输出图片
		FileOutputStream fos = new FileOutputStream(target);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fos);
		encoder.encode(buf_image);
		fos.close();
	}

}
