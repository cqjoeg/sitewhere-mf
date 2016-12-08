package com.sitewhere.measurefilter.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * MF configuration
 *
 * @author Joeg
 */
@Configuration
@ComponentScan(basePackages = {"com.sitewhere.measurefilter.api","com.sitewhere.measurefilter.mvc"})
public class MFConfiguration {

}
