package org.zb.client.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class DefaultFeignConfiguration {
    @Bean
    public Logger.Level logLevel(){
        // FULL,NONE,BASIC...最好选NONE或者BASIC
        return Logger.Level.BASIC;
    }
}
