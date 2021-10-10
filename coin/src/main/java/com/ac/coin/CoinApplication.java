package com.ac.coin;

import com.ac.coin.po.Graph;
import com.ac.coin.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
public class CoinApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoinApplication.class, args);
    }
}
