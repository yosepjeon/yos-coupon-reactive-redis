package com.yosep.msa.coupon.coupon.controller

import com.yosep.msa.coupon.coupon.service.CouponCounterHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.router

@Configuration
//@EnableWebFlux
class CouponCounterRouter(val couponCounterHandler: CouponCounterHandler) {

    @Bean
    fun routerFunction() = nest(
        path("/reactive/api/coupons"),
        router {
            listOf(
                GET("/{id}",couponCounterHandler::getCouponById),
                POST("/use/{id}",couponCounterHandler::decreaseCouponByOne),
                GET("/test",couponCounterHandler::test)
            )
        }
    )
}