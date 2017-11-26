package com.yw.common.beansUtils.utils.highcharts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.yw.common.beansUtils.utils.highcharts.base.BaseObject;
import com.yw.common.beansUtils.utils.highcharts.label.LabelsItems;
import com.yw.common.beansUtils.utils.highcharts.utils.JsonArray;

@XmlAccessorType( XmlAccessType.NONE )
@XmlType( namespace = "chart-options" )
public class Labels extends BaseObject {

    @XmlTransient
    private JsonArray<LabelsItems> items;

    public Labels() {
    }

    @XmlTransient
    public JsonArray<LabelsItems> getItems() {
        if ( items == null ) {
            items = new JsonArray<LabelsItems>();
        }

        return items;
    }

}
