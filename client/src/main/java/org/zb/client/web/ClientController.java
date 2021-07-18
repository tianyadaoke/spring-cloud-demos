package org.zb.client.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zb.client.feign.MobileService;

import java.util.UUID;

@RestController
@RequestMapping("/client")
@Slf4j
public class ClientController {
    @Autowired
    MobileService mobileService;
    @GetMapping("/code")
    public String getCode(@RequestParam("mobile") String mobile){
        log.info("get client mobile number ,try to call mobile service to check mobile exists");
        Boolean isValid = mobileService.checkMobile(mobile);
        if(isValid==true){
            return UUID.randomUUID().toString().substring(0,6);
        }
        return "mobile is already exists";

    }
}
