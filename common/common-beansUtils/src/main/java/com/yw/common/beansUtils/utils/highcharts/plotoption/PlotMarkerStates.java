package com.yw.common.beansUtils.utils.highcharts.plotoption;

import com.yw.common.beansUtils.utils.highcharts.base.BaseObject;

public class PlotMarkerStates extends BaseObject {

    private PlotMarkerSelect select;

    private PlotMarkerHover  hover;

    public PlotMarkerStates() {
        select = new PlotMarkerSelect();
        hover = new PlotMarkerHover();
    }

    public PlotMarkerHover getHover() {
        return hover;
    }

    public PlotMarkerSelect getSelect() {
        return select;
    }
}