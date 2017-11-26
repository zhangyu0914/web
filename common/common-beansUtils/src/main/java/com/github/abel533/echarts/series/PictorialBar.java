/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2015 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.abel533.echarts.series;

import com.github.abel533.echarts.code.SeriesType;

/**
 * 图案的条形图
 *
 * @author liuzh
 */
public class PictorialBar extends Series<PictorialBar> {
    /**
     * 柱条最小高度，可用于防止某item的值过小而影响交互
     */
    private Integer barMinHeight;
    /**
     * 柱条（K线蜡烛）宽度，不设时自适应
     */
    private Integer barWidth;
    /**
     * 柱条（K线蜡烛）最大宽度，不设时自适应
     */
    private Integer barMaxWidth;
    /**
     * 柱间距离，默认为柱形宽度的30%，可设固定值
     */
    private String barGap;
    /**
     * 类目间柱形距离，默认为类目间距的20%，可设固定值
     */
    private String barCategoryGap;
    
    private Integer symbolSize;//

    /**
     * 构造函数
     */
    public PictorialBar() {
        this.type(SeriesType.pictorialBar);
    }
    
    /**
     * 
     */
    private String symbolRepeat;
    
    /**
     * 
     */
    private String symbolMargin;
    
    /**
     * 
     */
    private boolean symbolClip;
    
    /**
     * 
     */
    private Integer symbolBoundingData;

    /**
     * 构造函数,参数:name
     *
     * @param name
     */
    public PictorialBar(String name) {
        super(name);
        this.type(SeriesType.pictorialBar);
    }

    /**
     * 获取barMinHeight值
     */
    public Integer barMinHeight() {
        return this.barMinHeight;
    }

    /**
     * 设置barMinHeight值
     *
     * @param barMinHeight
     */
    public PictorialBar barMinHeight(Integer barMinHeight) {
        this.barMinHeight = barMinHeight;
        return this;
    }

    /**
     * 获取barWidth值
     */
    public Integer barWidth() {
        return this.barWidth;
    }

    /**
     * 设置barWidth值
     *
     * @param barWidth
     */
    public PictorialBar barWidth(Integer barWidth) {
        this.barWidth = barWidth;
        return this;
    }

    /**
     * 获取barMaxWidth值
     */
    public Integer barMaxWidth() {
        return this.barMaxWidth;
    }

    /**
     * 设置barMaxWidth值
     *
     * @param barMaxWidth
     */
    public PictorialBar barMaxWidth(Integer barMaxWidth) {
        this.barMaxWidth = barMaxWidth;
        return this;
    }

    /**
     * 获取barGap值
     */
    public String barGap() {
        return this.barGap;
    }

    /**
     * 设置barGap值
     *
     * @param barGap
     */
    public PictorialBar barGap(String barGap) {
        this.barGap = barGap;
        return this;
    }

    /**
     * 获取barCategoryGap值
     */
    public String barCategoryGap() {
        return this.barCategoryGap;
    }

    /**
     * 设置barCategoryGap值
     *
     * @param barCategoryGap
     */
    public PictorialBar barCategoryGap(String barCategoryGap) {
        this.barCategoryGap = barCategoryGap;
        return this;
    }

    /**
     * 获取barMinHeight值
     */
    public Integer getBarMinHeight() {
        return barMinHeight;
    }

    /**
     * 设置barMinHeight值
     *
     * @param barMinHeight
     */
    public void setBarMinHeight(Integer barMinHeight) {
        this.barMinHeight = barMinHeight;
    }

    /**
     * 获取barGap值
     */
    public String getBarGap() {
        return barGap;
    }

    /**
     * 设置barGap值
     *
     * @param barGap
     */
    public void setBarGap(String barGap) {
        this.barGap = barGap;
    }

    /**
     * 获取barCategoryGap值
     */
    public String getBarCategoryGap() {
        return barCategoryGap;
    }

    /**
     * 设置barCategoryGap值
     *
     * @param barCategoryGap
     */
    public void setBarCategoryGap(String barCategoryGap) {
        this.barCategoryGap = barCategoryGap;
    }

    /**
     * 获取barWidth
     */
    public Integer getBarWidth() {
        return barWidth;
    }

    /**
     * 设置barWidth
     *
     * @param barWidth
     */
    public void setBarWidth(Integer barWidth) {
        this.barWidth = barWidth;
    }

    /**
     * 获取barMaxWidth
     */
    public Integer getBarMaxWidth() {
        return barMaxWidth;
    }

    /**
     * 设置barMaxWidth
     *
     * @param barMaxWidth
     */
    public void setBarMaxWidth(Integer barMaxWidth) {
        this.barMaxWidth = barMaxWidth;
    }

	public String getSymbolRepeat() {
		return symbolRepeat;
	}
	
	public String SymbolRepeat() {
		return symbolRepeat;
	}

	public void setSymbolRepeat(String symbolRepeat) {
		this.symbolRepeat = symbolRepeat;
	}
	
	public void SymbolRepeat(String symbolRepeat) {
		this.symbolRepeat = symbolRepeat;
	}

	public String getSymbolMargin() {
		return symbolMargin;
	}
 
	public void setSymbolMargin(String symbolMargin) {
		this.symbolMargin = symbolMargin;
	}
	
	public String SymbolMargin() {
		return symbolMargin;
	}
	
	public void SymbolMargin(String symbolMargin) {
		this.symbolMargin = symbolMargin;
	}

	public boolean getSymbolClip() {
		return symbolClip;
	}

	public void setSymbolClip(boolean symbolClip) {
		this.symbolClip = symbolClip;
	}
	
	public boolean SymbolClip() {
		return symbolClip;
	}
	
	public void SymbolClip(boolean symbolClip) {
		this.symbolClip = symbolClip;
	}

	public Integer getSymbolBoundingData() {
		return symbolBoundingData;
	}

	public void setSymbolBoundingData(Integer symbolBoundingData) {
		this.symbolBoundingData = symbolBoundingData;
	}
	
	public Integer SymbolBoundingData() {
		return symbolBoundingData;
	}
	
	public void SymbolBoundingData(Integer symbolBoundingData) {
		this.symbolBoundingData = symbolBoundingData;
	}

	public Integer getSymbolSize() {
		return symbolSize;
	}

	public void setSymbolSize(Integer symbolSize) {
		this.symbolSize = symbolSize;
	}
	
	public Integer SymbolSize() {
		return symbolSize;
	}
	
	public void SymbolSize(Integer symbolSize) {
		this.symbolSize = symbolSize;
	}
    
}
