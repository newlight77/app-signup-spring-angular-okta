package com.newlight77.core.profile

import java.util.*

interface IProfileAdapter {
    fun save(profile: ProfileDomain): ProfileDomain
    fun findByUsername(username: String): Optional<ProfileDomain>
}