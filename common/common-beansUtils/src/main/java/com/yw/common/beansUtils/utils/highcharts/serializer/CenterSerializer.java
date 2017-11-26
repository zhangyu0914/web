package com.yw.common.beansUtils.utils.highcharts.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.yw.common.beansUtils.utils.highcharts.serie.SeriesCenter;

public class CenterSerializer implements JsonSerializer<SeriesCenter> {

    @Override
    public JsonElement serialize( SeriesCenter center, Type arg1, JsonSerializationContext arg2 ) {
        if ( ( center.getX() == null ) || ( center.getY() == null ) ) {
            return new JsonPrimitive( "" );
        }
        JsonArray r = new JsonArray();
        r.add( new JsonPrimitive( center.getX() ) );
        r.add( new JsonPrimitive( center.getY() ) );
        return r;
    }

}
