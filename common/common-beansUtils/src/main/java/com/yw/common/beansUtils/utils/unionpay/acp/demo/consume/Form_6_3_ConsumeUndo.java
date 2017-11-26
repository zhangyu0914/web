package com.yw.common.beansUtils.utils.unionpay.acp.demo.consume;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yw.common.beansUtils.utils.unionpay.acp.demo.DemoBase;
import com.yw.common.beansUtils.utils.unionpay.acp.sdk.SDKConfig;
import com.yw.common.beansUtils.utils.unionpay.acp.sdk.SDKUtil;

/**
 * 重要：联调测试时请仔细阅读注释！
 * 
 * 产品：跳转网关支付产品<br>
 * 交易：消费撤销：后台资金类交易，有同步应答和后台通知应答<br>
 * 日期： 2015-09<br>
 * 版本： 1.0.0 
 * 版权： 中国银联<br>
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己需要，按照技术文档编写。该代码仅供参考，不提供编码性能规范性等方面的保障<br>
 * 该接口参考文档位置：open.unionpay.com帮助中心 下载  产品接口规范  《网关支付产品接口规范》<br>
 *              《平台接入接口规范-第5部分-附录》（内包含应答码接口规范，全渠道平台银行名称-简码对照表）<br>
 * 测试过程中的如果遇到疑问或问题您可以：1）优先在open平台中查找答案：
 * 							        调试过程中的问题或其他问题请在 https://open.unionpay.com/ajweb/help/faq/list 帮助中心 FAQ 搜索解决方案
 *                             测试过程中产生的6位应答码问题疑问请在https://open.unionpay.com/ajweb/help/respCode/respCodeList 输入应答码搜索解决方案
 *                          2） 咨询在线人工支持： open.unionpay.com注册一个用户并登陆在右上角点击“在线客服”，咨询人工QQ测试支持。
 * 交易说明:1）以后台通知或交易状态查询交易（Form_6_5_Query）确定交易成功，建议发起查询交易的机制：可查询N次（不超过6次），每次时间间隔2N秒发起,即间隔1，2，4，8，16，32S查询（查询到03，04，05继续查询，否则终止查询）
 *       2）消费撤销仅能对当清算日的消费做，必须为全额，一般当日或第二日到账。
 */

public class Form_6_3_ConsumeUndo extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		/**
		 * 请求银联接入地址，获取证书文件，证书路径等相关参数初始化到SDKConfig类中
		 * 在java main 方式运行时必须每次都执行加载
		 * 如果是在web应用开发里,这个方法可使用监听的方式写入缓存,无须在这出现
		 */
		//这里已经将加载属性文件的方法挪到了web/AutoLoadServlet.java中
		//SDKConfig.getConfig().loadPropertiesFromSrc(); //从classpath加载acp_sdk.properties文件
		super.init();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String origQryId = req.getParameter("origQryId");
		String txnAmt = req.getParameter("txnAmt");
		
		Map<String, String> data = new HashMap<String, String>();
		
		/***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
		data.put("version", SDKUtil.version);            //版本号
		data.put("encoding", SDKUtil.encoding_UTF8);          //字符集编码 可以使用UTF-8,GBK两种方式
		data.put("signMethod", "01");                     //签名方法 目前只支持01-RSA方式证书加密
		data.put("txnType", "31");                        //交易类型 31-消费撤销
		data.put("txnSubType", "00");                     //交易子类型  默认00
		data.put("bizType", "000201");                    //业务类型 B2C网关支付，手机wap支付
		data.put("channelType", "07");                    //渠道类型，07-PC，08-手机
		
		/***商户接入参数***/
		data.put("merId", "777290058110048");             //商户号码，请改成自己申请的商户号或者open上注册得来的777商户号测试
		data.put("accessType", "0");                      //接入类型，商户接入固定填0，不需修改	
		data.put("orderId", DemoBase.getOrderId());       //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则，重新产生，不同于原消费		
		data.put("txnTime", DemoBase.getCurrentTime());   //订单发送时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
		data.put("txnAmt", txnAmt);                       //【撤销金额】，消费撤销时必须和原消费金额相同	
		data.put("currencyCode", "156");                  //交易币种(境内商户一般是156 人民币)
		data.put("reqReserved", "透传信息");                 //请求方保留域，透传字段（可以实现商户自定义参数的追踪）本交易的后台通知,对本交易的交易状态查询交易、对账文件中均会原样返回，商户可以按需上传，长度为1-1024个字节		
		data.put("backUrl", DemoBase.backUrl);            //后台通知地址，后台通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费撤销交易 商户通知,其他说明同消费交易的商户通知
		
		/***要调通交易以下字段必须修改***/
		data.put("origQryId", origQryId);   			  //【原始交易流水号】，原消费交易返回的的queryId，可以从消费交易后台通知接口中或者交易状态查询接口中获取
		
		/**请求参数设置完毕，以下对请求参数进行签名并发送http post请求，接收同步应答报文**/
		Map<String, String> submitFromData  = SDKUtil.signData(data,SDKUtil.encoding_UTF8);//报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。

		String url = SDKConfig.getConfig().getBackRequestUrl();//交易请求url从配置文件读取对应属性文件acp_sdk.properties中的 acpsdk.backTransUrl
		
		//如果这里通讯读超时（30秒），需发起交易状态查询交易
		Map<String, String> resmap = SDKUtil.submitUrl(submitFromData, url,SDKUtil.encoding_UTF8);//发送请求报文并接受同步应答（默认连接超时时间30秒，读取返回结果超时时间30秒）;这里调用signData之后，调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过

		/**对应答码的处理，请根据您的业务逻辑来编写程序,以下应答码处理逻辑仅供参考------------->**/
		
		//应答码规范参考open.unionpay.com帮助中心 下载  产品接口规范  《平台接入接口规范-第5部分-附录》
		if(resmap.get("respCode").equals("00")){
			//交易已受理(不代表交易已成功），等待接收后台通知确定交易成功，也可以主动发起 查询交易确定交易状态。
			//TODO
		}else if(resmap.get("respCode").equals("03") ||
				 resmap.get("respCode").equals("04") ||
				 resmap.get("respCode").equals("05")){
			//后续需发起交易状态查询交易确定交易状态。
			//TODO
		}else{
			//其他应答码为失败请排查原因
			//TODO
		}
		resp.getWriter().write("请求报文=["+submitFromData.toString()+"] <br/><br/>" + "应答报文=["+resmap.toString()+"]");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
