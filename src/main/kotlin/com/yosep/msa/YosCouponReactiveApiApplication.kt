package com.yosep.msa

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.web.reactive.config.EnableWebFlux

@SpringBootApplication
@EnableAutoConfiguration(exclude = [WebMvcAutoConfiguration::class] )
@EnableWebFlux
class YosCouponReactiveApiApplication {

}


fun main(args: Array<String>) {

    runApplication<YosCouponReactiveApiApplication>(*args)
}