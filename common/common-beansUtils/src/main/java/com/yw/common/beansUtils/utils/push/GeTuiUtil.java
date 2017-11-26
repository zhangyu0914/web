package com.yw.common.beansUtils.utils.push;

import java.util.ArrayList;
import java.util.List;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.APNTemplate;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.yw.common.beansUtils.utils.JavaBeanUtil;

public class GeTuiUtil {

	/**
	 *<pre>
	 * 说       明: 发给Android单人
	 * @param content
	 * @param title
	 * @param clientId
	 * @return
	 * @throws Exception
	 * 涉及版本: 
	 * 创  建  者: 陈林林(Vickey)
	 * 日       期: 2015-8-4下午6:24:11
	 *</pre>
	 */
	public static IPushResult sendSingleMsg(String content, String title, String clientId , String gtAppid, String gtAppkey, String gtHost, Long gtOfflineExpireTime, String gtMaster) throws Exception {
		IGtPush push = new IGtPush(gtHost, gtAppkey, gtMaster);
		push.connect();
		NotificationTemplate template = createNotificationTemplate(title, content, gtAppid, gtAppkey, gtHost, gtOfflineExpireTime, gtMaster);
		SingleMessage message = new SingleMessage();
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(gtOfflineExpireTime);
		message.setData(template);
		Target target1 = new Target();
		target1.setAppId(gtAppid);
		target1.setClientId(clientId);
		IPushResult ret = push.pushMessageToSingle(message, target1);
		return ret;
	}

    /**
     *<pre>
     * 说       明: 根据CID发送IOS单人
     * @param content
     * @param badge
     * @param clientId
     * @return
     * @throws Exception
     * 涉及版本: 
     * 创  建  者: 陈林林(Vickey)
     * 日       期: 2015-8-4下午6:04:53
     *</pre>
     */
    public static IPushResult sendIOSSingleMsg(String content, int badge, String clientId, String gtAppid, String gtAppkey, String gtHost, Long gtOfflineExpireTime, String gtMaster) throws Exception {
        IGtPush push = new IGtPush(gtHost, gtAppkey, gtMaster);
        push.connect();
        TransmissionTemplate template = createTransmissionTemplate(content, badge, gtAppid, gtAppkey, gtHost, gtOfflineExpireTime, gtMaster);
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(gtOfflineExpireTime);
        message.setData(template);
        Target target1 = new Target();
        target1.setAppId(gtAppid);
        target1.setClientId(clientId);
        IPushResult ret = push.pushMessageToSingle(message, target1);
        return ret;
    }

	/**
	 * 根据deviceToken发给IOS单人
	 * @param content
	 * @param title
	 * @param clientId
	 * @return
	 * @throws Exception
	 */
	public static IPushResult sendApnsMsg(String content, String title, String clientId, String gtAppid, String gtAppkey, String gtHost, Long gtOfflineExpireTime, String gtMaster) throws Exception {
		IGtPush push = new IGtPush(gtHost, gtAppkey, gtMaster);
		push.connect();
		APNTemplate template = new APNTemplate();
		template.setPushInfo("", 2, content, "");
		SingleMessage message = new SingleMessage();
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(gtOfflineExpireTime);
		message.setData(template);
		IPushResult ret = push.pushAPNMessageToSingle(gtAppid, clientId, message);
		return ret;
	}

	/**
	 * 群推Android所有人
	 * @param content
	 * @param title
	 * @return
	 * @throws Exception
	 */
	public static IPushResult sendAppMsg(String content, String title, String gtAppid, String gtAppkey, String gtHost, Long gtOfflineExpireTime, String gtMaster) throws Exception {
		IGtPush push = new IGtPush(gtHost, gtAppkey, gtMaster);
		push.connect();
		NotificationTemplate template = createNotificationTemplate(title, content, gtAppid, gtAppkey, gtHost, gtOfflineExpireTime, gtMaster);
		AppMessage message = new AppMessage();
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(gtOfflineExpireTime);
		message.setData(template);
		Target target1 = new Target();
		target1.setAppId(gtAppid);
		IPushResult ret = push.pushMessageToApp(message);
		return ret;
	}

    /**
     * 群推IOS所有人
     * @param content
     * @param badge
     * @return
     * @throws Exception
     */
    public static IPushResult sendIOSAppMsg(String content, int badge, String gtAppid, String gtAppkey, String gtHost, Long gtOfflineExpireTime, String gtMaster) throws Exception {
        IGtPush push = new IGtPush(gtHost, gtAppkey, gtMaster);
        push.connect();
        TransmissionTemplate template = createTransmissionTemplate(content, badge, gtAppid, gtAppkey, gtHost, gtOfflineExpireTime, gtMaster);
        AppMessage message = new AppMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(gtOfflineExpireTime);
        message.setData(template);
        Target target1 = new Target();
        target1.setAppId(gtAppid);
        IPushResult ret = push.pushMessageToApp(message);
        return ret;
    }

	/**
	 *  发给平台上指定用户
	 * @param content
	 * @param title
	 * @param clientIds
	 * @return
	 * @throws Exception
	 */
	public static IPushResult sendMsgToUsers(String content, String title, List<String> clientIds,int badge, String gtAppid, String gtAppkey, String gtHost, Long gtOfflineExpireTime, String gtMaster) throws Exception {
		IGtPush push = new IGtPush(gtHost, gtAppkey, gtMaster);
		push.connect();
		NotificationTemplate template = createNotificationTemplate(title, content, gtAppid, gtAppkey, gtHost, gtOfflineExpireTime, gtMaster);
		ListMessage message = new ListMessage();
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(gtOfflineExpireTime);
		message.setData(template);
		List<Target> targets = new ArrayList<Target>();
		for (String clientId : clientIds) {
			Target target1 = new Target();
			target1.setAppId(gtAppid);
			target1.setClientId(clientId);
			targets.add(target1);
		}
		// 获取taskID
		String taskId = push.getContentId(message);
		IPushResult ret = push.pushMessageToList(taskId, targets);
		return ret;
	}

