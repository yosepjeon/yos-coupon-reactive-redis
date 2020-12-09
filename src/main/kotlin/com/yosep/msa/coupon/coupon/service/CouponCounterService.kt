package com.yosep.msa.coupon.coupon.service

import com.yosep.msa.coupon.common.CouponUtil
import com.yosep.msa.coupon.coupon.domain.CouponCounter
import com.yosep.msa.coupon.coupon.repository.CouponRedisCounterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import java.util.function.Consumer

@Service
class CouponCounterService(
    @Autowired
    private val repository: CouponRedisCounterRepository
) {
    fun getCouponCounter(id:String): Mono<CouponCounter> {

        return repository.findById(id)
    }

    fun useCouponOnce(id:String):Mono<Boolean> {
        println("useCouponOnce Call")


        return repository.decrease(id)
            .switchIfEmpty(Mono.empty())
            .flatMap { value -> Mono.justOrEmpty(CouponUtil.isSafeCountValue(value))}
            .doOnSuccess {
                if(!it) {
                    println("값을 복구합니다.")
                    repository.increase(id).subscribe()
                }
            }
//            .blockOptional()
//            .orElse(false)


//        return repository.decrease(id)
//            .switchIfEmpty(Mono.empty())
//            .flatMap { value -> Mono.justOrEmpty(CouponUtil.isSafeCountValue(value))}
//            .doOnSuccess { it -> {
//                println("$it@@@@")
//                doOnSuccessConsumer(it)
//            } }
//            .blockOptional()
//            .orElse(false)
    }

    fun useCouponMultiple(id:String, num:Long) {

    }

}