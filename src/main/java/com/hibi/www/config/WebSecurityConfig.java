package com.hibi.www.config;


import com.hibi.www.domain.Permission;
import com.hibi.www.filter.KaptchaAuthenticationFilter;
import com.hibi.www.interceptor.MyFilterSecurityInterceptor;
import com.hibi.www.service.impl.IPermissionService;
import com.hibi.www.service.impl.IUserService;
import com.hibi.www.tools.LogTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用方法安全设置
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Autowired
    private IUserService userService;

    @Autowired
    private IPermissionService permissionService;



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();// 使用 BCrypt 加密
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder()); // 设置密码加密方式
        return authenticationProvider;
    }
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //动态获取管理员权限
        List<String> admin = permissionService.findPermissionByLike("ADMIN");
        LogTool.printLog(this.getClass(),admin.toString(),1);
        http.addFilterBefore(new KaptchaAuthenticationFilter("/login","/login?error")
                , UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests().antMatchers("static/**","error/**").permitAll()
        .antMatchers("/admin/**").hasAnyRole(admin.toString().replace("[","").replace("]",""))
                .antMatchers("/user/**").hasAnyRole("USER")
                .antMatchers("/socket/**").permitAll()
                .antMatchers("/menu/**").hasAnyRole("ADMIN","USER")
        .and()
        .formLogin()
        .loginPage("/login")
        .successHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                boolean flag = true;
                for (String str:admin) {
                    System.out.println(str+"----"+authorities.iterator().next().getAuthority());
                    System.out.println(str.equals(authorities.iterator().next().getAuthority().split("_")[1]));
                    if (str.equals(authorities.iterator().next().getAuthority().split("_")[1]) == true) {
                        flag = true;
                        break;
                    }else {
                        flag = false;
                    }
                }
                if (principal != null && principal instanceof UserDetails && flag == true) {
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter out = httpServletResponse.getWriter();
                    out.write("{\"status\":\"ok\",\"message\":\"登录成功\"}");
                    out.flush();
                    out.close();
                }else{
                    httpServletResponse.setContentType("application/json;charset=utf-8");
                    PrintWriter out = httpServletResponse.getWriter();
                    out.write("{\"status\":\"faild\",\"message\":\"对不起，您没有权限访问该页面\"}");
                    out.flush();
                    out.close();
                }
            }
        }).failureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = httpServletResponse.getWriter();
                out.write("{\"status\":\"error\",\"message\":\"用户名或密码错误\"}");
                out.flush();
                out.close();
            }
        }).and().exceptionHandling().accessDeniedPage("/error/403");
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/login");
        http.csrf().ignoringAntMatchers("/h2-console/**");// 禁用 H2 控制台的 CSRF 防护
        http.csrf().ignoringAntMatchers("/ajax/**");// 禁用 H2 控制台的 CSRF 防护
        http.csrf().ignoringAntMatchers("/upload");
        http.headers().frameOptions().sameOrigin();// 允许来自同一来源的H2 控制台的请求

//        http.authorizeRequests()
//                .antMatchers("static/**").permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .failureUrl("/login?error")
//                .permitAll() //登录页面用户任意访问
//                .and()
//                .logout().permitAll()
//                .and()
//                .csrf().disable(); //注销行为任意访问
//        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }

    /**
     * 认证信息管理
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
        auth.authenticationProvider(authenticationProvider());
    }

}
