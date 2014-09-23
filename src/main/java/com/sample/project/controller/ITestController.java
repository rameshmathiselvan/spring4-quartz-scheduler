package com.sample.project.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.project.util.StaticConstants;

/**
 * Controller to test the web flow
 * 
 */
@Controller
public class ITestController {
    private static final Logger LOGGER = Logger.getLogger(ITestController.class);

    /**
     * It is used to test the web flow
     * 
     * @param name
     * @param model
     * @return ITest page
     */
    @RequestMapping(StaticConstants.CONTROLLER_MAPPING)
    public String testWebFlow(@RequestParam(value = StaticConstants.CONTROLLER_PARAM_NAME, required = false, defaultValue = StaticConstants.PARAM_NAME_DEFAULT_VALUE)
    String name, Model model) {
        LOGGER.info("Inside testWebFlow method");
        model.addAttribute(StaticConstants.CONTROLLER_PARAM_NAME, name);
        return StaticConstants.WEBFLOW_VIEW_NAME;
    }
}
