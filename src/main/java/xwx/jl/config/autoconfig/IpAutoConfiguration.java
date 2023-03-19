package xwx.jl.config.autoconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import xwx.jl.config.mvc.SpringMvcConfig;
import xwx.jl.properties.IpProperties;
import xwx.jl.service.impl.IpCountServiceImpl;

// 开始定时功能
@EnableScheduling
// 导入这个bean,
// IpProperties.class是用来读取yml配置的，里面配有参数的默认值和枚举
// SpringMvcConfig.class是用来注入拦截器，对每个web请求拦截处理
@Import({IpProperties.class, SpringMvcConfig.class})
public class IpAutoConfiguration {

    /**
     * 自动配置
     * 1.加载这个模块时
     * 2.会加载META-INF/spring.factories这个文件
     * 3.然后加载这个类IpAutoConfiguration
     * 相当于一个启动类
     * @return
     */
    @Bean
    public IpCountServiceImpl ipCountService(){
        return new IpCountServiceImpl();
    }
}
