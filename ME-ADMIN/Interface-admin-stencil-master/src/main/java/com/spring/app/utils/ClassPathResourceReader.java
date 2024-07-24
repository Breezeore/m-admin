package com.spring.app.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * @ClassName ClassPathResourceReader
 * @Date 2023/10/17 14:51
 * @Version 1.0
 **/
public class ClassPathResourceReader {
    /**
     * path:文件路径
     * @since JDK 1.8
     */
    private final String path;
    /**
     * content:文件内容
     * @since JDK 1.6
     */
    private String content;
    public ClassPathResourceReader(String path) {
        this.path = path;
    }
    public String getContent() {
        if (content == null) {
            try {
                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
                if (inputStream!=null) {
                    content = new BufferedReader(new InputStreamReader(inputStream))
                            .lines().collect(Collectors.joining("\n"));
                }else {
                    throw new RuntimeException("File does not exist");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return content;
    }
}
