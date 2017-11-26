package com.yw.common.beansUtils.utils.validation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.apache.commons.collections.CollectionUtils;

import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;

/**
 *<pre>
 * 功       能: 校验工具类,参考：http://www.tuicool.com/articles/FRFjqmA
 * 涉及版本: V1.0.0 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-1-27下午5:21:50
 * Q    Q: 308053847
 *</pre>
 */
public class ValidationUtils {
	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	/**
	 *<pre>
	 * 说       明: 手动校验数据
	 * @param obj
	 * @return
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-1-27下午5:56:01
	 *</pre>
	 */
	public static <T> ValidationResult validateEntity(T obj) {
		ValidationResult result = new ValidationResult();
		Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
		
		if (CollectionUtils.isNotEmpty(set)) {
			
			result.setHasErrors(true);
			Map<String, String> errorMsg = new HashMap<String, String>();
			for (ConstraintViolation<T> cv : set) {
				errorMsg.put(cv.getPropertyPath().toString(), ErrorTypeEnum.getZH(cv.getMessage()));
			}
			result.setErrorMsg(errorMsg);
		}
		return result;
	}

	/**
	 *<pre>
	 * 说       明: 指定实体中的属性，进行手动校验数据
	 * @param obj
	 * @param propertyName
	 * @return
	 * 涉及版本: V1.0.0 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-1-27下午5:57:07
	 *</pre>
	 */
	public static <T> ValidationResult validateProperty(T obj, String propertyName) {
		ValidationResult result = new ValidationResult();
		Set<ConstraintViolation<T>> set = validator.validateProperty(obj,propertyName, Default.class);
		if (CollectionUtils.isNotEmpty(set)) {
			result.setHasErrors(true);
			Map<String, String> errorMsg = new HashMap<String, String>();
			for (ConstraintViolation<T> cv : set) {
				errorMsg.put(propertyName, cv.getMessage());
			}
			result.setErrorMsg(errorMsg);
		}
		return result;
	}
}
