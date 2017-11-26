package com.yw.common.beansUtils.utils.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.Constants.ErrorCode;
import com.yw.common.beansUtils.utils.date.DateUtils;
import com.yw.common.beansUtils.utils.enums.ConfigurationEnum;
import com.yw.common.beansUtils.utils.enums.FileResourceTypeEnum;
import com.yw.common.beansUtils.utils.image.ImageUtils;
import org.apache.log4j.Logger;import com.yw.common.beansUtils.utils.Constants;
import com.yw.common.beansUtils.utils.resultUtil.IhygeiaResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 * 文件工具类
 * 
 * @author Zhaoxin.Zhao
 * 
 */
public class FileUtil {

	private final Logger log = Logger.getLogger(Constants.LOG_MODEL);

	private static final String FILE_TYPE_IMAGE = "image";
	private static final String FILE_TYPE_VIDEO = "video";
	private static final String FILE_TYPE_FILE = "file";

	// 支持的图片格式
	private static final String DEFAULT_IMAGES = "gif,jpg,jpeg,png,bmp";
	// 支持的视频格式
	private static final String DEFAULT_VIDEO = "swf,flv,wav,wma,wmv,avi,mpg,rm,rmvb,mp4";
	// 支持的文件格式
	private static final String DEFAULT_FILES = DEFAULT_IMAGES + ","
			+ DEFAULT_VIDEO + ",txt,pdf";
	// 文件的默认最大值10M
	private static final int DEFAULT_FILE_MAX_SIZE = 10485760;

	// 上传默认文件截取字符串
	public static final String DEFAULT_STAMP = "resource";

	// 本地测试绝对地址
	// public static final String UPLOAD_FILE_REAL_DIR = "E:/images/resource";

	private FileUtil() {
	}

	private static class FileUtilHandle {
		public static FileUtil instance = new FileUtil();
	}

	public static FileUtil getInstance() {
		return FileUtilHandle.instance;
	}

	/**
	 * 上传图片支持的格式
	 * 
	 * @return
	 */
	public String getImagesSuffix() {

		String images = DEFAULT_IMAGES;

		try {
			String suffix = ConfigurationEnum.UPLOAD_FILE_IMAGES_SUFFIX
					.getValue();
			if (StringUtils.isNotBlank(suffix))
				images = suffix;
		} catch (Exception e) {
			log.error("文件上传获取上传图片支持的格式异常：" + e.getMessage(), e);
		}
		return images.toLowerCase();
	}

	/**
	 * 上传视频支持的格式
	 * 
	 * @return
	 */
	public String getVideosSuffix() {

		String videos = DEFAULT_VIDEO;
		
		try {
			String suffix = ConfigurationEnum.UPLOAD_FILE_VIDEOS_SUFFIX
					.getValue();
			if (StringUtils.isNotBlank(suffix))
				videos = suffix;
		} catch (Exception e) {
			log.error("文件上传获取上传视频支持的格式异常：" + e.getMessage(), e);
		}
		return videos.toLowerCase();

	}

	/**
	 * 上传文件支持的格式，包含图片、视频文件的格式
	 * 
	 * @return
	 */
	public String getFilesSuffix() {

		String files = DEFAULT_FILES;
		try {
			String suffix = ConfigurationEnum.UPLOAD_FILE_FILES_SUFFIX
					.getValue();
			if (StringUtils.isNotBlank(suffix)) {
				files = getImagesSuffix() + "," + getVideosSuffix() + ","
						+ suffix;
			}
		} catch (Exception e) {
			log.error("文件上传获取上传文件支持的格式异常：" + e.getMessage(), e);
		}
		return files.toLowerCase();
	}

	/**
	 * 上传文件的最大大小
	 * 
	 * @return
	 */
	public int getFileMaxSize() {

		try {
			String maxSizeStr = ConfigurationEnum.UPLOAD_FILE_MAX_SIZE
					.getValue();
			if (StringUtils.isNotBlank(maxSizeStr))
				return Integer.parseInt(maxSizeStr);
		} catch (Exception e) {
			log.error("文件上传获取上传文件的最大大小异常：" + e.getMessage(), e);
		}
		return DEFAULT_FILE_MAX_SIZE;
	}

