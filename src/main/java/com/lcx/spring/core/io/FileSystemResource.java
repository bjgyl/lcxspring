package com.lcx.spring.core.io;/**
 * Created by lichenxiang on 2018/8/23.
 */

import com.lcx.spring.util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p> 文件系统资源 </p>
 *
 * @author lichenxiang
 * @version FileSystemResource: FileSystemResource.java, v 0.1 2018年08月23日 下午7:08:08 lichenxiang Exp $
 */
public class FileSystemResource implements Resource {

    private final String path;
    private final File file;


    public FileSystemResource(String path) {
        Assert.notNull(path, "Path must not be null");
        this.file = new File(path);
        this.path = path;
    }

    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    public String getDescription() {
        return "file [" + this.file.getAbsolutePath() + "]";
    }

}