package com.yosep.msa.yoscouponapi.coupon.service

import com.yosep.msa.coupon.coupon.repository.CouponRedisCounterRepository
import org.springframework.stereotype.Service

@Service
class CouponCounterService(
    private val repository: CouponRedisCounterRepository
) {

}