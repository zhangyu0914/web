package com.yw.common.beansUtils.utils.file;

import java.io.Serializable;

/**
 * 上传文件Bean
 * 
 * @author Zhaoxin.Zhao
 * 
 */
public class UploadFile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6979083774696459150L;

	private Long fileSize; // 文件大小，单位为K
	private String fileName; // 文件名称
	private String filePath; // 文件路径
	private String thumPath;// 缩略图路径

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getThumPath() {
		return thumPath;
	}

	public void setThumPath(String thumPath) {
		this.thumPath = thumPath;
	}

}
