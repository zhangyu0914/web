package com.yw.common.beansUtils.dto.importFile;

import com.yw.common.beansUtils.entity.importFile.ModelEpsEntity;
import com.yw.common.beansUtils.entity.importFile.ModelPropertyEntity;
import com.yw.common.beansUtils.utils.string.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 功       能: 导入模型属性定义
 * 涉及版本: V2.0.0 
 * 创  建  者: Vickey
 * 日       期: 2017-04-26 10:26:51
 * Q    Q: 308053847
 * </pre>
 */
public class ModelPropertyDto {

	private static final long serialVersionUID = -2986347222798L;

	private String prop_id;// 属性ID
	private String prop_name;// 属性名称
	private List<ModelEpsDto> eps;//

	public ModelPropertyDto() {
		super();
	}

	public ModelPropertyDto(ModelPropertyEntity data) {
		
		if (data != null) {
			
			this.setProp_id(data.getProp_id());
			this.setProp_name(data.getProp_name());

			if (!StringUtils.isBlank(data.getEps())
					&& !data.getEps().isEmpty()) {
				
				for(ModelEpsEntity eps : data.getEps()){
					this.setEpsObj(new ModelEpsDto(eps));
				}
			}
		}
	}

	public String getProp_id() {
		return prop_id;
	}

	public void setProp_id(String prop_id) {
		this.prop_id = prop_id;
	}

	public String getProp_name() {
		return prop_name;
	}

	public void setProp_name(String prop_name) {
		this.prop_name = prop_name;
	}

	public List<ModelEpsDto> getEps() {
		return eps;
	}

	public void setEps(List<ModelEpsDto> eps) {
		this.eps = eps;
	}
	
	public void setEpsObj(ModelEpsDto eps) {
		if (this.eps == null) {
			this.eps = new ArrayList<ModelEpsDto>();
		}
		this.eps.add(eps);
	}
	
}
