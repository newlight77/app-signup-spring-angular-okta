package com.newlight77.core.signup

import com.newlight77.core.metafile.MetafileModel
import java.time.Instant

class SignupCodeModel(val code: String)
class SignupStatusModel(val status: String)

class SignupModel
    private constructor(
        val username: String,
        val password: String?,
        val firstname: String?,
        val lastname: String?,
        val phoneNumber: String?,
        var status: Status?,
        val signupDate: Instant?,

        var cguAcceptedVersion: String? = null,
        var resumeFile: MetafileModel? = null,
        var state: SignupStateModel? = null
    ) {

    data class Builder (
            val username: String,
            var password: String? = null,
            var firstname: String? = null,
            var lastname: String? = null,
            var phoneNumber: String? = null,
            var status: Status? = null,
            var signupDate: Instant? = null,

            var cguAcceptedVersion: String? = null,
            var resumeFile: MetafileModel? = null, // not used
            var state: SignupStateModel? = null
    ) {

        fun password(password: String?) = apply { this.password = password }
        fun firstname(firstname: String?) = apply { this.firstname = firstname }
        fun lastname(lastname: String?) = apply { this.lastname = lastname }
        fun phoneNumber(phoneNumber: String?) = apply { this.phoneNumber = phoneNumber }
        fun status(status: Status) = apply { this.status = status }
        fun signupDate(signupDate: Instant?) = apply { this.signupDate = signupDate }

        fun cguAcceptedVersion(cguAcceptedVersion: String?) = apply { this.cguAcceptedVersion = cguAcceptedVersion }
        fun resumeFile(resumeFile: MetafileModel?) = apply { this.resumeFile = resumeFile }
        fun state(state: SignupStateModel?) = apply {
            this.state = state ?: SignupStateModel.Builder(username).build()
        }

        fun build() = SignupModel(
                username = username,
                password = password,
                firstname = firstname,
                lastname = lastname,
                phoneNumber = phoneNumber,
                status = status,
                signupDate = signupDate ?: Instant.now(),
                cguAcceptedVersion = cguAcceptedVersion,
                resumeFile = resumeFile,
                state = state
        )
    }
}

fun toModel(domain: SignupDomain): SignupModel {
    return SignupModel.Builder(domain.username)
            .password(domain.password)
            .firstname(domain.firstname)
            .lastname(domain.lastname)
            .phoneNumber(domain.phoneNumber)
            .status(domain.status!!)
            .signupDate(domain.signupDate)
            .resumeFile(domain.resumeFile?.let { com.newlight77.core.metafile.toModel(it) })
            .cguAcceptedVersion(domain.cguAcceptedVersion)
            .state(domain.state?.let { toModel(it) })
            .build()
}

fun fromModel(model: SignupModel): SignupDomain {
    return SignupDomain.Builder(model.username)
            .password(model.password)
            .firstname(model.firstname)
            .lastname(model.lastname)
            .phoneNumber(model.phoneNumber)
            .status(model.status ?: Status.NONE)
            .signupDate(model.signupDate)
            .resumeFile(model.resumeFile?.let { com.newlight77.core.metafile.fromModel(it) })
            .cguAcceptedVersion(model.cguAcceptedVersion)
            .state(model.state?.let { fromModel(it) })
            .build()
}