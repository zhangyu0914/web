package com.yw.common.beansUtils.utils.validation;

import java.util.Map;

/**
 *<pre>
 * 功       能: 检验结果
 * 涉及版本: V1.0.0 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-1-27下午5:20:52
 * Q    Q: 308053847
 *</pre>
 */
public class ValidationResult {
	
	private boolean hasErrors;// 校验结果是否有错
	private Map<String, String> errorMsg;// 校验错误信息

	public boolean isHasErrors() {
		return hasErrors;
	}

	public void setHasErrors(boolean hasErrors) {
		this.hasErrors = hasErrors;
	}

	public Map<String, String> getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(Map<String, String> errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return "ValidationResult [hasErrors=" + hasErrors + ", errorMsg="
				+ errorMsg + "]";
	}
}
