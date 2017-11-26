package com.yw.common.beansUtils.utils.highcharts.plotoption;

import javax.xml.bind.annotation.XmlElement;

import com.yw.common.beansUtils.utils.highcharts.base.BaseObject;

public class PlotOptionsStates extends BaseObject {

    @XmlElement( type = PlotStatesSelect.class )
    private PlotStatesSelect select;

    public PlotStatesSelect getSelect() {
        if ( select == null ) {
            select = new PlotStatesSelect();
        }
        return select;
    }
}