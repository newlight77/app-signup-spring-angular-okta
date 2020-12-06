package com.newlight77.core.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@ConfigurationProperties("security.oauth")
data class OAuthProperties (
    var issuer: String = "",
    var audience: String = "",
    var clientId: String = "",
    var clientSecret: String = "" ) {
}
