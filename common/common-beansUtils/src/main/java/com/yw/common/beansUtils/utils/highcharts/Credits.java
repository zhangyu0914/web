package com.yw.common.beansUtils.utils.highcharts;

import com.yw.common.beansUtils.utils.highcharts.base.BaseObject;

public class Credits extends BaseObject {

    private Boolean enabled;

    public boolean istEnabled() {
        return enabled;
    }

    public Credits setEnabled( boolean enabled ) {
        this.enabled = enabled;
        return this;
    }

}
