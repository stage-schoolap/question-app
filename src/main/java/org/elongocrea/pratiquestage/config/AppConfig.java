package org.elongocrea.pratiquestage.config;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;



@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) { // Allow to specify desired locale in each request
        final LocaleChangeInterceptor intercep = new LocaleChangeInterceptor();
        intercep.setParamName("locale");// Request Param to intercept
        registry.addInterceptor(intercep);
    }

    //
    @Bean(name="messageSource")
    public MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasenames("classpath:Bundle"); // Bundle refers to file name
        resource.setDefaultEncoding("ISO-8859-1"); // UTF-8
        resource.setAlwaysUseMessageFormat(true); // Handle Apostroph & others
        resource.setUseCodeAsDefaultMessage(true);

        return resource;
    }

    @Bean(name = "localeResolver")
    public LocaleResolver localeResolver() { // Identify which locale is used
        return new SessionLocaleResolver();
    }

    @Bean
    public LayoutDialect layoutDialect(){
        return new LayoutDialect();
    }

}
