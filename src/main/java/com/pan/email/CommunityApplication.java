package com.pan.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * springboot容器启动类
 * auth:
 */
@SpringBootApplication
public class CommunityApplication {

    /**
     * 解决 Elasticsearch 和 Redis 底层的 Netty 启动冲突问题
     */
    @PostConstruct
    public void init() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);
    }

}
