package com.yosep.msa.yoscouponapi.coupon.service

import com.yosep.msa.coupon.coupon.domain.CouponCounter
import com.yosep.msa.coupon.coupon.repository.CouponRedisCounterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty

@Service
class CouponCounterService(
    @Autowired
    private val repository: CouponRedisCounterRepository
) {
    fun getCouponCounter(id:String): Mono<CouponCounter> {
        println(id)
        return repository.findById(id).switchIfEmpty(Mono.empty())
    }
}