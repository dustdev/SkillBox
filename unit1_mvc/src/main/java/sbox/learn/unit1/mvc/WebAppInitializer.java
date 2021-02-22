package sbox.learn.unit1.mvc;

import lombok.var;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import sbox.learn.unit1.mvc.app.config.AppContextConfig;
import sbox.learn.unit1.mvc.web.config.WebContextConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Конфигурация приложения Spring
 */
public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        // Конфигурация Root-контекста
        var appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(AppContextConfig.class);

        // Sets ContextLoaderListener to servletContext
        servletContext.addListener(new ContextLoaderListener(appContext));

        // Конфигурация Web-контекста
        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();

        // Registers annotated configurations class
        webContext.register(WebContextConfig.class);

        // Создание и регистрация на "/" эскземпляра DispatcherSevlet
        var dispetcherServlet = new DispatcherServlet(webContext);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispetcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

    }
}
