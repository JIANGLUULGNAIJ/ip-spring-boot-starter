package xwx.jl.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;

@Component("ipProperties")
@ConfigurationProperties(prefix = "tools.ip" )
@Data
public class IpProperties {

    // 这些初始值都是默认值,导入这个starter模块可以在配置中自定义配置

    /**
     * 日志显示周期
     */
    @DurationUnit(ChronoUnit.SECONDS)
    private Long cycle = 5L;

    /**
     * 是否周期内重置数据
     */
    private Boolean cycleReset = false;

    /**
     * 日志输出格式 detail:详细模式 simple:极简模式(只显示ip)
     */
    private String model = LogModel.DETAIL.value;

    // 定义一个枚举
    public enum LogModel{
        // 每个常量都相当于调用了一次构造方法
        DETAIL("detail"),
        SIMPLE("simple");

        private String value;

        // 定义一个传入一个参数的够惨方法
        LogModel(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
