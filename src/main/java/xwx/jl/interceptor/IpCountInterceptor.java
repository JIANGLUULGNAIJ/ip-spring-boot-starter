package xwx.jl.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import xwx.jl.service.impl.IpCountServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IpCountInterceptor implements HandlerInterceptor {

    @Autowired
    private IpCountServiceImpl ipCountService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 每个ip请求都会统计访问次数，存入hashmap中，方便后续打印报表(定时的操作，用户可以自己配置定时时间)
        ipCountService.count();
        return true;
    }
}
