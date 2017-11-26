package com.yw.common.beansUtils.utils.highcharts.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.yw.common.beansUtils.utils.highcharts.shared.Jsonify;

@XmlAccessorType( XmlAccessType.NONE )
public class BaseObject implements Jsonify {
    public BaseObject() {
        super();
    }

    @Override
    public String toJson() {
        return GsonHelper.toJson( this );
    }

}