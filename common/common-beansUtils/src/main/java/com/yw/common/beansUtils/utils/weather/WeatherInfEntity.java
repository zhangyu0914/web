package com.yw.common.beansUtils.utils.weather;

/**
 *<pre>
 * 功       能: 
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-7-12下午2:26:33
 * Q    Q: 308053847
 *</pre>
 */
public class WeatherInfEntity {

	private OneDayWeatherInfEntity[] weatherInfs;
	private String dressAdvise;// 穿衣建议
	private String washCarAdvise;// 洗车建议
	private String coldAdvise;// 感冒建议
	private String sportsAdvise;// 运动建议
	private String ultravioletRaysAdvise;// 紫外线建议

	public WeatherInfEntity() {
		dressAdvise = "";
		washCarAdvise = "";
		coldAdvise = "";
		sportsAdvise = "";
		ultravioletRaysAdvise = "";
	}

	public void printInf() {

		System.out.println(dressAdvise);
		System.out.println(washCarAdvise);
		System.out.println(coldAdvise);
		System.out.println(sportsAdvise);
		System.out.println(ultravioletRaysAdvise);
		for (int i = 0; i < weatherInfs.length; i++) {
			System.out.println(weatherInfs[i]);
		}

	}

	public OneDayWeatherInfEntity[] getWeatherInfs() {
		return weatherInfs;
	}

	public void setWeatherInfs(OneDayWeatherInfEntity[] weatherInfs) {
		this.weatherInfs = weatherInfs;
	}

	public String getDressAdvise() {
		return dressAdvise;
	}

	public void setDressAdvise(String dressAdvise) {
		this.dressAdvise = dressAdvise;
	}

	public String getWashCarAdvise() {
		return washCarAdvise;
	}

	public void setWashCarAdvise(String washCarAdvise) {
		this.washCarAdvise = washCarAdvise;
	}

	public String getColdAdvise() {
		return coldAdvise;
	}

	public void setColdAdvise(String coldAdvise) {
		this.coldAdvise = coldAdvise;
	}

	public String getSportsAdvise() {
		return sportsAdvise;
	}

	public void setSportsAdvise(String sportsAdvise) {
		this.sportsAdvise = sportsAdvise;
	}

	public String getUltravioletRaysAdvise() {
		return ultravioletRaysAdvise;
	}

	public void setUltravioletRaysAdvise(String ultravioletRaysAdvise) {
		this.ultravioletRaysAdvise = ultravioletRaysAdvise;
	}

}