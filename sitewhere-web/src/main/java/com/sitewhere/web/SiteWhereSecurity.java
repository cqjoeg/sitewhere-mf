/*
 * Copyright (c) SiteWhere, LLC. All rights reserved. http://www.sitewhere.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package com.sitewhere.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.sitewhere.SiteWhere;
import com.sitewhere.security.SitewhereAuthenticationProvider;
import com.sitewhere.spi.user.SiteWhereRoles;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SiteWhereSecurity {

    @Order(1)
    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
    public static class RestSecurityConfiguration extends WebSecurityConfigurerAdapter {

        /*
         * (non-Javadoc)
         *
         * @see org.springframework.security.config.annotation.web.configuration.
         * WebSecurityConfigurerAdapter#authenticationManagerBean()
         */
        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        /*
         * (non-Javadoc)
         *
         * @see org.springframework.security.config.annotation.web.configuration.
         * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
         * annotation.authentication.builders.AuthenticationManagerBuilder)
         */
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(
                    new SitewhereAuthenticationProvider(SiteWhere.getServer().getUserManagement()));
        }

        /*
         * (non-Javadoc)
         *
         * @see org.springframework.security.config.annotation.web.configuration.
         * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
         * annotation.web.builders.HttpSecurity)
         */
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().antMatcher(
                    "/api/**").authorizeRequests().antMatchers(HttpMethod.OPTIONS,
                    "/api/**").permitAll().antMatchers(HttpMethod.GET,
                    "/api/**/symbol").permitAll().antMatchers("/api/**").hasRole(
                    SiteWhereRoles.AUTH_REST).and().httpBasic();
        }
    }

    @Order(2)
    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
    public static class MvcSecurityConfiguration extends WebSecurityConfigurerAdapter {

        /*
         * (non-Javadoc)
         *
         * @see org.springframework.security.config.annotation.web.configuration.
         * WebSecurityConfigurerAdapter#authenticationManagerBean()
         */
        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        /*
         * (non-Javadoc)
         *
         * @see org.springframework.security.config.annotation.web.configuration.
         * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
         * annotation.authentication.builders.AuthenticationManagerBuilder)
         */
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(
                    new SitewhereAuthenticationProvider(SiteWhere.getServer().getUserManagement()));
        }

        /*
         * (non-Javadoc)
         *
         * @see org.springframework.security.config.annotation.web.configuration.
         * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
         * annotation.web.builders.WebSecurity)
         */
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/css/**", "/fonts/**", "/img/**", "/lib/**", "/locales/**",
                    "/scripts/**");
        }

        /*
         * (non-Javadoc)
         *
         * @see org.springframework.security.config.annotation.web.configuration.
         * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
         * annotation.web.builders.HttpSecurity)
         */
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.requestMatcher(new RequestMatcher() {
                @Override
                public boolean matches(HttpServletRequest request) {
                    System.out.println("\n\n\nMATCHING ON REST PATTERNS!!!!\n\n\n");
                    return true;
                }
            });
            http.csrf().disable();
            http.requestMatchers().antMatchers("/admin/**", "/logout");
            http.authorizeRequests().antMatchers("/admin", "/admin/",
                    "/admin/loginFailed.html").permitAll().antMatchers("/admin/**").hasRole(
                    SiteWhereRoles.AUTH_ADMIN_CONSOLE).anyRequest().authenticated();
            http.formLogin().loginPage("/admin/").loginProcessingUrl("/admin/login.html").defaultSuccessUrl(
                    "/admin/tenant.html").failureUrl("/admin/loginFailed.html");
            http.logout().logoutSuccessUrl("/admin/");
        }
    }

//    @Order(3)
//    @Configuration
//    @EnableWebSecurity
//    @EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//    public static class MFRestSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//        /*
//         * (non-Javadoc)
//         *
//         * @see org.springframework.security.config.annotation.web.configuration.
//         * WebSecurityConfigurerAdapter#authenticationManagerBean()
//         */
//        @Bean
//        @Override
//        public AuthenticationManager authenticationManagerBean() throws Exception {
//            return super.authenticationManagerBean();
//        }
//
//        /*
//         * (non-Javadoc)
//         *
//         * @see org.springframework.security.config.annotation.web.configuration.
//         * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
//         * annotation.authentication.builders.AuthenticationManagerBuilder)
//         */
//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.authenticationProvider(
//                    new SitewhereAuthenticationProvider(SiteWhere.getServer().getUserManagement()));
//        }
//
//        /*
//         * (non-Javadoc)
//         *
//         * @see org.springframework.security.config.annotation.web.configuration.
//         * WebSecurityConfigurerAdapter#configure(org.springframework.security.config.
//         * annotation.web.builders.HttpSecurity)
//         */
//        protected void configure(HttpSecurity http) throws Exception {
//            http.csrf().disable();
//            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().antMatcher(
//                    "/mf/api/**").authorizeRequests().antMatchers(HttpMethod.OPTIONS,
//                    "/mf/api/**").permitAll().antMatchers(HttpMethod.GET,
//                    "/mf/api/**/symbol").permitAll().antMatchers("/mf/api/**").hasRole(
//                    SiteWhereRoles.AUTH_REST).and().httpBasic();
//        }
//    }


//    /**
//     * 提供一个 rest 接口
//     * /mf/login
//     * 参数
//     * username :
//     * pasword  :
//     *
//     * 成功返回：SiteWhereRestAuthentication 类型的 json 格式数据
//     */
//    @Order(4)
//    @Configuration
//    @EnableWebSecurity
//    @EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//    public static class MFSecurityConfiguration extends WebSecurityConfigurerAdapter {
//        @Bean
//        @Override
//        public AuthenticationManager authenticationManagerBean() throws Exception {
//            return super.authenticationManagerBean();
//        }
//
//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.authenticationProvider(
//                    new SiteWhereRestAuthenticationProvider(SiteWhere.getServer().getUserManagement()));
//        }
//
//        @Override
//        public void configure(WebSecurity web) throws Exception {
//            web.ignoring().antMatchers("/css/**", "/fonts/**", "/img/**", "/lib/**", "/locales/**",
//                    "/scripts/**");
//        }
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.requestMatcher(new RequestMatcher() {
//                @Override
//                public boolean matches(HttpServletRequest request) {
//                    System.out.println("\n\n\nMATCHING ON MeasureFilter!!!!\n\n\n");
//                    return true;
//                }
//            });
//            http.csrf().disable();
//            http.requestMatchers().antMatchers("/mf/**", "/logout");
//            http.authorizeRequests()
//                    .antMatchers("/mf", "/mf/", "/mf/loginFailed.html").permitAll()
//                    .antMatchers("/mf/**").hasRole(SiteWhereRoles.AUTH_ADMIN_CONSOLE).anyRequest().authenticated();
//            http.formLogin().loginPage("/mf/login")
//                    .usernameParameter("username")
//                    .passwordParameter("password")
//                    .successHandler(new SecurityAccessHandler())
//                    .failureHandler(new SecurityAccessHandler());
//            http.logout().logoutSuccessUrl("/mf/");
//        }
//    }


}