	/**
	 * 上传文件路径截取标记符
	 * 
	 * @return
	 */
	public String getStamp() {

		try {
			String stamp = ConfigurationEnum.UPLOAD_FILE_STAMP
					.getValue();
			if (StringUtils.isNotBlank(stamp))
				return stamp;
		} catch (Exception e) {
			log.error("文件上传获取上传文件路径截取标记符异常：" + e.getMessage(), e);
		}

		return DEFAULT_STAMP;
	}

	/**
	 * 获取上传文件的外网访问地址
	 * 
	 * @return
	 */
	public String getUploadFileOutterNet() {
		return ConfigurationEnum.UPLOAD_FILE_OUTER_NET.getValue();
	}

	/**
	 * 上传图片服务器的绝对地址
	 * 
	 * @return
	 */
	public String getUploadFileRealDir() {
		return ConfigurationEnum.UPLOAD_FILE_REAL_DIR.getValue();
	}

	/**
	 * 获得文件的后缀名
	 * 
	 * @param fileName
	 * @return 如：.jpg
	 */
	public static String getSuffix(String fileName) {

		if (StringUtils.isBlank(fileName))
			return "";
		int beginIndex = fileName.lastIndexOf(".");
		if (-1 == beginIndex)
			return null;
		return fileName.substring(beginIndex, fileName.length()).toLowerCase();
	}
	
	public static String getFileName(String fileName, String splitChar) {

		if (StringUtils.isBlank(fileName))
			return "";
		String[] beginIndex = fileName.split(splitChar);
		return beginIndex[beginIndex.length - 1];
	}

	/**
	 * 获得文件的后缀名称
	 * 
	 * @param fileName
	 * @return 如：jpg
	 */
	public static String getSuffixName(String fileName) {

		if (StringUtils.isBlank(fileName))
			return "";
		int beginIndex = fileName.lastIndexOf(".");
		if (-1 == beginIndex)
			return "";
		return fileName.substring(beginIndex + 1, fileName.length())
				.toLowerCase();
	}

	/**
	 * <pre>
	 * 说       明: 上传单个文件
	 * @param request
	 * @param logoRealPathDir : /mnt/opt/resource/upload/
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-6-16下午2:43:26
	 * </pre>
	 */
	public IhygeiaResultUtil uploadFile(HttpServletRequest request)
			throws Exception {

		IhygeiaResultUtil result = uploadFiles(request);
		IhygeiaResultUtil resultOne = new IhygeiaResultUtil();
		List<UploadFile> list = (List<UploadFile>) result.getData();
		if (null != list && list.size() > 0) {
			resultOne.setCodeEnum(ErrorCode.SUCCESS);
			resultOne.setData(list.get(0));
		}
		return resultOne;
	}

