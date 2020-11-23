//package com.yosep.msa.yoscouponapi.coupon.domain
//
//import com.fasterxml.jackson.annotation.JsonFormat
//import com.fasterxml.jackson.annotation.JsonManagedReference
//import com.yosep.msa.coupon.common.domain.BaseEntity
//import org.springframework.format.annotation.DateTimeFormat
//import java.time.LocalDateTime
//import javax.persistence.*
//import javax.validation.constraints.NotNull
//import kotlin.jvm.Transient
//
//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn
//abstract class Coupon(
//        @Id
//        open val couponId: String,
//
//        @NotNull
//        @Column(nullable = false)
//        open var couponName:String,
//
//        @NotNull
//        @Enumerated(EnumType.STRING)
//        @Column(length = 10, nullable = false)
//        open var couponState:CouponState,
//
////        @Enumerated(EnumType.STRING)
////        @Column(nullable = true)
////        var couponType
//
////        @JsonManagedReference
////        @OneToOne
////        var stock: Stock,
//
//        @Column(nullable = false)
//        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
//        open//        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "Asia/Seoul")
//        var startDate:LocalDateTime,
//
//        @Column(nullable = false)
//        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
//        open//        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "Asia/Seoul")
//        var endDate: LocalDateTime,
//
//        @Column(nullable = false)
//        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
////        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "Asia/Seoul")
//        override val createdDate:LocalDateTime,
//
//        @Column(nullable = false)
//        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
////        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "Asia/Seoul")
//        override var lastModifiedDate: LocalDateTime
//
//): BaseEntity() {
//
//}