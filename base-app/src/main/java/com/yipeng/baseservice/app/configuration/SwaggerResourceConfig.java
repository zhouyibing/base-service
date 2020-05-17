package com.yipeng.baseservice.app.configuration;

import com.yipeng.baseservice.app.result.AppInfoResult;
import com.yipeng.baseservice.app.service.AppInfoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Primary
@AllArgsConstructor
public class SwaggerResourceConfig implements SwaggerResourcesProvider {

    @Autowired
    private AppInfoService appInfoService;

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<AppInfoResult> appInfoResults = appInfoService.getAll();
        if(CollectionUtils.isEmpty(appInfoResults)) {
            return resources;
        }
        appInfoResults.forEach(appInfoResult -> {
            if(!StringUtils.isEmpty(appInfoResult.getDocUrl())) {
                resources.add(swaggerResource(appInfoResult.getServiceName(), appInfoResult.getDocUrl()));
            }
        });

        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        log.info("name:{},location:{}",name,location);
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}