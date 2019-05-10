package com.banling.sc.register.controller;

import com.banling.sc.register.model.ApplicationInfo;
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
@RefreshScope
public class ApplicationController {

        @Value("${spring.datasource.url}")
    private String dbUrl;

//    @Value("${spring.datasource.type}")
//    private String type;

    @Value("${loginUsername}")
    private String loginUsername;

    @Value("${loginPassword}")
    private String loginPassword;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.filters}")
    private String filters;

    @Value("${spring.datasource.logSlowSql}")
    private String logSlowSql;

    @Autowired
    ApplicationInfoService applicationInfoService;

    @GetMapping(value = "/getList")
    public ResponseEntity getApplicationList(@RequestParam String name) {
        System.out.println("刷新配置:"+toString());

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

    @Override
    public String toString() {
        return "ApplicationController{" +
                "dbUrl='" + dbUrl + '\'' +
                ", loginUsername='" + loginUsername + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", driverClassName='" + driverClassName + '\'' +
                ", initialSize=" + initialSize +
                ", minIdle=" + minIdle +
                ", maxActive=" + maxActive +
                ", maxWait=" + maxWait +
                ", timeBetweenEvictionRunsMillis=" + timeBetweenEvictionRunsMillis +
                ", minEvictableIdleTimeMillis=" + minEvictableIdleTimeMillis +
                ", validationQuery='" + validationQuery + '\'' +
                ", testWhileIdle=" + testWhileIdle +
                ", testOnBorrow=" + testOnBorrow +
                ", testOnReturn=" + testOnReturn +
                ", filters='" + filters + '\'' +
                ", logSlowSql='" + logSlowSql + '\'' +
                ", applicationInfoService=" + applicationInfoService +
                '}';
    }
}
