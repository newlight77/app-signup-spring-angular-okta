package com.newlight77.core.okta

import com.newlight77.core.right.AccessRight
import com.newlight77.core.signup.SignupDomain

interface IamRegisterService {
    fun register(signup: SignupDomain): Boolean
    fun delete(username: String): Boolean
    fun addRole(username: String, role: AccessRight): Boolean
}