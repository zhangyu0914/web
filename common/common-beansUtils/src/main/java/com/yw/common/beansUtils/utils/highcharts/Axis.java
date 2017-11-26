package com.yw.common.beansUtils.utils.highcharts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import com.yw.common.beansUtils.utils.highcharts.axis.AxisLabels;
import com.yw.common.beansUtils.utils.highcharts.axis.AxisPlotLines;
import com.yw.common.beansUtils.utils.highcharts.base.BaseObject;
import com.yw.common.beansUtils.utils.highcharts.format.DateTimeLabelFormats;
import com.yw.common.beansUtils.utils.highcharts.utils.ArrayString;
import com.yw.common.beansUtils.utils.highcharts.utils.JsonArray;

@XmlAccessorType( XmlAccessType.NONE )
public class Axis extends BaseObject {

    @XmlElement
    private Integer                  maxZoom;

    @XmlElement
    private Boolean                  startOnTick;

    @XmlElement
    private Double                   tickInterval;

    @XmlElement
    private Boolean                  showFirstLabel;

    @XmlElement
    private Double                   min;

    @XmlElement
    private Double                   max;

    @XmlElement
    private String                   type;

    @XmlElement( type = AxisLabels.class )
    private AxisLabels               labels;

    private DateTimeLabelFormats     dateTimeLabelFormats;

    @XmlTransient
    private ArrayString              categories;

    private JsonArray<AxisPlotLines> plotLines;

    private Title                    title;

    public Axis() {
        labels = null;
        dateTimeLabelFormats = null;
        categories = null;
        title = null;
    }

    @XmlTransient
    public ArrayString getCategories() {
        if ( categories == null ) {
            categories = new ArrayString();
        }
        return categories;
    }

    public int getCategoriesLength() {
        return ( categories != null ) ? categories.size() : 0;
    }

    public DateTimeLabelFormats getDateTimeLabelFormats() {
        if ( dateTimeLabelFormats == null ) {
            dateTimeLabelFormats = new DateTimeLabelFormats();
        }
        return dateTimeLabelFormats;
    }

    public AxisLabels getLabels() {
        if ( labels == null ) {
            labels = new AxisLabels();
        }
        return labels;
    }

    public double getMax() {
        return max;
    }

    public int getMaxZoom() {
        return maxZoom;
    }

    public double getMin() {
        return min;
    }

    public JsonArray<AxisPlotLines> getPlotLines() {
        return plotLines;
    }

    public double getTickInterval() {
        return tickInterval;
    }

    public Title getTitle() {
        if ( title == null ) {
            title = new Title();
        }
        return title;
    }

    public String getType() {
        return type;
    }

    public boolean isShowFirstLabel() {
        return showFirstLabel;
    }

    public boolean isStartOnTick() {
        return startOnTick;
    }

    public Axis setMax( double max ) {
        this.max = max;
        return this;
    }

    public Axis setMaxZoom( int maxZoom ) {
        this.maxZoom = maxZoom;
        return this;
    }

    public Axis setMin( double min ) {
        this.min = min;
        return this;
    }

    public Axis setPlotLines( JsonArray<AxisPlotLines> plotLines ) {
        this.plotLines = plotLines;
        return this;
    }

    public Axis setShowFirstLabel( boolean showFirstLabel ) {
        this.showFirstLabel = showFirstLabel;
        return this;
    }

    public Axis setStartOnTick( boolean startOnTick ) {
        this.startOnTick = startOnTick;
        return this;
    }

    public Axis setTickInterval( double tickInterval ) {
        this.tickInterval = tickInterval;
        return this;
    }

    public Axis setType( String type ) {
        this.type = type;
        return this;
    }

}
