package com.yw.common.beansUtils.utils.highcharts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.yw.common.beansUtils.utils.highcharts.base.BaseObject;
import com.yw.common.beansUtils.utils.highcharts.plotoption.PlotOptionsSeries;

@XmlAccessorType( XmlAccessType.NONE )
public class PlotOptions extends BaseObject {

    @XmlElement
    private final PlotOptionsSeries area, areaspline, line, pie, series,
            spline;

    private PlotOptionsSeries       column, bar;

    public PlotOptions() {
        area = new PlotOptionsSeries();
        areaspline = new PlotOptionsSeries();
        bar = new PlotOptionsSeries();
        column = new PlotOptionsSeries();
        line = new PlotOptionsSeries();
        pie = new PlotOptionsSeries();
        series = new PlotOptionsSeries();
        spline = new PlotOptionsSeries();
    }

    public PlotOptionsSeries getArea() {
        return area;
    }

    public PlotOptionsSeries getAreaspline() {
        return areaspline;
    }

    public PlotOptionsSeries getBar() {
        return bar;
    }

    public PlotOptionsSeries getColumn() {
        return column;
    }

    public PlotOptionsSeries getLine() {
        return line;
    }

    public PlotOptionsSeries getPie() {
        return pie;
    }

    public PlotOptionsSeries getSeries() {
        return series;
    }

    public PlotOptionsSeries getSpline() {
        return spline;
    }
}
