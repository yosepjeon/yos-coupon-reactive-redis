package com.yosep.msa.practice.reactor

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Mono

@SpringBootTest(classes = [ReactorBasicTest1::class])
class ReactorBasicTest1 {

    @Test
    fun test1() {

        var seq =  Mono.just(1)
            .doOnNext { it ->
                it+1
            }

        seq.subscribe {
            it -> it+1
        }


    }
}