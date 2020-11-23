//package com.yosep.msa.coupon.coupon.service
//
//import com.yosep.msa.yoscouponapi.coupon.domain.withAmount.CouponWithAmount
//import com.yosep.msa.yoscouponapi.coupon.repository.CouponWithAmountRepository
//import org.springframework.http.MediaType
//import org.springframework.stereotype.Component
//import org.springframework.web.reactive.function.server.ServerRequest
//import org.springframework.web.reactive.function.server.ServerResponse
//import org.springframework.web.reactive.function.server.ServerResponse.notFound
//import org.springframework.web.reactive.function.server.ServerResponse.ok
//import org.springframework.web.reactive.function.server.body
//import reactor.core.publisher.Mono
//
//@Component
//class CouponWithAmountHandler(private val repository: CouponWithAmountRepository) {
//    fun getAll(req: ServerRequest): Mono<ServerResponse> = ok()
//        .contentType(MediaType.APPLICATION_JSON)
//        .body<List<CouponWithAmount>>(Mono.just(repository.findAll()))
//        .switchIfEmpty(notFound().build())
//
//
//}