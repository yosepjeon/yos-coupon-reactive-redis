package com.yosep.msa.coupon.coupon.domain.resource

import com.yosep.msa.coupon.coupon.controller.CouponCounterRouter
import com.yosep.msa.coupon.coupon.domain.withAmount.CouponCounter
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.hateoas.server.reactive.WebFluxLinkBuilder

class CouponCounterResource(val couponCounter: CouponCounter, vararg links: Link?): EntityModel<CouponCounter>() {
    init {
        add(WebMvcLinkBuilder.linkTo(CouponCounterRouter::class).slash(couponCounter.id).withSelfRel())
//        add(WebFluxLinkBuilder.linkTo(CouponCounterRouter::class))
//        add(WebFluxLinkBuilder.linkTo(CouponCounterRouter::class).slash(couponCounter.id).withSelfRel().andAffordance())
    }
}