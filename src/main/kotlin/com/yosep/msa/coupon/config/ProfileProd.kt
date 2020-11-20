package com.yosep.msa.yoscouponapi.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.context.annotation.PropertySource

@Configuration
@Profile(value= ["prod"])
@PropertySource("classpath:prod/application.properties")
class ProfileProd {
}