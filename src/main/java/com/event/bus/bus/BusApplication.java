package com.event.bus.bus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 由于EventBus是将消息队列放入到内存中的，listener消费这个消息队列，故系统重启之后，保存或者堆积在队列中的消息丢失。
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.event"})
public class BusApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusApplication.class, args);
    }

}
