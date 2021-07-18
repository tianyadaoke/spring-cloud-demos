package org.zb.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zb.client.feign.fallback.MobileServiceFallback;

@FeignClient(value = "mobile",fallbackFactory = MobileServiceFallback.class)
public interface MobileService {
    @GetMapping("/mobile/check")
    public Boolean checkMobile(@RequestParam String mobile);
}
