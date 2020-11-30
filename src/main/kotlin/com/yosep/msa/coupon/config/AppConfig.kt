package com.yosep.msa.coupon.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.yosep.msa.coupon.coupon.domain.CouponCounter
import com.yosep.msa.coupon.coupon.repository.CouponRedisCounterRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import java.util.*


@Configuration
class AppConfig {
    @Bean
    fun modelMapper(): ModelMapper {
        return ModelMapper()
    }

    @LoadBalanced
    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper().registerModule(KotlinModule())
    }

    @Bean
    fun applicationRunner(): ApplicationRunner? {
        return object : ApplicationRunner {
            @Autowired
            lateinit var repo: CouponRedisCounterRepository

            @Throws(Exception::class)
            override fun run(args: ApplicationArguments) {
                // TODO Auto-generated method stub
                for (i in 1..5) {
                    val couponCounter = CouponCounter("test$i", 100)
                    repo.save(couponCounter).subscribe {
                        println("$couponCounter.id 생성완료")
                    }
                }
            }
        }
    }
}