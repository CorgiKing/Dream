package org.corgiking.qrcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class ZxingTest {
	public static void main(String[] args) throws WriterException, IOException, NotFoundException {
	    genQrCode();
	    analyzeQrCode();
	  }

	/**
	 * 解析二维码
	 * @throws IOException
	 * @throws NotFoundException
	 */
	  private static void analyzeQrCode() throws IOException, NotFoundException {
	    MultiFormatReader multiFormatReader = new MultiFormatReader();
	    File file = new File("E:/develop/qrcode.png");
	    BufferedImage image = ImageIO.read(file);
	    //定义二维码参数
	    Map<DecodeHintType,Object> hints = new HashMap<>();
	    hints.put(DecodeHintType.CHARACTER_SET,"utf-8");

	    //获取读取二维码结果
	    BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
	    Result result = multiFormatReader.decode(binaryBitmap, hints);

	    System.out.println("读取二维码： " + result.toString());
	    System.out.println("二维码格式： " + result.getBarcodeFormat());
	    System.out.println("二维码内容： " + result.getText());
	  }

	  /**
	   * 生成二维码
	   * @throws WriterException
	   * @throws IOException
	   */
	  private static void genQrCode() throws WriterException, IOException {
	    int width=300;
	    int hight=300;
	    String content="www.baidu.com";
	    HashMap<EncodeHintType,Object> hints=new HashMap<>();
	    hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
	    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);//纠错等级L,M,Q,H
	    hints.put(EncodeHintType.MARGIN, 2); //边距
	    BitMatrix bitMatrix=new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, hight, hints);
	    Path file=new File("D:/download/imag.png").toPath();
	    MatrixToImageWriter.writeToPath(bitMatrix, "png", file);
	  }
}
