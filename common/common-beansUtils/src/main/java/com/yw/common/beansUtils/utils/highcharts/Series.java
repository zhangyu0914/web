package com.yw.common.beansUtils.utils.highcharts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.yw.common.beansUtils.utils.highcharts.base.BaseObject;
import com.yw.common.beansUtils.utils.highcharts.plotoption.PlotMarkerStates;
import com.yw.common.beansUtils.utils.highcharts.serie.SeriesCenter;
import com.yw.common.beansUtils.utils.highcharts.utils.JsonArray;

@XmlAccessorType( XmlAccessType.NONE )
@XmlType( namespace = "chartoptions" )
public class Series extends BaseObject {

    @XmlElement( type = PlotMarkerStates.class )
    private PlotMarkerStates states;

    @XmlElements( @XmlElement( name = "data", type = Point.class ) )
    private Object           data;

    @XmlElement
    private String           color;

    @XmlElement
    private String           name;

    @XmlElement
    private String           type;

    @XmlElement
    private String           size;

    private SeriesCenter     center;

    public String getCenterX() {
        return ( center != null ) ? center.getX() : null;
    }

    public String getCenterY() {
        return ( center != null ) ? center.getY() : null;
    }

    public String getColor() {
        return color;
    }

    @SuppressWarnings( "unchecked" )
    public JsonArray<Point> getData() {
        if ( data == null ) {
            data = new JsonArray<Point>();
        }
        return ( JsonArray<Point> ) data;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public PlotMarkerStates getStates() {
        if ( states == null ) {
            states = new PlotMarkerStates();
        }
        return states;
    }

    public String getType() {
        return type;
    }

    public Series setCenter( String x, String y ) {
        if ( center == null ) {
            center = new SeriesCenter();
        }
        center.setX( x );
        center.setY( y );
        return this;
    }

    public Series setColor( String color ) {
        this.color = color;
        return this;
    }

    @XmlTransient
    public Series setData( JsonArray<Point> data ) {
        this.data = data;
        return this;
    }

    public Series setName( String name ) {
        this.name = name;
        return this;
    }

    public Series setSize( String size ) {
        this.size = size;
        return this;
    }

    public Series setStates( PlotMarkerStates states ) {
        this.states = states;
        return this;
    }

    public Series setType( String type ) {
        this.type = type;
        return this;
    }
}
