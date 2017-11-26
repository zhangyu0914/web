package com.yw.common.beansUtils.utils;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.yw.common.beansUtils.utils.ErrorType.ErrorTypeEnum;
import com.yw.common.beansUtils.utils.enums.ConfigurationEnum;
import com.yw.common.beansUtils.utils.result.ResultUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 * <pre>
 * 功   能: 分页
 * 创建者: 陈林林(Vickey)
 * 日   期: 2014-6-22下午12:23:51
 * Q  Q: 308053847
 * </pre>
 */
public class InterfacePage<T> implements Serializable{
	
	private static final long serialVersionUID = 3540898448501557630L;
	private Integer totalPage=1;// 总页数
	private Integer pageNo = 1;// 从第几页开始查
	private Integer pageSize = Integer.valueOf(ConfigurationEnum.PAGE_SIZE.getValue());// 每页显示多少条
	private Integer totalCount=0;// 总记录数
	// 不作为JSON字段返回
	@JSONField(serialize = false)
	private Integer start = 0;
	// 不作为JSON字段返回
	@JSONField(serialize = false)
	private int offset = 0;//偏移多少条
	// 不作为JSON字段返回
	@JSONField(serialize = false)
	private List<T> list;//使用泛型,不需要强传就可以使用数据
	// 不作为JSON字段返回
	@JSONField(serialize = false)
	private Integer limit = 0;//偏移开始条数，用于医路同行,不需要强传就可以使用数据

	
	public InterfacePage() {
		super();
	}

	public InterfacePage(Integer pageNo, Integer pageSize) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public Integer getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalPage() {
		try {
			return totalPage = (this.totalCount + this.pageSize -1 )/this.pageSize;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getStart() {
		if (this.start != null && this.start > 0) {//此判断，针对EXTJS分页
			this.pageNo = (this.start + this.pageSize) / this.pageSize;
		}
		return (this.pageNo -1) * this.pageSize;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	/**
	 *<pre>
	 * 说   明:  设置一个公用方法进行统一检验
	 * @param page
	 * @return
	 * 创建者: 陈    林(Vickey)
	 * 日   期: 2014-8-23下午6:14:27
	 *</pre>
	 */
	public static ResultUtil validatePage(InterfacePage page) throws Exception{
		ResultUtil resultUtil = new ResultUtil();
		if (StringUtils.isBlank(page) ||
				StringUtils.isBlankOr(
						page.getPageNo(),
						page.getPageSize())) {
			resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_LACK_PARAMS);
			return resultUtil;
		}
		if (page.getPageNo() < 1 || page.getPageSize() < 0) {
			resultUtil.setCode(ErrorTypeEnum.FAILURE_BASE_ILLEGAL_PARAMS);
			return resultUtil;
		}
		return null;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getOffset() {
		if (this.offset > 0) {
			if (this.getTotalCount() >= this.offset) {
				this.setTotalCount(this.getTotalCount() - this.offset);
			}
		}
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
}
