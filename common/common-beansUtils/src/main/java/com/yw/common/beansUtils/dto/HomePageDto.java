package com.yw.common.beansUtils.dto;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.github.abel533.echarts.Option;
import com.yw.common.beansUtils.entity.HomePageEntity;
import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 *<pre>
 * 功       能: 首页
 * 涉及版本: V1.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-03-13 22:36:25
 * Q    Q: 308053847
 *</pre>
 */
public class HomePageDto extends BaseDto {

	private static final long serialVersionUID = -2978831570220L;
	
	private Integer warningCount;//警告数量
	private Integer bindingCount;//绑定数量
	private Integer preceptionCount;//感知
	private Integer getwayCount;//网关
	private Integer meory;//内存
	private Integer cpu;//CPU
	private Integer disk;//磁盘
	private String[] report;
	private Map<String, String> map;
	private Integer[] bindingCountArray;//绑定数量
	private Integer[] totalCountArray;//总数
	
	//无参构造方法
	public HomePageDto() {
		super();
	}
	
	public HomePageDto(HomePageEntity data) {
		super();

		if (data != null) {
			this.setWarningCount(data.getWarningCount());
			this.setBindingCount(data.getBindingCount());
			this.setPreceptionCount(data.getPreceptionCount());
			this.setGetwayCount(data.getGetwayCount());
			this.setMeory(data.getMeory());
			this.setCpu(data.getCpu());
			this.setDisk(data.getDisk());
			this.setReport(data.getOption());
			this.setMap(data.getMap());
			this.setBindingCountArray(data.getBindingCountArray());
			this.setTotalCountArray(data.getTotalCountArray());
		}
	}

	public Integer getWarningCount() {
		return warningCount;
	}

	public void setWarningCount(Integer warningCount) {
		this.warningCount = warningCount;
	}

	public Integer getBindingCount() {
		return bindingCount;
	}

	public void setBindingCount(Integer bindingCount) {
		this.bindingCount = bindingCount;
	}

	public String[] getReport() {
		return report;
	}

	public void setReport(List<Option> option) {
		if (!StringUtils.isBlank(option)
				&& !option.isEmpty()) {
			
			this.report = new String[option.size()];
			int i=0;
			for (Option obj : option) {
				this.report[i++] = "option="+JSON.toJSONString(obj);
			}
		}
		this.report = report;
	}

	public Integer getPreceptionCount() {
		return preceptionCount;
	}

	public void setPreceptionCount(Integer preceptionCount) {
		this.preceptionCount = preceptionCount;
	}

	public Integer getGetwayCount() {
		return getwayCount;
	}

	public void setGetwayCount(Integer getwayCount) {
		this.getwayCount = getwayCount;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public Integer getMeory() {
		return meory;
	}

	public void setMeory(Integer meory) {
		this.meory = meory;
	}

	public Integer getCpu() {
		return cpu;
	}

	public void setCpu(Integer cpu) {
		this.cpu = cpu;
	}

	public Integer getDisk() {
		return disk;
	}

	public void setDisk(Integer disk) {
		this.disk = disk;
	}

	public Integer[] getBindingCountArray() {
		return bindingCountArray;
	}

	public void setBindingCountArray(Integer[] bindingCountArray) {
		this.bindingCountArray = bindingCountArray;
	}

	public Integer[] getTotalCountArray() {
		return totalCountArray;
	}

	public void setTotalCountArray(Integer[] totalCountArray) {
		this.totalCountArray = totalCountArray;
	}
	
}
