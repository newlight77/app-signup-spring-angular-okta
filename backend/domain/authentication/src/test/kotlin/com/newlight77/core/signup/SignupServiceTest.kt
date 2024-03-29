package com.newlight77.core.signup

import com.newlight77.core.notification.EmailNotificationDomain
import com.newlight77.core.notification.MetaNotificationDomain
import com.newlight77.core.notification.SmsNotificationDomain
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.time.Instant
import java.util.*

@ExtendWith(MockitoExtension::class)
class SignupServiceTest {

    @Mock
    lateinit var adapter: ISignupAdapter

    lateinit var service: SignupService

    @BeforeEach
    fun beforeEach() {
        Mockito.reset(adapter)
    }

    @Test
    fun `should do a signup`() {
        // Arranges
        val metaNotification = MetaNotificationDomain(baseUrl = "baseUrl", emailFrom = "emailFrom", smsFrom = "smsFrom")
        val signup = SignupDomain.Builder("kong@gmail.com")
                .firstname("kong")
                .lastname("to")
                .phoneNumber("1234567890")
                .signupDate(Instant.now())
                .activationCode("123456")
                .status(Status.USER)
                .state(SignupStateDomain.Builder("kong@gmail.com").build())
                .build()

        Mockito.`when`(adapter.findByUsername("kong@gmail.com")).thenReturn(
                Optional.empty(),
                Optional.of(signup)
        )
        Mockito.`when`(adapter.save(signup)).thenReturn(signup)
        Mockito.`when`(adapter.register(signup)).thenReturn(true)
        Mockito.`when`(adapter.sendEmail(any(EmailNotificationDomain::class.java))).thenReturn(true)
        Mockito.`when`(adapter.sendSms(any(SmsNotificationDomain::class.java))).thenReturn(true)

        service = SignupService(adapter)

        // Act
        val result = service.signup(signup, metaNotification)

        // Arrange
        Mockito.verify(adapter).save(signup)
        Assertions.assertTrue(result.saved!!)
        Assertions.assertTrue(result.registered!!)
        Assertions.assertTrue(result.emailSent!!)
        Assertions.assertTrue(result.smsSent!!)
    }

    @Test
    fun `should find a signup by username`() {
        // Arrange
        val username = "kong@gmail.com"
        val signup = SignupDomain.Builder(username)
                .firstname("kong")
                .lastname("to")
                .phoneNumber("1234567890")
                .signupDate(Instant.now())
                .activationCode("123456")
                .status(Status.USER)
                .build()

        Mockito.`when`(adapter.findByUsername(username)).thenReturn(Optional.of(signup))

        service = SignupService(adapter)

        // Act
        val result = service.findByUsername(username)

        // Arrange
        Assertions.assertEquals(signup, result)
    }

    @Test
    fun `should update the signup status`() {
        // Arrange
        val username = "kong@gmail.com"

        val state = SignupStateDomain.Builder("kong")
                .statusUpdated(true)
                .build()

        val signup = SignupDomain.Builder(username)
                .firstname("kong")
                .lastname("to")
                .phoneNumber("1234567890")
                .signupDate(Instant.now())
                .activationCode("123456")
                .status(Status.USER)
                .state(state)
                .build()

        Mockito.`when`(adapter.updateStatus(signup)).thenReturn(signup)

        service = SignupService(adapter)

        // Act
        val result = service.updateStatus(signup, Status.USER)

        // Arrange
        Mockito.verify(adapter).updateStatus(signup)
        Assertions.assertTrue(result.statusUpdated!!)
    }

    @Test
    fun `should do a hard delete the signup when the code matches the activation code`() {
        // Arrange
        val username = "kong@gmail.com"

        val state = SignupStateDomain.Builder("kong")
            .build()

        val signup = SignupDomain.Builder(username)
            .firstname("kong")
            .lastname("to")
            .phoneNumber("1234567890")
            .signupDate(Instant.now())
            .activationCode("123456")
            .status(Status.USER)
            .state(state)
            .build()

        Mockito.doNothing().`when`(adapter).delete(signup.username)

        service = SignupService(adapter)

        val code = "123456"

        // Act
        service.delete(signup, code)

        // Arrange
        Mockito.verify(adapter).delete("kong@gmail.com")

    }

    @Test
    fun `should do a soft delete the signup when the code does not match the activation code`() {
        // Arrange
        val username = "kong@gmail.com"

        val state = SignupStateDomain.Builder("kong")
            .build()

        val signup = SignupDomain.Builder(username)
            .firstname("kong")
            .lastname("to")
            .phoneNumber("1234567890")
            .signupDate(Instant.now())
            .activationCode("123456")
            .status(Status.USER)
            .state(state)
            .build()

        service = SignupService(adapter)

        val code = ""

        // Act
        service.delete(signup, code)

        // Arrange
        Assertions.assertTrue(state.deleted!!)

    }

    @Test
    fun `should find all signups`() {

    }

    @Test
    fun `should activate the signup`() {

    }

    @Test
    fun `should deactivate the signup`() {

    }

    @Test
    fun `should resend code for a signup`() {

    }

    @Test
    fun `should verify by code for a signup`() {

    }

    @Test
    fun `should verify by email for a signup`() {

    }

    @Test
    fun `should update a signup upon portrait uploaded`() {

    }

    @Test
    fun `should update a signup upon resume uploaded`() {

    }

    @Test
    fun `should update a signup upon resume linkedin uploaded`() {

    }

    @Test
    fun `should generate an activation code with 6 digits`() {
        // Arrange
        service = SignupService(adapter)

        // Act
        val result = service.generateCode()

        // Arrange
        Assertions.assertEquals(6, result.length)
    }

    @Test
    fun `should encode and decode a string`() {
        // Arrange
        val code = "123456"
        service = SignupService(adapter)

        // Act
        val encoded = service.encode(code)
        val decoded = service.decode(encoded)

        // Arrange
        Assertions.assertEquals("123456", decoded)
    }


    @Test
    fun `should generate a random string`() {
        // Arrange
        service = SignupService(adapter)

        // Act
        val result = service.randomString()

        // Arrange
        Assertions.assertEquals(12, result.length)
    }

    private fun <T> any(type: Class<T>): T = Mockito.any<T>(type)
}