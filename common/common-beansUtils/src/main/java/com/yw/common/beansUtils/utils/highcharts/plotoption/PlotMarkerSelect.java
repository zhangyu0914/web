package com.yw.common.beansUtils.utils.highcharts.plotoption;

import com.yw.common.beansUtils.utils.highcharts.base.BaseObject;

public class PlotMarkerSelect extends BaseObject {

    private String fillColor, lineColor;

    private int    lineWidth;

    public String getFillColor() {
        return fillColor;
    }

    public String getLineColor() {
        return lineColor;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public PlotMarkerSelect setFillColor( String fillColor ) {
        this.fillColor = fillColor;
        return this;
    }

    public PlotMarkerSelect setLineColor( String lineColor ) {
        this.lineColor = lineColor;
        return this;
    }

    public PlotMarkerSelect setLineWidth( int lineWidth ) {
        this.lineWidth = lineWidth;
        return this;
    }

}