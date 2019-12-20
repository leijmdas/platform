package com.kunlong.platform.util;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * 图片工具类
 * @author zz
 *
 */
public class ImageHelper {

	private ImageHelper() {
	}

	static byte[] getImageBinary(BufferedImage bi) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(bi, "jpg", baos);
		} catch (IOException e) {
			throw new RuntimeException("写图片流异常", e);
		}
		byte[] bytes = baos.toByteArray();
		return bytes;
	}

	public static String encodeBase64(BufferedImage image) {
		return Base64.getEncoder().encodeToString(getImageBinary(image));
	}
	public static String encodeBase64(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}
}
