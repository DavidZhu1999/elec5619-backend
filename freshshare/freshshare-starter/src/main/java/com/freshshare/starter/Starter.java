package com.freshshare.starter;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(scanBasePackages = "com.freshshare")
@MapperScan(basePackages= "com.**.mapper")
public class Starter {

    public static void main(String args[]){
        SpringApplication.run(Starter.class, args);
    }

}
