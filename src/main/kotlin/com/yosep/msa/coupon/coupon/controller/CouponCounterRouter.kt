package com.yosep.msa.coupon.coupon.controller

import com.yosep.msa.coupon.coupon.service.CouponCounterHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.router

@Configuration
class CouponCounterRouter(
    private val handler: CouponCounterHandler
) {
    @Bean
    fun routerFunction() = nest(
        path("/reactive/api/coupons"),
        router {
            listOf(
                GET("/{id}",handler::getById)
            )
        }
    )
}