package com.newlight77.core.signup

import com.newlight77.core.metafile.MetafileDomain
import com.newlight77.core.notification.MetaNotificationDomain

interface ISignupService {
    fun signup(signup: SignupDomain, metaNotification: MetaNotificationDomain) : SignupStateDomain
    fun findByUsername(username: String): SignupDomain
    fun findAll(): List<SignupDomain>
    fun delete(signup: SignupDomain, authorizationCode: String?)
    fun activate(signup: SignupDomain): SignupStateDomain
    fun deactivate(signup: SignupDomain): SignupStateDomain
    fun resendCode(signup: SignupDomain, metaNotification: MetaNotificationDomain): SignupStateDomain
    fun verifyByCode(signup: SignupDomain, code: String): SignupStateDomain
    fun verifyByCodeFromToken(token: String): SignupStateDomain

    fun updateStatus(signup: SignupDomain, status: Status): SignupStateDomain
}