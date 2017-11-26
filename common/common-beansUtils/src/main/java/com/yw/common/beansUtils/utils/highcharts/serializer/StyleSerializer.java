package com.yw.common.beansUtils.utils.highcharts.serializer;

import java.util.Map;

import com.yw.common.beansUtils.utils.highcharts.base.Style;

public class StyleSerializer extends Serializer<Style> {

    @Override
    public Map<String, String> getProperties( Style instance ) {
        return instance.getProperties();
    }

}
