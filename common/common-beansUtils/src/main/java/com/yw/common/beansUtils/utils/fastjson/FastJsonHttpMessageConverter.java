package com.yw.common.beansUtils.utils.fastjson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Collection;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastJsonHttpMessageConverter extends
        AbstractHttpMessageConverter<Object> {

    public final static Charset UTF8 = Charset.forName("UTF-8");

    private Charset charset = UTF8;
    
//    public static final String AES_KEY = "3hwqxR1W4dF&1*37";//AES密钥

    private SerializerFeature[] serializerFeature;

    public FastJsonHttpMessageConverter() {
        super(new MediaType("application", "json", UTF8), new MediaType(
                "application", "*+json", UTF8));
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    public Charset getCharset() {
        return this.charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public SerializerFeature[] getFeatures() {
        return serializerFeature;
    }

    public void setFeatures(SerializerFeature... features) {
        this.serializerFeature = features;
    }

    @Override
    protected Object readInternal(Class<? extends Object> clazz,
                                  HttpInputMessage inputMessage) throws IOException,
            HttpMessageNotReadableException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream in = inputMessage.getBody();
        byte[] buf = new byte[1024];
        for (;;) {
            int len = in.read(buf);
            if (len == -1) {
                break;
            }

            if (len > 0) {
                baos.write(buf, 0, len);
            }
        }

        byte[] bytes = baos.toByteArray();

        if(Collection.class.isAssignableFrom(clazz)){
            return JSON.parseArray(new String(bytes));
        }

        if (charset == UTF8) {
            return JSON.parseObject(bytes, clazz);
        } else {
            return JSON.parseObject(bytes, 0, bytes.length,
                    charset.newDecoder(), clazz);
        }
    }

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {

    	 try {
    		 
	        OutputStream out = outputMessage.getBody();
	        byte[] bytes;
	
	        if (charset == UTF8) {
	            if (serializerFeature != null) {
	                bytes = JSON.toJSONBytes(obj, serializerFeature);
	            } else {
	                bytes = JSON.toJSONBytes(obj);
	            }
	
	        } else {
	            String text;
	            if (serializerFeature != null) {
	                text = JSON.toJSONString(obj, serializerFeature);
	            } else {
	                text = JSON.toJSONString(obj);
	            }
	            bytes = text.getBytes(charset);
	        }
             out.write(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}