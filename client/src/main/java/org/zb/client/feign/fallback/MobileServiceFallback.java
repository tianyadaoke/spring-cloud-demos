package org.zb.client.feign.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.zb.client.feign.MobileService;

@Slf4j
@Component
public class MobileServiceFallback implements FallbackFactory<MobileService> {

    @Override
    public MobileService create(Throwable cause) {
        return new MobileService() {
            @Override
            public Boolean checkMobile(String mobile) {
                log.info("remote open feign call failure");
                log.info("check in database if mobile exists");
                if("8888".equals(mobile)){
                    return false;
                }
                return true; //mobile is valid
            }
        };
    }
}
