package com.newlight77.core.signup

import com.newlight77.core.metafile.MetafileDomain
import com.newlight77.core.notification.EmailNotificationDomain
import com.newlight77.core.notification.SmsNotificationDomain
import com.newlight77.core.right.AccessRight
import java.util.*

interface ISignupAdapter {
    fun save(signup: SignupDomain): SignupDomain
    fun delete(username: String)
    fun findByUsername(username: String): Optional<SignupDomain>
    fun findAll(): List<SignupDomain>
    fun update(signup: SignupDomain): SignupDomain

    fun register(signup: SignupDomain) : Boolean
    fun unregister(username: String) : Boolean
    fun sendEmail(notification: EmailNotificationDomain) : Boolean
    fun sendSms(notification: SmsNotificationDomain) : Boolean
    fun updateStatus(signup: SignupDomain): SignupDomain

    // events
    fun statusUpdated(signup: SignupDomain)

    fun assignRole(username: String, accessRight: AccessRight)
}