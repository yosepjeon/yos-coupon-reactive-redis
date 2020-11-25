package com.yosep.msa.coupon.coupon.repository

import reactor.core.publisher.Mono

interface CounterRepository<T,ID> {
    fun increase(id: ID): Mono<Long>
    fun increase(id: ID, delta:Long): Mono<Long>
    fun decrease(id: ID): Mono<Long>
    fun decrease(id: ID, delta:Long): Mono<Long>
}