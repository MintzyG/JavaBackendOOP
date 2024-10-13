package com.demo.finance;

import com.demo.finance.filters.AuthFilters;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FinanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<AuthFilters> filterRegistrationBean() {
        FilterRegistrationBean<AuthFilters> filterRegistrationBean = new FilterRegistrationBean<>();
        AuthFilters authFilters = new AuthFilters();
        filterRegistrationBean.setFilter(authFilters);
        filterRegistrationBean.addUrlPatterns("/api/v1/categories/*");
        return filterRegistrationBean;
    }
}