    /**
     * 发送IOS多用户
     * @param content
     * @param clientIds
     * @param badge
     * @return
     * @throws Exception
     */
    public static IPushResult sendIOSMsgToUsers(String content,List<String> clientIds,int badge, String gtAppid, String gtAppkey, String gtHost, Long gtOfflineExpireTime, String gtMaster) throws Exception {
        IGtPush push = new IGtPush(gtHost, gtAppkey, gtMaster);
        push.connect();
        TransmissionTemplate template = createTransmissionTemplate(content, badge, gtAppid, gtAppkey, gtHost, gtOfflineExpireTime, gtMaster);
        ListMessage message = new ListMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(gtOfflineExpireTime);
        message.setData(template);
        List<Target> targets = new ArrayList<Target>();
        for (String clientId : clientIds) {
            Target target1 = new Target();
            target1.setAppId(gtAppid);
            target1.setClientId(clientId);
            targets.add(target1);
        }
        // 获取taskID
        String taskId = push.getContentId(message);
        IPushResult ret = push.pushMessageToList(taskId, targets);
        return ret;
    }

    /**
     * android发送模版
     * @param title
     * @param content
     * @return
     * @throws Exception
     */
	public static NotificationTemplate createNotificationTemplate(String title, String content, String gtAppid, String gtAppkey, String gtHost, Long gtOfflineExpireTime, String gtMaster) throws Exception {
		NotificationTemplate template = new NotificationTemplate();
		template.setAppId(gtAppid);
		template.setAppkey(gtAppkey);
		template.setTitle(title);
		template.setText(content);
		template.setLogo("ic_launcher.png");
		template.setLogoUrl("");
		template.setIsRing(true);
		template.setIsVibrate(true);
		template.setIsClearable(true);
		template.setTransmissionType(1);
		template.setTransmissionContent("");
		return template;
	}

    /**
     * IOS透传模版
     * @param content
     * @param badge
     * @return
     * @throws Exception
     */
    public static TransmissionTemplate createTransmissionTemplate(String content, int badge, String gtAppid, String gtAppkey, String gtHost, Long gtOfflineExpireTime, String gtMaster) throws Exception {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(gtAppid);
        template.setAppkey(gtAppkey);
        template.setTransmissionType(1);
        template.setTransmissionContent("请输入需要透传的内容");
        /*iOS 推送需要对该字段进行设置具体参数详见iOS模板说明*/
        template.setPushInfo("", badge, content, "sound","", "","","");
        return template;
    }
	
    public static LinkTemplate linkTemplateDemo(String content, int badge, String gtAppid, String gtAppkey, String gtHost, Long gtOfflineExpireTime, String gtMaster) {
        LinkTemplate template = new LinkTemplate();
        // 设置APPID与APPKEY
        template.setAppId(gtAppid);
        template.setAppkey(gtAppkey);
        // 设置通知栏标题与内容
        template.setTitle("请输入通知栏标题");
        template.setText("请输入通知栏内容");
        // 配置通知栏图标
        template.setLogo("ic_launcher.png");
        // 配置通知栏网络图标
        template.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        template.setIsRing(true);
        template.setIsVibrate(true);
        template.setIsClearable(true);
        // 设置打开的网址地址
        template.setUrl("http://www.baidu.com");
        return template;
        }
    
    public static void main(String[] args) {
    	 try {
    		 String gtAppid = "5aRB0PrGUn5BRHjPJ6lG83";
    		 String gtAppkey = "naxBSfd4NG8MKsq4aimvYA"; 
    		 String gtHost= "http://sdk.open.api.igexin.com/apiex.htm"; 
    		 String gtMaster= "9nRRB1w8k3AWimU7DQgwa7"; 
    		 Long gtOfflineExpireTime = 86400000L;
    		 
    		 IPushResult pushResult = GeTuiUtil.sendIOSSingleMsg(System.currentTimeMillis() + "", 1, "f55d378e7eec09fc27d2f18bb9684770", gtAppid, gtAppkey, gtHost, gtOfflineExpireTime, gtMaster);//libing
    		 IPushResult pushResult2 = GeTuiUtil.sendIOSSingleMsg(System.currentTimeMillis() + "", 1, "c4bc4bfbb9f0f30183414e38b0e0a8f1", gtAppid, gtAppkey, gtHost, gtOfflineExpireTime, gtMaster);//liangliang
    		 IPushResult pushResult3 = GeTuiUtil.sendSingleMsg(System.currentTimeMillis() + "", "标题", "77967524555847aeacfd71638ac7c5dd", gtAppid, gtAppkey, gtHost, gtOfflineExpireTime, gtMaster);//yayun
    		 IPushResult pushResult4 = GeTuiUtil.sendSingleMsg(System.currentTimeMillis() + "", "标题", "b71a400daf511f5e866fc4d8496bf658", gtAppid, gtAppkey, gtHost, gtOfflineExpireTime, gtMaster);//vickey
    		
    		 System.out.println(JavaBeanUtil.javaBeanToMap(pushResult));
    		 System.out.println(JavaBeanUtil.javaBeanToMap(pushResult2));
    		 System.out.println(JavaBeanUtil.javaBeanToMap(pushResult3));
    		 System.out.println(JavaBeanUtil.javaBeanToMap(pushResult4));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
