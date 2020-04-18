package com.gao.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author:Gao
 * @Date:2020-04-14 19:59
 */
@ComponentScan("com.gao")
@SpringBootApplication (exclude = { org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class })
public class HrApplication {
    public static void main(String[] args) {
        SpringApplication.run(HrApplication.class,args);
    }
}
