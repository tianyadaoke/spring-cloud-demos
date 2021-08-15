package org.zb.mobile.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.zb.mobile.config.PatternProperties;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/mobile")
@Slf4j
//@RefreshScope
public class MobileController {
    // @Value("${pattern.dateformat}")
    // private String dateFormat;
    @Autowired
    private PatternProperties properties;

    @GetMapping("/prop")
    public PatternProperties getProperties(@RequestHeader(value = "Truth",required = false) String truth) {
        System.out.println(truth);
        return properties;
    }

    @GetMapping("/now")
    public String getDateFormat() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(properties.getDateformat()));
    }

    @GetMapping("/check")
    public Boolean checkMobile(@RequestParam String mobile) {
        log.info("check mobile number :" + mobile);
        if ("8888".equals(mobile)) return false;
        return true;
    }
}
