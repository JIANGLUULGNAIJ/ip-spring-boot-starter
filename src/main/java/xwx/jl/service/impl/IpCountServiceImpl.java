package xwx.jl.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import xwx.jl.properties.IpProperties;
import xwx.jl.service.IpCountService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class IpCountServiceImpl implements IpCountService {

    /**
     * 下面这些自动装配报错都是因为误报
     * 导入此模块不会报错
     */

    // 数据存储采用hashmap方式，也可以使用redis
    private Map<String,Integer> ipCountMap = new HashMap<String,Integer>();

    // 这个请求对象来自导入这个starter的web工程,咱们用这个请求可以获取ip地址
    @Autowired
    private HttpServletRequest httpRequest;

    @Autowired
    private IpProperties ipProperties;

    @Override
    public void count() {

        // 从请求对象中获取ip
        String ip = httpRequest.getRemoteAddr();

        // 判断hashmap中是否含有该ip
        if (ipCountMap.containsKey(ip)){
            // 1.hashmap中有有该ip访问记录
            ipCountMap.put(ip,ipCountMap.get(ip)+1);
        }else {
            // 2.hashmap中没有该ip访问记录
            ipCountMap.put(ip,1);
        }
    }


    // 定时打印报表
    @Override
    @Scheduled(cron = "0/#{ipProperties.cycle} * * * * ?") // 根据用户设置的周期值来打印，若没有配置则使用默认值
    public void printReport() {


        if (ipProperties.getModel().equals(IpProperties.LogModel.DETAIL.getValue())){
            // 详细模式
            System.out.println("+---------ip---------+----num----+");
            for (Map.Entry<String,Integer> entry:ipCountMap.entrySet()) {
                System.out.println(String.format("|  %-18s|  %9d|",entry.getKey(),entry.getValue()));
            }
            System.out.println("+--------------------+-----------+");

        }else if (ipProperties.getModel().equals(IpProperties.LogModel.SIMPLE.getValue())){
            // 极简模式
            System.out.println("+---------ip---------+");
            for (String key:ipCountMap.keySet()) {
                System.out.println(String.format("|  %-18s|",key));
            }
            System.out.println("+--------------------+");

        }


        // 如果用户设置重置数据为true，则每次答应后都会重置数据
        if (ipProperties.getCycleReset()){
            ipCountMap.clear();
        }

    }
}
