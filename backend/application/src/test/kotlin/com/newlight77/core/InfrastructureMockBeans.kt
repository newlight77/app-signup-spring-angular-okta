package com.newlight77.core


import com.newlight77.core.email.EmailService
import com.newlight77.core.okta.OktaService
import com.newlight77.core.twilio.SmsService
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ActiveProfiles

@ComponentScan("com.newlight77.core")
@ActiveProfiles("test")
class InfrastructureMockBeans {
    @MockBean
    lateinit var oktaService: OktaService

    @MockBean
    lateinit var emailService: EmailService

    @MockBean
    lateinit var smsService: SmsService

}