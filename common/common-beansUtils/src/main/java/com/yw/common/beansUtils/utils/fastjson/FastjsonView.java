package com.yw.common.beansUtils.utils.fastjson;

import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * 功   能:
 * 创建者: 施俊帆
 * 日   期: 2014/8/12 18:25
 * Q  Q: 362116120
 */
public class FastjsonView extends AbstractView {
    private Object value;
    public FastjsonView(Object value) {
        this.value = value;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        OutputStream out = (response.getOutputStream());
        out.write(JSON.toJSONBytes(value));
        out.flush();
    }
}
