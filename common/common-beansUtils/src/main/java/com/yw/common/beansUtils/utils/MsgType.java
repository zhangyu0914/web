package com.yw.common.beansUtils.utils;

public interface MsgType {
	
	public static final int DISPLAYMODE_TWO_WAY = 2;//显示模式，默认为0，2为该消息双方显示
	
	public static final int DISPLAYMODE_ONE_WAY = 1;//显示模式，默认为0，1为该消息只在接收方显示

	//文字
	public static final int TEXT = 1;
	//图片
	public static final int IMAGE = 2;
	//病史
	public static final int ILL_HISTORY = 3;
	//医生名片
	public static final int DOCTOR_CARD = 4;
	//内部链接
	public static final int INNER_LINK = 5;
	//外部链接
	public static final int OUTER_LINK = 6;
	//易问医
	public static final int ASKDR = 7;
	//服务申请
	public static final int APPLY_SERVICE = 8;
	//随访
	public static final int PLAN = 9;
	//随访反馈
	public static final int FEEDBACK = 10;
	//查看病历
	public static final int LOOK_HISTORY = 11;
	//广播消息
	public static final int SYSTEM_BROADCAST = 999;
	//进制所有成员发言
	public static final int ALL_MEMBER_FORBID_SPEECH = 2000;
	//允许所有成员发言
	public static final int ALL_MEMBER_ALLOW_SPEECH = 2001;
	//加入群组
	public static final int JOIN_GROUP = 2002;
	//退出群组
	public static final int EXIT_GROUP = 2003;
	//解散群组
	public static final int DISBAND_GROUP = 2004;
	//修改讨论组名称
	public static final int MODIFY_GROUPNAME = 2005;
	//管理员从讨论组中移除成员
	public static final int REMOVE_FROM_GROUP = 2006;

	//通知
	public static final int NOTIFY = 9000;
	//下线通知
	public static final int NOTIFY_OFF_LINE = 9001;

	//同意医生认证
	public static final int DOCTOR_AUTH_APPROVE = 3001;
	//拒绝医生认证
	public static final int DOCTOR_AUTH_DISAPPROVE = 3002;
	public static final int DOCTOR_H5INVITE = 3003;
	//切换客服
	public static final int XIAOYIZHULI_CHANGE = 101;
	//项目通知
	public static final int RESEARCH_PROJECT_NOTIFY = 1501;
	//项目系统消息
	public static final int RESEARCH_PROJECT_SYS_MSG = 1502;
	//用户加入项目欢迎语
	public static final int RESEARCH_PROJECT_WELCOME_MSG = 1503;
	
	//分享FAQ
	public static final int SHARE_FAQ = 4001;
	//平台创建FAQ
	public static final int ASKDR_CREATE_FAQ = 4002;
	//医生创建FAQ
	public static final int DOCTOR_CREATE_FAQ = 4003;	
	//患者购买FAQ
	public static final int PATIENT_BUY_FAQ = 4004;	
}
