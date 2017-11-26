package com.yw.common.beansUtils.utils.ip;

/**
 * <pre>
 * 功   能新浪IP
 * 创建者陈林林(Vickey)
 * 日   期2014-8-20下午8:25:21
 * Q  Q308053847
 * </pre>
 */
public class SinaIp {
	private String ret;// 1
	private String star;// t"220.168.0.0"
	private String end;// "220.168.127.255"
	private String country;// "中国"
	private String province;// "湖南"
	private String city;// "长沙"
	private String district;// ""
	private String isp;// "电信"
	private String type;// ""
	private String desc;// ""
	private String ip;// ""

	public SinaIp() {
		super();
	}

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		if (this.city != null) {
			this.city += "市";
		}
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}