package com.yw.common.beansUtils.utils;
import javax.naming.RefAddr;
import javax.naming.Reference;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import com.yw.common.beansUtils.utils.des.DescryptCoder;

/**
 * <pre>
 * 功       能: JNDI连接池解密处理类
 * 涉及版本: V3.0.0  
 * 创  建  者: Vickey
 * 日       期: 2016年5月25日下午4:30:40
 * Q    Q: 308053847
 * </pre>
 */
public class JndiSecurityDataSourceFactory extends BasicDataSourceFactory {

	@Override
	@SuppressWarnings("rawtypes")
	public Object getObjectInstance(Object obj, javax.naming.Name name,
			javax.naming.Context nameCtx, java.util.Hashtable environment)
			throws Exception {

		if ((obj == null) || !(obj instanceof Reference)) {
			return null;
		}

		Reference ref = (Reference) obj;

		RefAddr ra = null;
		int len = ref.size();
		for (int i = 0; i < len; i++) {
			ra = ref.get(i);
			if ("password".equalsIgnoreCase(ra.getType())) {//只解密密码
				ref.remove(i);
				ref.add(i, new TransformRefAddr(ra) {
					private static final long serialVersionUID = 1L;

					@Override
					public Object transform(Object obj) {
						//开始解密
						return DescryptCoder.decrypt(obj.toString(), DescryptCoder.DES_KEY);
					}
				});
			}
		}
		return super.getObjectInstance(obj, name, nameCtx, environment);
	};

	private abstract class TransformRefAddr extends RefAddr {
		private static final long serialVersionUID = 1L;

		private RefAddr refAddr;

		public TransformRefAddr(RefAddr refAddr) {
			super(refAddr.getType());
			this.refAddr = refAddr;
		}

		@Override
		public Object getContent() {

			return this.transform(refAddr.getContent());
		}

		public abstract Object transform(Object obj);

	}

}
