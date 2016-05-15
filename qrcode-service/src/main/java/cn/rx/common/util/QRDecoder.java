package cn.rx.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;
import jp.sourceforge.qrcode.exception.DecodingFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QRDecoder {

	private static Logger logger = LoggerFactory.getLogger(QRDecoder.class);
	
	/**
	 * 二维码解码
	 * @param imgPath
	 * @return String
	 */
	public static String decoderQRCode(String imgPath) {

		// QRCode 二维码图片的文件
		File imageFile = new File(imgPath);
		BufferedImage bufImg = null;
		String decodedData = null;

		try {
			bufImg = ImageIO.read(imageFile);
			QRCodeDecoder decoder = new QRCodeDecoder();
			decodedData = new String(decoder.decode(new J2SEImage(bufImg)));

		} catch (IOException e) {
			logger.error("", e);
		} catch (DecodingFailedException dfe) {
			logger.error("", dfe);
		}
		return decodedData;
	}

	static class J2SEImage implements QRCodeImage {

		BufferedImage bufImg;

		public J2SEImage(BufferedImage bufImg) {
			this.bufImg = bufImg;
		}

		public int getWidth() {
			return bufImg.getWidth();
		}

		public int getHeight() {
			return bufImg.getHeight();
		}

		public int getPixel(int x, int y) {
			return bufImg.getRGB(x, y);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String imgPath = "/Users/xule/Downloads/temp/qr_lipengcheng.png";
		long start = System.currentTimeMillis();
		System.out.println("========decoder start...");
		String decoderContent = decoderQRCode(imgPath);
		System.out.println("解析结果：" + decoderContent);
		System.out.println("========decoder success!!!");
		long end = System.currentTimeMillis();
		System.out.println("花费时间：" + (end - start) + " ms");
	}

}
