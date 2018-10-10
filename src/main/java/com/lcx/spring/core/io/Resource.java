package com.lcx.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源类
 * Created by lichenxiang on 2018/8/23.
 */
public interface Resource {

    /**
     * 获取输入流
     * @return
     * @throws IOException
     */
    public InputStream getInputStream() throws IOException;

    /**
     * 获取描述信息
     * @return
     */
    public String getDescription();

}
