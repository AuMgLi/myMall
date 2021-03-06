package com.jq.mall.config;

import com.jq.mall.dto.AdminUserDetails;
import com.jq.mall.component.JwtAuthenticationTokenFilter;
import com.jq.mall.component.RestfulAccessDeniedHandler;
import com.jq.mall.component.RestfulAuthenticationEntryPoint;
import com.jq.mall.mbg.model.UmsAdmin;
import com.jq.mall.mbg.model.UmsPermission;
import com.jq.mall.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestfulAuthenticationEntryPoint restfulAuthenticationEntryPoint;

    /**
     * ???????????????????????????url?????????jwt????????????????????????????????????
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()  // ??????????????????JWT????????????????????????csrf
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // ??????token??????????????????session
                .and()
            .authorizeRequests()
                .antMatchers(HttpMethod.GET,   // ????????????????????????????????????????????????
                    "/",
                    "/*.html",
                    "/favicon.icon",
                    "/**/*.html",
                    "/**/*.css",
                    "/**/*.js",
                    "/swagger-resources/**",
                    "/v2/api-docs/**").permitAll()
                .antMatchers("/admin/login", "/admin/register").permitAll()  // ????????????????????????????????????
                .antMatchers(HttpMethod.OPTIONS).permitAll()  // ??????????????????????????????options??????
//                    .antMatchers("/**").permitAll()  // ???????????????????????????
                .anyRequest().authenticated();  // ????????????????????????
        http.headers().cacheControl();  // ????????????
        // ??????JWT filter
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // ????????????????????????????????????????????????
        http.exceptionHandling()
            .accessDeniedHandler(restfulAccessDeniedHandler)
            .authenticationEntryPoint(restfulAuthenticationEntryPoint);
    }

    /**
     * ????????????UserDetailsService???PasswordEncoder
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            UmsAdmin admin = adminService.getAdminByUsername(username);
            if (admin != null) {
                List<UmsPermission> permissionList = adminService.getPermissionList(admin.getId());
                return new AdminUserDetails(admin, permissionList);
            }
            throw new UsernameNotFoundException("????????????????????????");
        };
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
