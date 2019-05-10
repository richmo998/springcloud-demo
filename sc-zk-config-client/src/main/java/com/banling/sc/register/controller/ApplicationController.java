package com.banling.sc.register.controller;

import com.banling.sc.register.model.ApplicationInfo;
import com.banling.sc.register.model.DruidConfig;
import com.banling.sc.register.service.ApplicationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class ApplicationController {

    @Autowired
    DruidConfig druidConfig;

    @Autowired
    ApplicationInfoService applicationInfoService;

    @GetMapping(value = "/getList")
    public ResponseEntity getApplicationList(@RequestParam String name) {

        ResponseEntity responseEntity = null;

        try {
            List<ApplicationInfo> applicationInfos = applicationInfoService.selectByAppNameList(name);

            responseEntity = new ResponseEntity(applicationInfos,HttpStatus.OK);
            System.out.println("查询完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }


        return responseEntity;
    }

    @GetMapping(value = "/configs")
    public String getConfig(){
        System.out.println("是否能动态获取配置信息：");
        return druidConfig.toString();
    }
}
