package com.yw.common.beansUtils.utils;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.yw.common.beansUtils.utils.file.FileUtil;
import com.yw.common.beansUtils.utils.image.ImageUtils;

/**
 *<pre>
 * 功       能: 二维码生成器
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-11-9下午5:09:43
 * Q    Q: 308053847
 *</pre>
 */
public class ZxingUtil {

	private static final String CHARSET = "utf-8";
	private static final String FORMAT_NAME = "JPG";
	// 二维码尺寸
//	private static final int QRCODE_SIZE = 900;
	// LOGO宽度
	private static final int WIDTH = 195;
	// LOGO高度
	private static final int HEIGHT = 195;

	private static BufferedImage createImage(String content, int picWidth,
			int picHeight, boolean logIsUrlPath, String logUrl, String logFileName, boolean needCompress) throws Exception {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
				BarcodeFormat.QR_CODE, picWidth, picHeight, hints);
		bitMatrix = deleteWhite(bitMatrix);
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
						: 0xFFFFFFFF);
			}
		}
		if (logUrl == null || "".equals(logUrl)) {
			return image;
		}
		if (logIsUrlPath) {
			logUrl = FileUtil.saveToFile(logUrl, "/qrcode/", logFileName);
		}
		// 插入图片
		ZxingUtil.insertImage(image, picWidth, picHeight, logUrl, needCompress);
		return image;
	}
	
	/**
	 *<pre>
	 * 说       明: 去白边
	 * @param matrix
	 * @return
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-12-8下午6:26:43
	 *</pre>
	 */
	public static BitMatrix deleteWhite(BitMatrix matrix){
		int[] rec = matrix.getEnclosingRectangle();
		int resWidth = rec[2] + 1;
		int resHeight = rec[3] + 1;

		BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
		resMatrix.clear();
		for (int i = 0; i < resWidth; i++) {
			for (int j = 0; j < resHeight; j++) {
				if (matrix.get(i + rec[0], j + rec[1]))
					resMatrix.set(i, j);
			}
		}
		return resMatrix;
	}

	/**
	 * 插入LOGO
	 * 
	 * @param source
	 *            二维码图片
	 * @param picWidth TODO
	 * @param picHeight TODO
	 * @param imgPath
	 *            LOGO图片地址
	 * @param needCompress
	 *            是否压缩
	 * @throws Exception
	 */
	private static void insertImage(BufferedImage source, int picWidth,
			int picHeight, String imgPath, boolean needCompress) throws Exception {
		File file = new File(imgPath);
		if (!file.exists()) {
			System.err.println("" + imgPath + "   该文件不存在！");
			return;
		}
		Image src = ImageIO.read(new File(imgPath));
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		if (needCompress) { // 压缩LOGO
			if (width > WIDTH) {
				width = WIDTH;
			}
			if (height > HEIGHT) {
				height = HEIGHT;
			}
			Image image = src.getScaledInstance(width, height,
					Image.SCALE_SMOOTH);
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			src = image;
		}
		// 插入LOGO
		Graphics2D graph = source.createGraphics();
		int x = (picWidth - width) / 2 - 30;//减30，是因为去掉了白边
		int y = (picHeight - height) / 2 - 30;//减30，是因为去掉了白边
		graph.drawImage(src, x, y, width, height, null);
		Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
		graph.setStroke(new BasicStroke(3f));
		graph.draw(shape);
		graph.dispose();
	}

	/**
	 * 生成二维码(内嵌LOGO)
	 * 
	 * @param content
	 *            内容
	 * @param width TODO
	 * @param height TODO
	 * @param logPath
	 *            LOGO地址
	 * @param logIsUrlPath TODO
	 * @param logFileName TODO
	 * @param imgPath
	 *            存放目录
	 * @param needCompress
	 *            是否压缩LOGO
	 * @throws Exception
	 */
	public static void encode(String content, int width, int height,
			String logPath, boolean logIsUrlPath, String logFileName, String imgPath, boolean needCompress) throws Exception {
		BufferedImage image = ZxingUtil.createImage(content, width,
				height, logIsUrlPath, logPath, logFileName, needCompress);
		ImageIO.write(image, FORMAT_NAME, new File(imgPath));
	}

	/**
	 * 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
	 * 
	 * @author lanyuan Email: mmm333zzz520@163.com
	 * @date 2013-12-11 上午10:16:36
	 * @param destPath
	 *            存放目录
	 */
	public static void mkdirs(String destPath) {
		File file = new File(destPath);
		// 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
		if (!file.exists() && !file.isDirectory()) {
			file.mkdirs();
		}
	}


	/**
	 * 解析二维码
	 * 
	 * @param file
	 *            二维码图片
	 * @return
	 * @throws Exception
	 */
	public static String decode(File file) throws Exception {
		BufferedImage image;
		image = ImageIO.read(file);
		if (image == null) {
			return null;
		}
		BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(
				image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result;
		Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
		hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
		result = new MultiFormatReader().decode(bitmap, hints);
		String resultStr = result.getText();
		return resultStr;
	}

	/**
	 * 解析二维码
	 * 
	 * @param path
	 *            二维码图片地址
	 * @return
	 * @throws Exception
	 */
	public static String decode(String path) throws Exception {
		return ZxingUtil.decode(new File(path));
	}

	public static void main(String[] args) throws Exception {
		String text = "薯　灯可分列式本上楞珂要瓜熟蒂落！000000000000000";
//		new ByteArrayInputStream(logo)
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		File file = new File("F:\\temp\\1483446957792.jpg");
		   FileInputStream in = null;
		   
		   try {
		   in = new FileInputStream(file);
		   byte[] buffer = new byte[in.available()];
		   in.read(buffer);
		   out.write(buffer);
		   } catch (Exception e) {
		   e.printStackTrace();
		   } finally {
		   try {
		   if (in != null)
		   in.close();
		   } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		   }
		   }
		FileUtil.writeFile("F:\\temp\\logo.jpg",ZxingUtil.encode(text, 900, 900, out.toByteArray(), true));
		
	}
	
	private static BufferedImage createImage(String content, int picWidth,
			int picHeight, byte[] logo, boolean needCompress) throws Exception {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
		hints.put(EncodeHintType.MARGIN, 0);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
				BarcodeFormat.QR_CODE, picWidth, picHeight, hints);
		bitMatrix = deleteWhite(bitMatrix);
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
						: 0xFFFFFFFF);
			}
		}
		if (logo == null ) {
			return image;
		}
		
		// 插入图片
		ZxingUtil.insertImage(image, picWidth, picHeight, logo, needCompress);
		return image;
	}
	
	
	/**
	 * 插入LOGO
	 * 
	 * @param source
	 *            二维码图片
	 * @param picWidth TODO
	 * @param picHeight TODO
	 * @param imgPath
	 *            LOGO图片地址
	 * @param needCompress
	 *            是否压缩
	 * @throws Exception
	 */
	private static void insertImage(BufferedImage source, int picWidth,
			int picHeight, byte[] logo, boolean needCompress) throws Exception {
		BufferedImage input = ImageIO.read(new ByteArrayInputStream(logo));
		//设置圆角边框
		input=ImageUtils.setRadius(input);
		Image src = input;
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		if (needCompress) { // 压缩LOGO
			if (width > WIDTH) {
				width = WIDTH;
			}
			if (height > HEIGHT) {
				height = HEIGHT;
			}
			Image image = src.getScaledInstance(width, height,
					Image.SCALE_SMOOTH);
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			src = image;
		}

		// 插入LOGO
		Graphics2D graph = source.createGraphics();
		int x = (picWidth - width) / 2 - 30;//减30，是因为去掉了白边
		int y = (picHeight - height) / 2 - 30;//减30，是因为去掉了白边
		
		graph.drawImage(src, x, y, width, height, null);
		Shape shape = new RoundRectangle2D.Float(x, y, width, width, 1, 1);
//		graph.setStroke(new BasicStroke(1f));
		graph.draw(shape);
		graph.dispose();
	}
	
	/**
	 * 生成二维码(内嵌LOGO)
	 * @param content
	 *            内容
	 * @param width TODO
	 * @param height TODO
	 * @param logo
	 *            LOGO二进制数组
	 * @param needCompress
	 *            是否压缩LOGO
	 * @throws Exception
	 */
	public static byte[] encode(String content, int width, int height,
			byte[] logo, boolean needCompress) throws Exception {
		BufferedImage image = ZxingUtil.createImage(content, width,
				height, logo, needCompress);
		image.flush();
		int BUFFER_SIZE = 1024;
		ByteArrayOutputStream out = null;
		ImageOutputStream imOut= null; 
	    try {
			out = new ByteArrayOutputStream(BUFFER_SIZE);
			 imOut = ImageIO.createImageOutputStream(out); 
	         ImageIO.write(image, "png",imOut); 
	         return out.toByteArray(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				imOut.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	    return null;
		
		
	}
}