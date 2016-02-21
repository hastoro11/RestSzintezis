package com.sornyei.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by gaborsornyei on 16. 02. 20..
 */
@Configuration
@ComponentScan(basePackages = {"com.sornyei"})
@EnableWebMvc
public class AppConfig {
}
