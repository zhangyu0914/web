package com.yw.common.beansUtils.entity;

import java.util.Map;

import org.hibernate.validator.constraints.Length;

import com.yw.common.beansUtils.dto.PlatformAppDto;
import com.yw.common.beansUtils.utils.JavaBeanUtil;
import com.yw.common.beansUtils.utils.string.StringUtils;

/**
 *<pre>
 * 功       能: 第三方应用数据
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-06-23 12:25:32
 * Q    Q: 308053847
 *</pre>
 */
public class PlatformAppEntity extends BaseEntity {

	private static final long serialVersionUID = -2996383864322L;
	
	private Integer status;// 状态
	private Integer sysload;// 系统值
	private Integer memory;// 内存
	private Integer disk;// 磁盘
	private Integer cpu;// CPU

	
	
	/****以下是表中不存在的属性定义*******************************************************************************/
	/****V2.0.0版本*******************************************************************************/
	private String sn;// 设备序列号
	private String start_time;// 开始日期
	private String end_time;// 结束日期
	private Integer page_no;// 第几页开始查
	private Integer page_size;// 每页查多少条
	private String cmdid;// 命令ID
	private String cmddata;// 命令数据
	private String ep;//
	private String app_instance;//应用账号
	private String appkey;//应用密钥
	private String client_ip;//客户端IP
	private String client_url;//客户端域名
	
	private Map customdata;//
	private String customdataJson;//
	
	private String profileId;//
	private String model_id;//
	private Integer version;//

	// 无参构造方法
	public PlatformAppEntity() {
		super();
	}

	// TID参数构造方法
	public PlatformAppEntity(String tid) {
		super();
		this.setTid(tid);
	}

	public PlatformAppEntity(PlatformAppDto data) {
		super();

		if (data != null) {

		}
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public Integer getPage_no() {
		return page_no;
	}

	public void setPage_no(Integer page_no) {
		this.page_no = page_no;
	}

	public Integer getPage_size() {
		return page_size;
	}

	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

	public String getCmdid() {
		return cmdid;
	}

	public void setCmdid(String cmdid) {
		this.cmdid = cmdid;
	}

	public String getCmddata() {
		return cmddata;
	}

	public void setCmddata(String cmddata) {
		this.cmddata = cmddata;
	}

	public String getApp_instance() {
		return app_instance;
	}

	public void setApp_instance(String app_instance) {
		this.app_instance = app_instance;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getClient_ip() {
		return client_ip;
	}

	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}

	public String getClient_url() {
		return client_url;
	}

	public void setClient_url(String client_url) {
		this.client_url = client_url;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSysload() {
		return sysload;
	}

	public void setSysload(Integer sysload) {
		this.sysload = sysload;
	}

	public Integer getCpu() {
		return cpu;
	}

	public void setCpu(Integer cpu) {
		this.cpu = cpu;
	}

	public Integer getMemory() {
		return memory;
	}

	public void setMemory(Integer memory) {
		this.memory = memory;
	}

	public Integer getDisk() {
		return disk;
	}

	public void setDisk(Integer disk) {
		this.disk = disk;
	}

	public Map getCustomdata() {
		return customdata;
	}

	public void setCustomdata(Map customdata) {
		this.customdata = customdata;
	}

	public String getEp() {
		return ep;
	}

	public void setEp(String ep) {
		this.ep = ep;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getModel_id() {
		return model_id;
	}

	public void setModel_id(String model_id) {
		this.model_id = model_id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCustomdataJson() {
		if (!StringUtils.isBlank(this.customdata)) {
			try {
				this.customdataJson = JavaBeanUtil.javaBeanToString(this.customdata);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return customdataJson;
	}

	public void setCustomdataJson(String customdataJson) {
		this.customdataJson = customdataJson;
	}
	
}
