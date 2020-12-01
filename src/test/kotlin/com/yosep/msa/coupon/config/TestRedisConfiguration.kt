//package com.yosep.msa.coupon.config
//
//import org.springframework.boot.test.context.TestConfiguration
//import javax.annotation.PostConstruct
//import javax.annotation.PreDestroy
//import redis.embedded.RedisServer;
//
//
//@TestConfiguration
//class TestRedisConfiguration {
//    private val redisServer: RedisServer
//    @PostConstruct
//    fun postConstruct() {
//        println("시작!!!!")
//        redisServer.start()
//    }
//
//    @PreDestroy
//    fun preDestroy() {
//        println("종료!!!!")
//        redisServer.stop()
//    }
//
//    init {
//        redisServer = RedisServer(6379)
//    }
//}