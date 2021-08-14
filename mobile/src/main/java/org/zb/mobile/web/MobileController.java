package org.zb.mobile.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/mobile")
@Slf4j
public class MobileController {
    @Value("${pattern.dateformat}")
    private String dateFormat;
    @GetMapping("/now")
    public String getDateFormat(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateFormat));
    }
    @GetMapping("/check")
    public Boolean checkMobile(@RequestParam String mobile){
        log.info("check mobile number :"+mobile);
        if("8888".equals(mobile)) return false;
        return true;
    }
}
