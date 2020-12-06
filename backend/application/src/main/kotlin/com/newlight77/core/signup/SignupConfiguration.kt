package com.newlight77.core.signup

import com.newlight77.core.metafile.IMetafileAdapter
import com.newlight77.core.metafile.IMetafileService
import com.newlight77.core.metafile.MetafileService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SignupConfiguration {

    @Bean
    fun signupService(signupAdapter: ISignupAdapter): ISignupService {
        return SignupService(signupAdapter)
    }

    @Bean
    fun metafileService(metafileAdapter: IMetafileAdapter): IMetafileService {
        return MetafileService(metafileAdapter)
    }

}