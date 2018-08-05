package com.hibi.www.config;


import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.util.Properties;

/**
 * 日期：2018-6-28
 * 作者：宠爱
 * 类名：MyBaitesConfiguration
 * 描述：Mybatis配置类
 */

public class MyBaitesConfiguration {

    public SqlSessionFactory sqlSessionFactory(){
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setTypeAliasesPackage("com.hibi.www.domain");
//        如果引入了springboot环境使用的pagehelper后，不需要此处设置分页插件
//        设置分页插件
//        PageHelper helper = new PageHelper();
//        Properties properties = new Properties();
//        properties.setProperty("reasonable", "true");
//        properties.setProperty("supportMethodsArguments", "true");
//        properties.setProperty("returnPageInfo", "check");
//        properties.setProperty("params", "count=countSql");
//        helper.setProperties(properties);
        //添加分页插件
//        bean.setPlugins(new Interceptor[]{helper});
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            //基于注解扫描Mapper，不需配置xml路径
            //bean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
