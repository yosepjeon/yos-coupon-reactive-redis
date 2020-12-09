package com.yosep.msa.coupon.coupon.service

import com.yosep.msa.coupon.coupon.domain.CouponCounter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Component
//@ComponentScan(basePackages = ["com.yosep.msa.*"])
class CouponCounterHandler(
    @Autowired
    private val service: CouponCounterService
) {
    //    fun getById(req: ServerRequest): Mono<ServerResponse> = ok()
//        .contentType(MediaType.APPLICATION_JSON)
//        .body<CouponCounter>(Mono.justOrEmpty(repository.findById(req.pathVariable("id").toString())))
//        .switchIfEmpty(notFound().build())
    fun getCouponById(req: ServerRequest): Mono<ServerResponse> {

        return ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body<CouponCounter>(service.getCouponCounter(req.pathVariable("id").toString())
                .switchIfEmpty(Mono.empty()))
            .switchIfEmpty(notFound().build())
    }

    fun decreaseCouponByOne(req: ServerRequest):Mono<ServerResponse> {
        return ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body<Boolean>(service.useCouponOnce(req.pathVariable("id").toString()))
            .switchIfEmpty(notFound().build())

    }

    fun test(req: ServerRequest): Mono<ServerResponse> {
        return ok().build()
    }

}