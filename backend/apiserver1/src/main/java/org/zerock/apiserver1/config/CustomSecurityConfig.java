package org.zerock.apiserver1.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.zerock.apiserver1.security.filter.JWTCheckFilter;
import org.zerock.apiserver1.security.handler.APILoginFailHandler;
import org.zerock.apiserver1.security.handler.APILoginSuccessHandler;
import org.zerock.apiserver1.security.handler.CustomAccessDeniedHandler;

import java.util.Arrays;

@Configuration
@Log4j2
@EnableMethodSecurity
@RequiredArgsConstructor
public class CustomSecurityConfig {

@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    log.info("----------------- security--------------------------------");
    http.cors(httpSecurityCorsConfigurer -> {
        httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource());
    }); //cors설정

// api서버는 기본적으로 무상태임 그상태에서 서비스를 알려면 세션을 가능하면 안만들어줘야한다고함


http.sessionManagement(httpSecuritySessionManagementConfigurer -> {
    httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.NEVER);
});



    http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable()); //csrf 비활성화

    http.formLogin(config->{
        config.loginPage("/api/member/login");
        config.successHandler( new APILoginSuccessHandler());
        config.failureHandler(new APILoginFailHandler());
    });

http.addFilterBefore(new JWTCheckFilter(), UsernamePasswordAuthenticationFilter.class);
http.exceptionHandling(config->{
   config.accessDeniedHandler(new CustomAccessDeniedHandler());
});

    return http.build();
}


@Bean
public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder(); //패스워드 암호화
}





    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*")); //모든 출처에서 요청을 허용하는 설정입니다. 즉, 클라이언트가 어디에서 요청을 보내든 서버가 이를 받아들일 수 있다는 뜻
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE")); //허용할 HTTP 메서드를 명시합니다
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type")); //떤 헤더가 요청에 포함될 수 있는지 정의합니다. 이중 Authorization은 주로 JWT 같은 인증 토큰을 서버로 보낼 때 사용되고, Cache-Control, Content-Type은 데이터 전송에 관련된 헤더들입니다.
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
