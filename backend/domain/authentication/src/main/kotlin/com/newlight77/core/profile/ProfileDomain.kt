package com.newlight77.core.profile

import java.time.Instant

data class ProfileDomain(
        var username: String,
        var lastDate: Instant? = Instant.now(),
        var portraitFilename: String? = null,
        var resumeFilename: String? = null,
        var resumeLinkedinFilename: String? = null
    ) {

    data class Builder(
            val username: String,
            var lastDate: Instant? = null,

            var portraitFilename: String? = null,
            var resumeFilename: String? = null,
            var resumeLinkedinFilename: String? = null
    ) {
        fun lastDate(lastDate: Instant?) = apply { this.lastDate = lastDate }
        fun portraitFilename(portraitFilename: String?) = apply { this.portraitFilename = portraitFilename }
        fun resumeFilename(resumeFilename: String?) = apply { this.resumeFilename = resumeFilename }
        fun resumeLinkedinFilename(resumeLinkedinFilename: String?) = apply { this.resumeLinkedinFilename = resumeLinkedinFilename }

        fun build() = ProfileDomain(
                username = username,
                lastDate = lastDate,
                portraitFilename = portraitFilename,
                resumeFilename = resumeFilename,
                resumeLinkedinFilename = resumeLinkedinFilename
        )
    }
}
