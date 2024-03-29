package com.newlight77.core.login

import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import java.security.Principal
import javax.annotation.security.RolesAllowed

@RestController
@RequestMapping("logins")
class LoginApi(val loginHandler: LoginWebHandler) {

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun login(@RequestBody login: LoginModel) {
        print("saving login : $login")
        loginHandler.login(login)
    }

    @RolesAllowed("ROLE_ac_user_r")
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    fun list(principal: Principal) : List<LoginModel>{
        return loginHandler.findByUsername(authenticatedUser(principal))
    }

    private fun authenticatedUser(principal: Principal): String {
        return principal.name
    }
}