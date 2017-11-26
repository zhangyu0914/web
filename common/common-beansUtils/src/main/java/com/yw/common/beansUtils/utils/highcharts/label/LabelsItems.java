package com.yw.common.beansUtils.utils.highcharts.label;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.yw.common.beansUtils.utils.highcharts.base.Style;

@XmlAccessorType( XmlAccessType.NONE )
@XmlType( namespace = "labels" )
public class LabelsItems {

    private String html;

    private Style  style;

    public LabelsItems() {
        style = new Style();
    }

    public LabelsItems center( int centerPosition, double top ) {
        getStyle().setProperty( "left", ( centerPosition - ( ( getHtml().length() * 6 ) / 2 ) ) + "px" );
        getStyle().setProperty( "top", top + "px" );
        return this;
    }

    public String getHtml() {
        return html;
    }

    public Style getStyle() {
        return style;
    }

    public LabelsItems setHtml( String html ) {
        this.html = html;
        return this;
    }

}