package com.sample.project.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.sample.project.util.StaticConstants;

/**
 * Initializes web application context for the application
 * 
 */
public class WebInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(Config.class);
        ctx.setServletContext(servletContext);
        Dynamic servlet = servletContext.addServlet(StaticConstants.WEB_INITIALIZER_SERVLET, new DispatcherServlet(ctx));
        servlet.addMapping(StaticConstants.WEB_INITIALIZER_SERVLET_MAPPING);
        servlet.setLoadOnStartup(1);
    }
}
