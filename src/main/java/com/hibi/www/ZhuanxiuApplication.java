package com.hibi.www;

import com.hibi.www.filter.WsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ServletComponentScan
public class ZhuanxiuApplication {

//
//	@Bean
//	public FilterRegistrationBean myFilterRegistration(){
//		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//		filterRegistrationBean.setFilter(new WsFilter());
//		filterRegistrationBean.addUrlPatterns("/*");
//		filterRegistrationBean.addInitParameter("paramName","paramValue");
//		filterRegistrationBean.setName("WsFilter");
//		filterRegistrationBean.setOrder(2);
//		return  filterRegistrationBean;
//	}



	public static void main(String[] args) {
		SpringApplication.run(ZhuanxiuApplication.class, args);
	}


}
