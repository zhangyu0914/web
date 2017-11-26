package com.yw.common.beansUtils.utils.highcharts.plotoption;

import com.yw.common.beansUtils.utils.highcharts.base.BaseObject;

public class PlotOptionsMarker extends BaseObject {

    private String           symbol;

    private boolean          enabled;

    private PlotMarkerStates states;

    public PlotOptionsMarker() {
    }

    public PlotMarkerStates getStates() {
        if ( states == null ) {
            states = new PlotMarkerStates();
        }
        return states;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public PlotOptionsMarker setEnabled( boolean enabled ) {
        this.enabled = enabled;
        return this;
    }

    public PlotOptionsMarker setSymbol( String symbol ) {
        this.symbol = symbol;
        return this;
    }
}