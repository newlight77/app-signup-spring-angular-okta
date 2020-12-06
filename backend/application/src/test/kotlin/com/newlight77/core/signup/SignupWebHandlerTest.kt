package com.newlight77.core.signup

import com.icegreen.greenmail.util.GreenMail
import com.icegreen.greenmail.util.ServerSetup
import com.newlight77.core.InfrastructureMockBeans
import com.newlight77.core.email.EmailMessage
import com.newlight77.core.email.EmailService
import com.newlight77.core.login.SignupJpaRepository
import com.newlight77.core.metafile.*
import com.newlight77.core.okta.OktaService
import com.newlight77.core.okta.IamRegisterService
import com.newlight77.core.twilio.SmsMessage
import com.newlight77.core.twilio.SmsService
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.io.TempDir
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.time.Instant
import java.util.*
import kotlin.math.absoluteValue


//@ExtendWith(SpringExtension::class, MockitoExtension::class)
@ExtendWith(MockitoExtension::class)
@ActiveProfiles("test")
@DataJpaTest
@ContextConfiguration(classes = [InfrastructureMockBeans::class])
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SignupWebHandlerTest {
    @Autowired
    lateinit var signupWebHandler: SignupWebHandler

    @Autowired
    lateinit var registrationService: IamRegisterService

    @Autowired
    lateinit var metafileService: MetafileService

    @Autowired
    lateinit var emailService: EmailService

    @Autowired
    lateinit var smsService: SmsService

    @MockBean
    lateinit var signupJpaRepository: SignupJpaRepository

    @MockBean
    lateinit var metaFileRepository: MetafileJpaRepository

    @TempDir
    lateinit var tempDir: File

    @Captor
    var signupCaptor: ArgumentCaptor<SignupEntity> = ArgumentCaptor.forClass(SignupEntity::class.java)

    val greenMail = GreenMail(ServerSetup(2525, null, "smtp"))

    @BeforeEach
    internal fun beforeEach() {
        greenMail.reset()
    }

    @BeforeAll
    internal fun before() {
        greenMail.start()
    }

    @AfterAll
    internal fun after() {
        greenMail.stop()
    }

    @Test
    fun `should do a signup successfully`() {
        // Arrange
        val username = "kong@gmail.com"
        val state = SignupStateModel.Builder(username)
                .build()
        val signup = SignupModel.Builder(username)
                .password("password")
                .firstname("kong")
                .lastname("to")
                .phoneNumber("1234567890")
                .signupDate(Instant.now())
                .status(Status.USER)
                .state(state)
                .build()

        val signupEntity = toEntity(fromModel(signup))
        Mockito.`when`(signupJpaRepository.save(any(SignupEntity::class.java))).thenReturn(signupEntity)
        Mockito.`when`(signupJpaRepository.findByUsername("kong@gmail.com")).thenReturn(
                listOf(),
                listOf(signupEntity))
        Mockito.`when`(registrationService.register(any(SignupDomain::class.java))).thenReturn(true)
        Mockito.`when`(emailService.send(any(EmailMessage::class.java))).thenReturn(true)
        Mockito.`when`(smsService.send(any(SmsMessage::class.java))).thenReturn("SM235g4fwee4qf32gqg8g")

        // Act
        val result = signupWebHandler.signup(signup)

        // Arrange
        Assertions.assertTrue(result.registered!!)
        Assertions.assertTrue(result.emailSent!!)
        Assertions.assertTrue(result.activationCodeSent!!)
    }

    @Test
    fun `should find a signup by username`() {
        // Arrange
        val username = "kong@gmail.com"
        val state = SignupStateModel.Builder(username)
                .build()
        val signup = SignupModel.Builder(username)
                .password("password")
                .firstname("kong")
                .lastname("to")
                .phoneNumber("1234567890")
                .signupDate(Instant.now())
                .status(Status.USER)
                .state(state)
                .build()

        val expected = SignupModel.Builder(username)
                .password("")
                .firstname("kong")
                .lastname("to")
                .phoneNumber("1234567890")
                .signupDate(signup.signupDate)
                .status(Status.USER)
                .state(state)
                .build()

        val signupEntity = toEntity(fromModel(signup))
        Mockito.`when`(signupJpaRepository.findByUsername(username)).thenReturn(listOf(signupEntity))

        // Act
        val result = signupWebHandler.findByUsername(username)

        // Arrange
        Assertions.assertEquals(expected.username, result.username)
        Assertions.assertEquals(null, result.password)
    }

    @Test
    fun `should verify the account by code`() {
        // Arrange
        val username = "kong@gmail.com"
        val code = "123456"
        val state = SignupStateModel.Builder(username)
                .build()
        val signup = SignupModel.Builder(username)
                .password("password")
                .firstname("kong")
                .lastname("to")
                .phoneNumber("1234567890")
                .signupDate(Instant.now())
                .status(Status.USER)
                .state(state)
                .build()
        val domain = fromModel(signup)
        domain.activationCode = "123456"
        val signupEntity = toEntity(domain)
        Mockito.`when`(signupJpaRepository.findByUsername(username)).thenReturn(listOf(signupEntity))
        Mockito.`when`(signupJpaRepository.save(any(SignupEntity::class.java))).thenReturn(signupEntity)

        // Act
        val result = signupWebHandler.verifyByCode(username, code)

        // Arrange
        Assertions.assertTrue(result.activatedByCode!!)
    }

    @Test
    fun `should update the signup status`() {
        // Arrange
        val username ="kong@gmail.com"
        val state = SignupStateModel.Builder(username)
                .build()
        val signup = SignupModel.Builder(username)
                .password("password")
                .firstname("kong")
                .lastname("to")
                .phoneNumber("1234567890")
                .signupDate(Instant.now())
                .status(Status.USER)
                .state(state)
                .build()

        val expected = SignupEntity( null, username, "kong", "to",
                "1234567890", "123456", "as3af3af34faf3",
                Status.USER.toString(), signup.signupDate, signupState=toEntity(fromModel(state)))

        val signupEntity = toEntity(fromModel(signup))
        Mockito.`when`(signupJpaRepository.findByUsername(username)).thenReturn(listOf(signupEntity))
        Mockito.`when`(signupJpaRepository.save(any(SignupEntity::class.java))).thenReturn(expected)

        // Act
        val result = signupWebHandler.updateStatus(username, Status.USER)

        // Arrange
        Assertions.assertTrue(result.statusUpdated!!)
//        Mockito.verify(repository).save(signupCaptor.capture())
//        Assertions.assertEquals(Status.USER.toString(), signupCaptor.value.status)
    }


    private fun <T> any(type: Class<T>): T = Mockito.any<T>(type)

}