package xwx.jl.service;

import java.util.Map;

public interface IpCountService {

    // 统计每次web访问请求数量
    void count();

    // 打印报表
    void printReport();
}
