package com.sitewhere.measurefilter.mvc.controller;

import com.sitewhere.measurefilter.mvc.dao.UserRepository;
import com.sitewhere.measurefilter.mvc.domain.AppUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Spring MVC controller for MeasurementsFilter application
 * Created by CQ on 2016/11/15.
 *
 * @author Joeg
 */
@Controller
@RequestMapping
public class MeasureFilterController {

    /** Static logger instance */
    private static Logger LOGGER = Logger.getLogger(MeasureFilterController.class);

    @Autowired
    private UserRepository userRepository;
    /**
     * Display the login page (this login page is for MeasureFilter)
     * @return
     */
    @RequestMapping("/")
    public ModelAndView login(){
        AppUser temp = userRepository.findByUsername("a");

        return new ModelAndView("login");
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "";
    }


}
