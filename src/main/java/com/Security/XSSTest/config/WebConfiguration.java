/**package com.Security.XSSTest.config;

import com.alibaba.fastjson2.support.spring6.http.converter.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.List;

@ComponentScan("com.Security.XSSTest.controller")
@EnableWebMvc
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine springTemplateEngine) {

        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setOrder(1);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateEngine(springTemplateEngine); return resolver;

    }
    @Bean
    public SpringResourceTemplateResolver templateResolver() {

        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setSuffix(".html");
        resolver.setPrefix("classpath:/XSSTest/"); return resolver;

    }
    @Bean
    public SpringTemplateEngine springTemplateEngine(ITemplateResolver resolver) {

        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(resolver); return engine;

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new FastJsonHttpMessageConverter());
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/src/main/webapp/static/**").addResourceLocations("/src/main/webapp/static/");
    }

}**/
