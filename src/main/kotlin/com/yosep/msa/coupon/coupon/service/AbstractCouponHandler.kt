package com.yosep.msa.coupon.coupon.service

import reactor.core.publisher.Mono

abstract class AbstractCouponHandler {
    abstract fun getAll(): Mono<Any>

//    abstract fun getById():M
}