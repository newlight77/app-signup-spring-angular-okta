package com.newlight77.core.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.core.io.Resource

@ConfigurationProperties("security.jwt")
class JwtProperties {
    lateinit var keyStore: Resource
    lateinit var keyStorePassword: String
    lateinit var keyPairAlias: String
    lateinit var keyPairPassword: String

}