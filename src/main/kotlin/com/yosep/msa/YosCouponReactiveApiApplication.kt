package com.yosep.msa

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.yosep.*"])
@EnableAutoConfiguration(exclude = [WebMvcAutoConfiguration::class] )
class YosCouponReactiveApiApplication {

}


fun main(args: Array<String>) {

    runApplication<YosCouponReactiveApiApplication>(*args)
}