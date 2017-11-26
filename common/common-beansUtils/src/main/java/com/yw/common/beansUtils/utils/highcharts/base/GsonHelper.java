package com.yw.common.beansUtils.utils.highcharts.base;

import com.yw.common.beansUtils.utils.highcharts.format.DateTimeLabelFormats;
import com.yw.common.beansUtils.utils.highcharts.serializer.DateTimeLabelFormatsSerializer;
import com.yw.common.beansUtils.utils.highcharts.serializer.StyleSerializer;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.GsonBuilder;

public final class GsonHelper {

    private static final String     DATE_FORMAT  = "yyyyMMdd";

    private static final String     USER_OBJECT = "userObject";

    private static final GsonHelper INSTANCE    = new GsonHelper();

    static String toJson( Object object ) {
        return INSTANCE.gsonBuilder.create().toJson( object );
    }

    private final GsonBuilder gsonBuilder;

    private GsonHelper() {
        gsonBuilder = new GsonBuilder().registerTypeAdapter( DateTimeLabelFormats.class, new DateTimeLabelFormatsSerializer() ) //
        .registerTypeAdapter( Style.class, new StyleSerializer() )//
        .setDateFormat( DATE_FORMAT )//
        .setExclusionStrategies( new ExclusionStrategy() {

            @Override
            public boolean shouldSkipClass( Class<?> arg0 ) {
                return false;
            }

            @Override
            public boolean shouldSkipField( FieldAttributes attributes ) {
                return attributes.getName().equals( USER_OBJECT );
            }

        } );
    }
}
