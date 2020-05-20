package com.ntu.gmc.graduation.design.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zed
 * @date 2020/5/20 下午8:36
 * @contact shadowl91@163.com
 */
@Configuration
public class MyConfig {

    @Value("${value.file.path}")
    public String filePath;
}
