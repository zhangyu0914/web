package com.yw.common.beansUtils.dto;

import java.sql.Timestamp;

import com.yw.common.beansUtils.dto.BaseDto;
import com.yw.common.beansUtils.entity.PushMsgEntity;
import com.yw.common.beansUtils.utils.date.DateUtils;

/**
 * <pre>
 * 功       能: 推送消息
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-20 16:59:13
 * Q    Q: 308053847
 * </pre>
 */
public class PushMsgDto extends BaseDto {

	private static final long serialVersionUID = -1109795279518681480L;
	private String tid;// 主键
	private String appInstanceId;//
	private String appName;// 应用名称
	private String appReportName;//实例名称
	private String sn;// 消息
	private String msg;// 消息
	private String msgDsc;//消息缩写
	private String createTime;//创建时间

	public PushMsgDto() {
		super();
	}

	public PushMsgDto(PushMsgEntity data) {
		super();

		if (data != null) {
			
			this.setTid(data.getTid());
			this.setAppName(data.getAppName());
			try {
				this.setCreateTime(DateUtils.format(data.getCreateTime(), null));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppReportName() {
		return appReportName;
	}

	public void setAppReportName(String appReportName) {
		this.appReportName = appReportName;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getAppInstanceId() {
		return appInstanceId;
	}

	public void setAppInstanceId(String appInstanceId) {
		this.appInstanceId = appInstanceId;
	}

	public String getMsgDsc() {
		return msgDsc;
	}

	public void setMsgDsc(String msgDsc) {
		this.msgDsc = msgDsc;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


}
