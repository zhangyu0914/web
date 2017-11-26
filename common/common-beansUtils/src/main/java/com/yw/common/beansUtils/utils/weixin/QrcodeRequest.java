package com.yw.common.beansUtils.utils.weixin;

public class QrcodeRequest {
	private Integer expire_seconds;	//该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
	private String action_name;	//二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值
	private ActionInfo action_info;//二维码详细信息
	class  ActionInfo{
		private Scene scene;
		public ActionInfo(String scene_id,String scene_str){
			scene=new Scene(scene_id,scene_str);
		}
		public Scene getScene() {
			return scene;
		}
		public void setScene(Scene scene) {
			this.scene = scene;
		}
	
	}
	
	class Scene {
		private String scene_id;//场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
		private String scene_str;//场景值ID（字符串形式的ID），字符串类型，长度限制为1到64，仅永久二维码支持此字段
		public Scene(String scene_id,String scene_str){
			 this.scene_str=scene_str;
			 this.scene_id=scene_id;
		}
		public String getScene_id() {
			return scene_id;
		}
		public void setScene_id(String scene_id) {
			this.scene_id = scene_id;
		}
		public String getScene_str() {
			return scene_str;
		}
		public void setScene_str(String scene_str) {
			this.scene_str = scene_str;
		}
	}
	
	public ActionInfo getAction_info() {
		return action_info;
	}
	
	public void setAction_info(String scene_id,String scene_str) {
		this.action_info = new ActionInfo(scene_id,scene_str);
	}
	public void setAction_info(ActionInfo action_info) {
		this.action_info = action_info;
	}
	public Integer getExpire_seconds() {
		return expire_seconds;
	}
	public void setExpire_seconds(Integer expire_seconds) {
		this.expire_seconds = expire_seconds;
	}
	public String getAction_name() {
		return action_name;
	}
	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}
	  
	
}
