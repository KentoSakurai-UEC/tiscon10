package com.tiscon10;

import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.ViewResolver;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.helper.ConditionalHelpers;
import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;
import com.tiscon10.viewhelper.InsuranceTypeHelper;
import com.tiscon10.viewhelper.SpringMVCHelper;

/**
 * Spring Bootのエントリーポイント。
 * 
 * @author TIS Taro
 * 
 */
@SpringBootApplication
public class Tiscon10Application {

    public static void main(String[] args) {
        SpringApplication.run(Tiscon10Application.class, args);
    }

    @Bean
    public ViewResolver viewResolver() {
        HandlebarsViewResolver viewResolver = new HandlebarsViewResolver();
        viewResolver.setPrefix("classpath:/templates/");
        viewResolver.setSuffix(".hbs");
        viewResolver.setCache(false);
        Map<String, Helper<?>> helpers = Map.of(
            "eq", ConditionalHelpers.eq,
            "not", ConditionalHelpers.not,
            "fieldErrors", new SpringMVCHelper.FieldErrorsHelper(),
            "hasFieldErrors", new SpringMVCHelper.HasFieldErrorsHelper(),
            "insuranceType", new InsuranceTypeHelper()
        );
        viewResolver.setHelpers(helpers);
        return viewResolver;
    }
}
