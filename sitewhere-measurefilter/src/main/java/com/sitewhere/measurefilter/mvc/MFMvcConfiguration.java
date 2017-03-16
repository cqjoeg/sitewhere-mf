package com.sitewhere.measurefilter.mvc;

import com.sitewhere.measurefilter.mvc.controller.DeviceFieldController;
import com.sitewhere.measurefilter.mvc.service.impl.DeviceFieldServiceImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by CQ on 2016/11/16.
 *
 * @author Joeg
 */

@Configuration
//@ComponentScan(basePackageClasses = {DeviceFieldController.class,DeviceFieldServiceImpl.class}, basePackages = {"com.sitewhere.measurefilter"})
@ComponentScan(basePackageClasses={DeviceFieldController.class,DeviceFieldServiceImpl.class})
//@EnableJpaRepositories(basePackages = "com.sitewhere.measurefilter.mvc.dao")
//@EnableAutoConfiguration
//@EntityScan(basePackages = {"com.sitewhere.measurefilter.mvc.domain"})
public class MFMvcConfiguration extends WebMvcConfigurationSupport {
    /**
     * URL prefix for matching MeasureFilter calls
     */
    public static final String MF_MATCHER = "/mf/api*";
//    /**
//     * Ignore path extension on URLs.
//     */
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.favorPathExtension(false);
//    }
}
