package com.sitewhere.measurefilter.mvc;

import com.sitewhere.measurefilter.mvc.controller.DeviceFieldController;
import com.sitewhere.measurefilter.mvc.controller.MeasureFilterController;
import com.sitewhere.measurefilter.mvc.service.impl.DeviceInterfaceServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * Created by CQ on 2016/11/16.
 *
 * @author Joeg
 */

@Configuration
@ComponentScan(basePackageClasses = {DeviceFieldController.class})
public class MFMvcConfiguration extends WebMvcConfigurationSupport {

    /**
     * URL prefix for matching MeasureFilter calls
     */
    public static final String MF_MATCHER = "/mf/*";

    /**
     * Ignore path extension on URLs.
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

//    @Bean
//    public UrlBasedViewResolver viewResolver() {
//        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
//        resolver.setViewClass(JstlView.class);
//        resolver.setPrefix("/WEB-INF/jsp/");
//        resolver.setSuffix(".jsp");
//        return resolver;
//    }


}
