package org.zb.mobile.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mobile")
@Slf4j
public class MobileController {
    @GetMapping("/check")
    public Boolean checkMobile(@RequestParam String mobile){
        log.info("check mobile number :"+mobile);
        if("8888".equals(mobile)) return false;
        return true;
    }
}
