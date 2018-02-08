package com.diviso.purchase.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.diviso.purchase")
public class FeignConfiguration {

}