	/**
	 * <pre>
	 * 说       明: 上传多个文件
	 * @param request
	 * @param logoRealPathDir : /mnt/opt/resource/upload/
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-6-16下午2:43:26
	 * </pre>
	 */
	public IhygeiaResultUtil uploadFiles(HttpServletRequest request)
			throws Exception {

		// 上传文件集合
		List<UploadFile> list = new ArrayList<UploadFile>();
		IhygeiaResultUtil result = new IhygeiaResultUtil();
		result.setData(list);
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		boolean isMultipart = commonsMultipartResolver.isMultipart(request);
		if (!isMultipart) {
			log.error("无上传的文件数据，或者重复提交");
			return result;
		}

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> fileList = multipartRequest.getFiles("uploadFile");

		if (null == fileList || fileList.size() <= 0) {
			log.error("未获取上传的文件数据");
			return result;
		}

		// 上传文件的绝对地址
		String realDir = getUploadFileRealDir();

		// 保存图片的根路径
		File logoSaveFile = new File(realDir);
		if (!logoSaveFile.exists()) {
			logoSaveFile.mkdirs();
		}

		UploadFile uploadFile = null;
		int size = fileList.size();
		for (int i = 0; i < size; i++) {
			// 原始文件(上传文件)
			MultipartFile multipartFile = fileList.get(i);
			String srcFileName = multipartFile.getOriginalFilename();// 文件名
			long fileSize = multipartFile.getSize(); // 文件大小

			if (fileSize > getFileMaxSize()) {
				result.setCodeEnum(ErrorCode.FAILURE);// 上传的文件大小大于最大值
				log.error("上传的文件大小大于最大值：" + fileSize);
				return result;
			}
			if (!isZhiChi(srcFileName)) {
				result.setCodeEnum(ErrorCode.FAILURE);// 不支持的文件格式
				log.error("不支持的文件格式：" + srcFileName);
				return result;
			}

			String dir = getFileType(srcFileName);

			String simulationDir = "module_"
					+ request.getRequestURI().split("/")[4].toUpperCase();
			String date = DateUtils.getSysStringTime("yyyy-MM-dd");
			String dateSimulationDir = simulationDir.toLowerCase() + "/" + dir
					+ "/" + date;

			String fileDir = realDir + "/" + dateSimulationDir + "/";
			// 保存图片的根路径
			File logoSaveFileDir = new File(fileDir);
			if (!logoSaveFileDir.exists()) {
				logoSaveFileDir.mkdirs();
			}
			// 绝对路径
			String filePath = fileDir + UUID.randomUUID().toString()
					+ getSuffix(srcFileName);

			// 转换后的最新文件
			File newFile = new File(filePath);
			multipartFile.transferTo(newFile);
			int beginIndex = filePath.indexOf(getStamp());
			String uploadFilePath = filePath.substring(beginIndex - 1,
					filePath.length());// 相对路径
			String thumPath = "";// 缩列图

			int fileType = getResourceType(filePath);
			if (fileType == FileResourceTypeEnum.IMG.getCode()) {
				thumPath = ImageUtils.thumbnailPix(filePath);// 缩略图
			} else if (fileType == FileResourceTypeEnum.VIDEO.getCode()) {
				// 这里生成视频缩列图
			}

			uploadFile = new UploadFile();
			uploadFile.setFileName(srcFileName);
			uploadFile.setFilePath(uploadFilePath);
			uploadFile.setThumPath(thumPath);
			uploadFile.setFileSize(fileSize);
			list.add(uploadFile);
		}

		result.setCodeEnum(ErrorCode.SUCCESS);
		result.setData(list);
		return result;
	}

	/**
	 * 是否支持文件的格式
	 * 
	 * @param fileName
	 * @param dir
	 * @return
	 */
	private boolean isZhiChi(String fileName) {

		int index = -1;
		String suffixName = getSuffixName(fileName);
		if (StringUtils.isNotBlank(suffixName)) {
			index = getFilesSuffix().indexOf(suffixName);
		}
		if (index >= 0)
			return true;

		return false;

	}

	/**
	 * 获取文件类型
	 * 
	 * @param fileName
	 *            文件名称
	 * @return image,video,file
	 */
	public String getFileType(String fileName) {

		String suffixName = getSuffixName(fileName);
		int index = getImagesSuffix().indexOf(suffixName);
		if (index >= 0 && StringUtils.isNotBlank(suffixName)) {
			return FILE_TYPE_IMAGE;
		}

		index = getVideosSuffix().indexOf(suffixName);
		if (index >= 0 && StringUtils.isNotBlank(suffixName)) {
			return FILE_TYPE_VIDEO;
		}

		return FILE_TYPE_FILE;
	}

	/**
	 * 获取资源文件类型代码
	 * 
	 * @param fileName
	 *            文件名称
	 * @return -1未知、1图片、2视频、3文件
	 */
	public int getResourceType(String fileName) {

		String suffixName = getSuffixName(fileName);

		if (StringUtils.isBlank(suffixName)) {
			return FileResourceTypeEnum.UNKNOW.getCode();
		}
		int index = getImagesSuffix().indexOf(suffixName);
		if (index >= 0) {
			return FileResourceTypeEnum.IMG.getCode();
		}
		index = getVideosSuffix().indexOf(suffixName);
		if (index >= 0) {
			return FileResourceTypeEnum.VIDEO.getCode();
		}
		return FileResourceTypeEnum.FILE.getCode();
	}
	
