package com.newlight77.core.signup

import org.slf4j.LoggerFactory
import org.springframework.core.env.Environment
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.security.Principal
import javax.annotation.security.RolesAllowed
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("signup")
class SignupApi(val signupWebHandler: SignupWebHandler,
                private final val env: Environment) {

    private val logger = LoggerFactory.getLogger(javaClass)

    private var frontendBaseUrl = env.getProperty("core.frontendUrl")!!

    // not-secure
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun signup(@RequestBody signup: SignupModel): SignupStateModel {
        return signupWebHandler.signup(signup)
    }

    @RolesAllowed("ROLE_ac_user_r")
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    fun signup(principal: Principal): SignupModel {
        return signupWebHandler.findByUsername(authenticatedUser(principal))
    }

    @RolesAllowed("ROLE_ac_user_r")
    @GetMapping("state")
    @ResponseStatus(HttpStatus.OK)
    fun state(principal: Principal): SignupStateModel {
        return signupWebHandler.state(authenticatedUser(principal))
    }

    // not-secure
    @GetMapping("state/{username}")
    @ResponseStatus(HttpStatus.OK)
    fun state(@PathVariable username: String): SignupStateModel {
        return signupWebHandler.state(username)
    }

    @RolesAllowed("ROLE_ac_user_r")
    @PostMapping("code/resend")
    @ResponseStatus(HttpStatus.OK)
    fun resendCode(principal: Principal, @RequestBody resendCodeModel : SignupCodeModel): SignupStateModel {
        logger.info("signup resend code requested")
        return signupWebHandler.resendCode(authenticatedUser(principal))
    }

    @RolesAllowed("ROLE_ac_user_r")
    @PostMapping("code/verify")
    @ResponseStatus(HttpStatus.OK)
    fun verifyByCode(principal: Principal, @RequestBody codeModel : SignupCodeModel): SignupStateModel {
        logger.info("signup activation by code requested")
        return signupWebHandler.verifyByCode(authenticatedUser(principal), codeModel.code)
    }

    // not-secure
    @GetMapping("email/verify")
    @ResponseStatus(HttpStatus.OK)
    fun verifyByEmail(response: HttpServletResponse, @RequestParam token: String): ResponseEntity<Any> {
        logger.info("signup activation by email requested")
        val state = signupWebHandler.verifyByEmailFromToken(token)
        val url = frontendBaseUrl + "/register/activated/" + state.username
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, url).build()
    }

    @RolesAllowed("ROLE_ac_user_r")
    @PostMapping("status")
    @ResponseStatus(HttpStatus.OK)
    fun updateStatus(principal: Principal, @RequestBody statusModel : SignupStatusModel): SignupStateModel {
        logger.info("signup updating status requested")
        val status = toStatus(statusModel.status)
        return signupWebHandler.updateStatus(authenticatedUser(principal), status)
    }

    private fun authenticatedUser(principal: Principal): String {
        return principal.name
    }

    private fun validateUser(principal: Principal, username: String): String {
        if (principal.name != username)
            throw UserDetailNotDefinedException("username not expected")

        return username
    }

}

class UserDetailNotDefinedException(private val msg: String) : Throwable(msg) {}
