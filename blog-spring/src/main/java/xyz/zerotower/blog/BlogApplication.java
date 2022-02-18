package xyz.zerotower.blog;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

/**
 * @author ZeroTower
 * @version 01 2021-04-10
 * 移除了elasticSearch,三方登录只使用github和gitee
 * @version 02 2022-02-18
 * 更新了GitHub和gitee的oauth信息
 */
@MapperScan("xyz.zerotower.blog.dao")
//@EnableDiscoveryClient
@EnableScheduling
@SpringBootApplication

public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }



}
