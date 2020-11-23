//package com.yosep.msa.coupon.coupon.domain
//
//import lombok.EqualsAndHashCode
//import lombok.ToString
//import javax.persistence.GeneratedValue
//import javax.persistence.GenerationType
//
//
//@EqualsAndHashCode(of = ["id"], callSuper = false)
//@Entity
//class Stock internal constructor() {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private val id: Long? = null
//
//    @NotNull
//    @Column(nullable = false)
//    private var total: Long? = null
//
//    @NotNull
//    @Column(nullable = false)
//    private var remain: Long? = null
//    fun increase(value: Long) {
//        ensureValidStock(value)
//        val nextTotal = total!! + value
//        val nextRemain = remain!! + value
//        ensureValidStock(nextTotal)
//        total = nextTotal
//        remain = nextRemain
//    }
//
//    fun decrease(value: Long) {
//        ensureValidStock(value)
//        val nextTotal = total!! - value
//        val nextRemain = remain!! - value
//        ensureValidStock(nextTotal)
//        total = nextTotal
//        remain = nextRemain
//    }
//
//    fun syncCurrent(value: Long) {
//        remain = value
//    }
//
//    companion object {
//        fun of(total: Long): Stock {
//            ensureValidStock(total)
//            val stock = Stock()
//            stock.total = total
//            stock.remain = total
//            return stock
//        }
//
//        private fun ensureValidStock(value: Long) {
//            if (value < 1L) {
//                throw InvalidStockValueException()
//            }
//        }
//    }
//}
