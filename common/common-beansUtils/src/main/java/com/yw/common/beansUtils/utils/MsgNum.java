package com.yw.common.beansUtils.utils;

import java.io.Serializable;

public interface MsgNum {
	public static final class ModuleCode implements Serializable{
		/**
		 *医生邀请 
		 *包含医生邀请医生、医生邀请患者等，
		 *请配合BusiCode一起使用。
		 */
		public static final String DOCTOR_INVITE = "100";
		/**
		 *医患互动
		 *医生与患者通讯信息
		 *请配合BusiCode一起使用。
		 */
		public static final String DOCTOR_PATIENT = "200";
		/**
		 *讨论组申请 
		 *包含待处理申请审批、被邀请同意
		 *请配合BusiCode一起使用。
		 */
		public static final String GROUP_APPLY = "300";	
		/**
		 *患者病历 
		 */
		public static final String MEDICAL_RECORD = "400";
		/**
		 *用药
		 */
		public static final String MEDICATE = "500";			
		/**
		 *患者申请 
		 *包含患者申请医生服务，
		 *请配合BusiCode一起使用。
		 */
		public static final String PATIENT_APPLY = "600";
		/**
		 *随访
		 *包含随访检查计划
		 *
		 */
		public static final String PLAN = "700";
		/**
		 * 讨论组
		 * 讨论组即时通讯
		 * 
		 */
		public static final String USER_GROUP = "800";
		/**
		 * 小易助理
		 */
		public static final String XIAOYIZHULI = "900";	
		/**
		 * 易问医
		 */
		public static final String YIWENYI = "1000";

		/**
		 * 申请记录
		 */
		public static final String APPLY_RECORD = "1100";

        /**
         * 任务提醒
         */
        public static final String TASK_ALERT = "1200";
        
        /**
         * 医生邀请患者
         */
        public static final String DOCTOR_INVITE_PATIENT = "1300";
        
        /**
         * 科研项目
         */
        public static final String RESEARCH_PROJECT = "1500";

		/**
		 *通知PUSH
		 */
		public static final String PUSH = "2000";
	}
	
	
	public static final class BusiCode implements Serializable{
		
		/**
		 *医生邀请医生 
		 */
		public static final String DOCTOR_INVITE_DOCTOR = "101";
		/**
		 *医生邀请患者
		 */
		public static final String DOCTOR_INVITE_PATIENT = "102";
		/**
		 *医患互动
		 *医生与患者通讯信息
		 *请配合BusiCode一起使用。
		 */
		public static final String DOCTOR_PATIENT = "200";
		
		/**
		 *讨论组申请 
		 *包含待处理申请审批、被邀请同意
		 *请配合BusiCode一起使用。
		 */
		public static final String GROUP_APPLY = "300";	
		
		/**
		 *患者病历 
		 */
		public static final String MEDICAL_RECORD = "400";
		/**
		 *用药
		 */
		public static final String MEDICATE = "500";	
		/**
		 *患者申请 
		 *包含患者申请医生服务，
		 *请配合BusiCode一起使用。
		 */
		public static final String PATIENT_APPLY = "600";
		/**
		 *随访
		 *包含随访检查计划
		 *
		 */
		public static final String PLAN = "700";
		/**
		 * 讨论组
		 * 讨论组即时通讯
		 * 
		 */
		public static final String USER_GROUP = "800";
		/**
		 * 小易助理
		 */
		public static final String XIAOYIZHULI = "900";
		/**
		 * 易问医
		 */
		public static final String YIWENYI = "1000";
		/**
		 *通知PUSH
		 */
		public static final String PUSH = "2000";
		
	}
}
