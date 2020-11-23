package com.yosep.msa.coupon.coupon.repository

import com.yosep.msa.coupon.config.RedisConfig
import com.yosep.msa.coupon.coupon.domain.CouponCounter
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.ResourceProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.test.context.junit4.SpringRunner

//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores::class)
@Import(RedisConfig::class, CouponCounterRepository::class)
@SpringBootTest(classes = [CouponCounterRepositoryTest::class])
class CouponCounterRepositoryTest(
    @Autowired
    val contentRedisOps: ReactiveRedisOperations<String, String>,

    @Autowired
    var couponCounterRepository: CouponCounterRepository<CouponCounter,String>
) {

//    @Test
//    fun couponCounterSaveImplTest() {
//        var valueOps = contentRedisOps.opsForValue()
//
//        // Given
//        var couponCounter = CouponCounter.of("coupon-id1", 10)
//
//        // When
////        var createdCouponCounter = valueOps.set("")
//        // Then
////        var findedCouponCounter = couponCounterRepository.findById(couponCounter.id)
//
////        Assert.assertEquals(findedCouponCounter.do, true)
////        Assert.assertEquals(createdCouponCounter, findedCouponCounter)
//    }

    @Test
    fun couponCounterSaveTest() {

        // Given
        var couponCounter = CouponCounter.of("coupon-id1", 10)

        // When
        var createdCouponCounter = couponCounterRepository.save(couponCounter)

        // Then
//        var findedCouponCounter = couponCounterRepository.findById(couponCounter.id)

//        Assert.assertEquals(findedCouponCounter.do, true)
//        Assert.assertEquals(createdCouponCounter, findedCouponCounter)
    }
}