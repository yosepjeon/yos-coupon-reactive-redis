package com.yosep.msa.coupon.coupon.service

import com.yosep.msa.coupon.coupon.domain.CouponCounter
import com.yosep.msa.coupon.coupon.repository.CouponRedisCounterRepository
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Component
class CouponCounterHandler(
    private val repository: CouponRedisCounterRepository
) {
    fun getById(req: ServerRequest): Mono<ServerResponse> = ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body<CouponCounter>(Mono.justOrEmpty(repository.findById(req.pathVariable("id").toString())))
        .switchIfEmpty(notFound().build())


}