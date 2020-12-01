package com.yosep.msa.coupon.coupon.domain

import org.jetbrains.annotations.NotNull
import org.springframework.data.redis.core.RedisHash
import java.util.Objects.nonNull

@RedisHash("coupon-counter")
data class CouponCounter(
    @NotNull
    val id: String,
    var count: Long
) {
//    @NotBlank
//    private var id: String? = null
//    private var value: Long? = null

    val isGreaterThanZero: Boolean
        get() = nonNull(count) && 0L < count

    companion object {
        fun of(id: String, value: Long): CouponCounter {
            return CouponCounter(id, value)
        }
    }
}