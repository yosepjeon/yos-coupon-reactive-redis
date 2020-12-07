package com.yosep.msa.coupon.common

class CouponUtil {
    companion object {
        fun isSafeCountValue(value: Long):Boolean {
            return value >= 0
        }
    }
}