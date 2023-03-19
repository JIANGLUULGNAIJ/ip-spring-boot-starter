package xwx.jl.config.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xwx.jl.interceptor.IpCountInterceptor;


// webMvc的配置类
// proxyBeanMethods = true 默认就是单例对象，不会被拦截进行CGLIB代理
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    // 添加我们刚刚做好的拦截器到Mvc中
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ipCountInterceptor()).addPathPatterns("/*/**");
    }

    // 创建的拦截器对象要求是单例的
    @Bean
    public IpCountInterceptor ipCountInterceptor(){
        return new IpCountInterceptor();
    }

}
