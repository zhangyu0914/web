package com.yw.common.beansUtils.utils.highcharts.serializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.yw.common.beansUtils.utils.highcharts.format.DateTimeLabelFormats;
import com.yw.common.beansUtils.utils.highcharts.format.DateTimeLabelFormats.TimeUnit;

public class DateTimeLabelFormatsSerializer extends Serializer<DateTimeLabelFormats> {

    @Override
    public Map<String, String> getProperties( DateTimeLabelFormats instance ) {
        Map<TimeUnit, String> map = instance.getFormats();
        Map<String, String> r = new HashMap<String, String>();
        for ( Entry<TimeUnit, String> e : map.entrySet() ) {
            r.put( e.getKey().name().toLowerCase(), e.getValue() );
        }
        return r;
    }

}