	/**
	 *<pre>
	 * 说       明:  TODO FILEUTIL
	 * @param oldPath
	 * @param newPath
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-9-23上午11:57:29
	 *</pre>
	 */
	public static void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) {
				InputStream inStream = new FileInputStream(oldPath);
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread;
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param path TODO FILEUTIL
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-9-23下午1:22:07
	 *</pre>
	 */
	public static void renameFiles(String path) {
		File allFile = new File(path);
		String old = "Company";
		String rename = "baseStatistics";
		String renameFile = "BaseStatistics";
		if (allFile != null) {
			for (File f : allFile.listFiles()) {
				if (f.isDirectory()) {
					File newDirectory = new File(allFile.getPath() + "\\"
							+ rename);
					f.renameTo(newDirectory);
					renameFiles(newDirectory.getPath());
				} else {
					File newDirectory = new File(f.getPath()
							+ f.getName().replaceAll(old, renameFile));
					f.renameTo(newDirectory);
				}
			}
		}
	}
	
	/**
	 * <pre>
	 * 说       明: 导出文件时，对文件名进行编码
	 * @param fileName TODO FILEUTIL
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-7-19下午3:07:17
	 * </pre>
	 */
	public static String encodeFileName(String fileName,
			HttpServletRequest request) throws UnsupportedEncodingException {
		try {
			String agent = request.getHeader("USER-AGENT");

			if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
				fileName = URLEncoder.encode(fileName, "UTF8");
			} else if ((agent != null) && (-1 != agent.indexOf("Mozilla"))) {
				fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			try {
				fileName = new String(
						fileName.getBytes(Constants.ENCODING_UTF8),
						"iso-8859-1");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
				return null;
			}
			return null;
		}
		return fileName;
	}

	/**
	 * <pre>
	 * 说       明: 读取文件 TODO FILEUTIL
	 * @param dir
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-2-27下午2:52:44
	 * </pre>
	 */
	public static String readFile(File dir) throws Exception {
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(new FileInputStream(dir),
				"UTF-8"));
		String tempString = "";
		StringBuffer fileContent = new StringBuffer();
		while ((tempString = br.readLine()) != null) {

			fileContent.append(tempString + "\n");
		}
		br.close();
		return fileContent.toString();
	}
	
	public static FileInputStream readFileStr(String dir) throws Exception {
		return new FileInputStream(dir);
	}
	
	public static String readFile(InputStream inputStream) throws Exception {
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
		String tempString = "";
		StringBuffer fileContent = new StringBuffer();
		while ((tempString = br.readLine()) != null) {

			fileContent.append(tempString + "\n");
		}
		br.close();
		return fileContent.toString();
	}
	
	/**
	 *<pre>
	 * 说       明: 获取WEB CLASSES路径
	 * @param cls
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-10-13上午11:17:40
	 *</pre>
	 */
	public static URL getClasses(Class cls) throws Exception {
		return cls.getResource("/");
	}
	
	/**
	 *<pre>
	 * 说       明: 
	 * @param dir TODO FILEUTIL
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-9-23下午1:27:06
	 *</pre>
	 */
	public static List<String> readFileForDir(String dir) throws Exception {
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(new FileInputStream(dir),
				"UTF-8"));
		String tempString = "";
		List<String> list = new ArrayList<String>();
		while ((tempString = br.readLine()) != null) {

			list.add(tempString);
		}
		br.close();
		return list;
	}
	
	/**
	 * <pre>
	 * 说       明: 读取文件内容
	 * @param filePath TODO FILEUTIL
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-2-27下午2:42:05
	 * </pre>
	 */
	public static String readFile(String filePath) throws Exception {
		File dir = new File(filePath);
		if (!dir.exists()) {
			return null;
		}
		BufferedReader br = null;
		br = new BufferedReader(new InputStreamReader(new FileInputStream(dir),
				"UTF-8"));
		String tempString = "";
		StringBuffer fileContent = new StringBuffer();
		while ((tempString = br.readLine()) != null) {

			fileContent.append(tempString + "\n");
		}
		br.close();
		return fileContent.toString();
	}
	
	/**
	 * <pre>
	 * 说       明: 创建文件
	 * @param fileContent TODO FILEUTIL
	 * @param fileName
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-2-27下午2:46:34
	 * </pre>
	 */
	public static boolean createFile(String fileContent, String fileName)
			throws Exception {
		
		File file = null;
		file = new File(fileName.substring(0,fileName.replace("/", "\\").lastIndexOf("\\")));//如果目录不存在,则创建
		if (!file.exists()) {
			file.mkdirs();//连续创建多个不存在的目录
		}
		BufferedWriter bw= new BufferedWriter(new FileWriter(fileName));
		bw.write(fileContent);
		bw.close();
		return true;
	}
	
	/**
	 * <pre>
	 * 说       明: 根据二进制数据创建文件
	 * 涉及版本: V2.0.0  
	 * 创  建  者: Vickey
	 * 日       期: 2017年9月7日下午12:00:31
	 * Q    Q: 308053847
	 * </pre>
	 */
	public static File createFile(byte[] fileContent, String fileName, String path)
			throws Exception {
		
		File file = new File(path + "/" + fileName);
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		fileOutputStream.write(fileContent);
		fileOutputStream.close();
		return file;
	}
	
	/**
	 * <pre>
	 * 说       明: 修改文件的扩展名
	 * @param fileName TODO FILEUTIL
	 * @param newName
	 * @param newExtension
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-3-1下午3:50:50
	 * </pre>
	 */
	public static String renameFileName(String fileName, String newName,
			String newExtension) throws Exception {
		if (fileName == null || fileName.equals("")) {
			return null;
		}
		String name = fileName.substring(0, fileName.lastIndexOf("."));
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length());
		if ((newName == null || newName.equals("")) && newExtension != null
				&& !newExtension.trim().equals("")) {
			return name + "." + newExtension;
		}
		if ((newName != null && !newName.equals("")) && newExtension == null
				|| newExtension.trim().equals("")) {
			return newName + "." + extension;
		}
		return null;
	}
	
	/**
	 * <pre>
	 * 说       明: 创建目录
	 * @param filePath TODO FILEUTIL
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-3-2上午10:34:23
	 * </pre>
	 */
	public static boolean createDir(String filePath) throws Exception {
		File file = new File(filePath);// 如果目录不存在,则创建
		if (!file.exists()) {
			return file.mkdirs();// 连续创建多个不存在的目录
		}
		return true;
	}
	
	/**
	 * <pre>
	 * 说       明: 上传图片 TODO FILEUTIL
	 * @param request
	 * @param logoRealPathDir : /mnt/opt/resource/upload/
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-6-16下午2:43:26
	 * </pre>
	 */
	public static String uploadFile(HttpServletRequest request,
			String logoRealPathDir) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = multipartRequest.getFile("uploadFile");
		if (!StringUtils.isBlankOr(new Object[] { multipartFile,
				multipartFile.getOriginalFilename() })) {
			File logoSaveFile = new File(logoRealPathDir);
			if (!logoSaveFile.exists()) {
				logoSaveFile.mkdirs();
			}

			String simulationDir = "module_"
					+ request.getRequestURI().split("/")[4].toUpperCase();
			String date = DateUtils.getSysStringTime("yyyy-MM-dd");
			String dateSimulationDir = simulationDir.toLowerCase() + "/" + date;

			String filePath = logoRealPathDir + "/" + dateSimulationDir + "/"
					+ multipartFile.getOriginalFilename();
			File newFile = new File(filePath);

			multipartFile.transferTo(newFile);
			return filePath;
		}
		return null;
	}
	
	/**
	 * <pre>
	 * 说       明: 获取文件的扩展名
	 * @param fileName TODO FILEUTIL
	 * @return
	 * @throws Exception
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-3-16下午2:15:49
	 * </pre>
	 */
	public static String getExtension(String fileName) throws Exception {
		if (fileName == null || fileName.equals("")) {
			return null;
		}
		return fileName.substring(fileName.lastIndexOf("."), fileName.length())
				.toLowerCase();
	}
	

	public static void main(String[] args) {

		String logUrl = "/qrcode/72300667-8ff3-409e-aaaf-53f4e82f03ae.png";
		System.out.println(FileUtil.saveToFile(logUrl, "d:\\", null));

	}
	
	/**
	 *<pre>
	 * 说       明:  写文件
	 * @param oldPath
	 * @param newPath
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-9-23上午11:57:29
	 *</pre>
	 */
	public static void writeFile(String filePath, byte[] input) {
		try {
			
			FileOutputStream fs = new FileOutputStream(filePath);
			fs.write(input);
			fs.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <pre>
	 * 说       明: 下载网络上的图片保存到本地
	 * 涉及版本: V3.3.0  
	 * 创  建  者: Vickey
	 * 日       期: 2016年9月19日上午9:35:38
	 * Q    Q: 308053847
	 * </pre>
	 * @param fileName TODO
	 */
	public static String saveToFile(String imgUrl, String saveDir, String fileName) {
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		int BUFFER_SIZE = 1024;
		byte[] buf = new byte[BUFFER_SIZE];
		int size = 0;
		if (fileName == null || fileName.equals("")) {
			fileName = FileUtil.getFileName(imgUrl, "/");
		}
		try {
			url = new URL(imgUrl);
			httpUrl = (HttpURLConnection) url.openConnection();
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
			fos = new FileOutputStream(fileName);
			while ((size = bis.read(buf)) != -1) {
				fos.write(buf, 0, size);
			}
			fos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassCastException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
				bis.close();
				httpUrl.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		return fileName;
	}
	
	/**
	 * <pre>
	 * 说       明: 
	 * 涉及版本: V3.3.0  
	 * 创  建  者: Vickey
	 * 日       期: 2016年9月26日下午7:32:24
	 * Q    Q: 308053847
	 * </pre>
	 */
	public static String getFile(byte[] bfile, String filePath,String fileName) {  
        BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;  
        String path = filePath+"\\"+fileName;
        try {  
            File dir = new File(filePath);  
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在  
                dir.mkdirs();  
            }  
            file = new File(path);  
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(bfile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (bos != null) {  
                try {  
                    bos.close();  
                } catch (IOException e1) {  
                   e1.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }
		return path;  
    } 
	
	/**
	 * <pre>
	 * 说       明: 下载网络上的图片并转换成二进制数字
	 * 涉及版本: V3.3.0  
	 * 创  建  者: Vickey
	 * 日       期: 2016年9月19日上午9:35:38
	 * Q    Q: 308053847
	 * </pre>
	 * @param fileName TODO
	 */
	public static byte[] getNetFile(String imgUrl) {
		BufferedInputStream bis = null;
		HttpURLConnection httpUrl = null;
		URL url = null;
		int BUFFER_SIZE = 1024;
		byte[] temp = new byte[BUFFER_SIZE];
		ByteArrayOutputStream out = null;
		try {
			url = new URL(imgUrl);
			httpUrl = (HttpURLConnection) url.openConnection();
			httpUrl.connect();
			bis = new BufferedInputStream(httpUrl.getInputStream());
			out = new ByteArrayOutputStream(BUFFER_SIZE);
			 int size = 0;  
		        while ((size = bis.read(temp)) != -1) {  
		            out.write(temp, 0, size);  
		        }  
		        bis.close();  
		        byte[] content = out.toByteArray();  
		        return content;  
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassCastException e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
				out.close();
				httpUrl.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static byte[] getFile(String imgUrl) {
		BufferedInputStream bis = null;
		int BUFFER_SIZE = 1024;
		byte[] temp = new byte[BUFFER_SIZE];
		ByteArrayOutputStream out = null;
		try {
			InputStream input = new FileInputStream(imgUrl);
			bis = new BufferedInputStream(input);
			out = new ByteArrayOutputStream(BUFFER_SIZE);
			 int size = 0;  
		        while ((size = bis.read(temp)) != -1) {  
		            out.write(temp, 0, size);  
		        }  
		        bis.close();  
		        byte[] content = out.toByteArray();  
		        return content;  
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassCastException e) {
			e.printStackTrace();
		} finally {
			try {
				bis.close();
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
