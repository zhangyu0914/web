package com.yw.common.beansUtils.utils.highcharts;

import com.yw.common.beansUtils.utils.highcharts.base.BaseObject;
import com.yw.common.beansUtils.utils.highcharts.shared.SeriesType;

public class Chart extends BaseObject {
    private String  zoomType;

    private String  backgroundColor;

    private Integer marginRight;

    private Integer marginBottom;

    private Integer width;

    private Integer height;

    private String  defaultSeriesType;

    private int     marginLeft;

    private int     marginTop;

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getDefaultSeriesType() {
        return defaultSeriesType;
    }

    public int getHeight() {
        return height;
    }

    public int getMarginBottom() {
        return marginBottom;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public int getWidth() {
        return width;
    }

    public String getZoomType() {
        return zoomType;
    }

    public Chart setBackgroundColor( String backgroundColor ) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public Chart setDefaultSeriesType( SeriesType type ) {
        defaultSeriesType = type.name().toLowerCase();
        return this;
    }

    public Chart setHeight( int height ) {
        this.height = height;
        return this;
    }

    public Chart setMarginBottom( int marginBottom ) {
        this.marginBottom = marginBottom;
        return this;
    }

    public Chart setMarginLeft( int marginLeft ) {
        this.marginLeft = marginLeft;
        return this;
    }

    public Chart setMarginRight( int marginRight ) {
        this.marginRight = marginRight;
        return this;
    }

    public Chart setMarginTop( int marginTop ) {
        this.marginTop = marginTop;
        return this;
    }

    public Chart setWidth( int width ) {
        this.width = width;
        return this;
    }

    public Chart setZoomType( String zoomType ) {
        this.zoomType = zoomType;
        return this;
    }

}
