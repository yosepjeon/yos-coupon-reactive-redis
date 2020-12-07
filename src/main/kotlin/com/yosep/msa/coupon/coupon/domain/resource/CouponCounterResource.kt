package com.yosep.msa.coupon.coupon.domain.resource

import com.yosep.msa.coupon.coupon.controller.CouponCounterRouter
import com.yosep.msa.coupon.coupon.domain.CouponCounter
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.Link
import org.springframework.hateoas.server.reactive.WebFluxLinkBuilder
import org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.*

class CouponCounterResource(val couponCounter: CouponCounter, vararg links: Link?): EntityModel<CouponCounter>() {
    init {
//        add(WebFluxLinkBuilder.linkTo(CouponCounterRouter::class).slash(couponCounter.id).withSelfRel())
//        WebFluxLinkBuilder.WebFluxLink
//        add(WebFluxLinkBuilder.linkTo(CouponCounterRouter::class.java).)
    }
}