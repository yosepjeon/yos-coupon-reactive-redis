package com.yosep.msa.coupon.coupon.service

import com.yosep.msa.coupon.common.CouponUtil
import com.yosep.msa.coupon.coupon.domain.withAmount.CouponCounter
import com.yosep.msa.coupon.coupon.domain.withAmount.CouponCounterCaculateResult
import com.yosep.msa.coupon.coupon.repository.CouponRedisCounterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CouponCounterService(
    @Autowired
    private val repository: CouponRedisCounterRepository
) {
    fun getCouponCounter(id: String): Mono<CouponCounter> {

        return repository.findById(id)
    }

    fun useCouponOnce(id: String): Mono<CouponCounterCaculateResult> {
        var responseBody: CouponCounterCaculateResult

        return repository.decrease(id)
            .switchIfEmpty(Mono.empty())
            .flatMap { value ->
                responseBody = CouponCounterCaculateResult(CouponUtil.isSafeCountValue(value))
                Mono.justOrEmpty(responseBody)
            }
            .doOnSuccess {

                if (!it.result) {
                    repository.increase(id).subscribe()
                }
            }
    }

    fun useCouponMultiple(id: String, num: Long): Mono<CouponCounterCaculateResult> {
        var responseBody: CouponCounterCaculateResult

        return repository.decrease(id, num)
            .switchIfEmpty(Mono.empty())
            .flatMap { value ->
                responseBody = CouponCounterCaculateResult(CouponUtil.isSafeCountValue(value))
                Mono.justOrEmpty(responseBody)
            }
            .doOnSuccess {
                if (!it.result) {
                    repository.increase(id, num).subscribe()
                }
            }
    }

}