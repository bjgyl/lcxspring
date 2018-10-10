package com.lcx.spring.core.io;/**
 * Created by lichenxiang on 2018/8/23.
 */

import com.lcx.spring.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>项目内部文件资源</p>
 *
 * @author lichenxiang
 * @version ClassPathResource: ClassPathResource.java, v 0.1 2018年08月23日 下午7:09:09 lichenxiang Exp $
 */
public class ClassPathResource implements Resource{

    private String path;
    private ClassLoader classLoader;

    public ClassPathResource(String path) {
        this(path, (ClassLoader) null);
    }

    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    public InputStream getInputStream() throws IOException {
        InputStream is = this.classLoader.getResourceAsStream(this.path);

        if (is == null) {
            throw new FileNotFoundException(path + " cannot be opened");
        }
        return is;

    }
    public String getDescription(){
        return this.path;
    }
}